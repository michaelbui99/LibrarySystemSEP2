package database.material;

import database.BaseDAO;
import shared.materials.DVD;
import shared.materials.Material;
import shared.materials.MaterialStatus;
import shared.materials.audio.AudioBook;
import shared.materials.audio.CD;
import shared.materials.reading.Book;
import shared.materials.reading.EBook;
import shared.person.MaterialCreator;
import shared.materials.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MaterialDAOImpl extends BaseDAO implements MaterialDAO
{

  private static MaterialDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static MaterialDAO getInstance()
  {
    //Double lock check for Thread safety
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new MaterialDAOImpl();
        }
      }
    }
    return instance;
  }

  @Override public int create(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String genre, String url, String keywords)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if ((title == null) || (publisher == null) || (releaseDate == null) || (
          description == null) || (keywords == null || keywords
          .matches("[0-9]+")) || (language == null
          || !language.equals("Engelsk") && !language.equals("Dansk")
          && !language.equals("Arabisk") || language.matches("[0-9]+")) || (
          genre == null || genre.matches(".*\\d.*")) || (targetAudience == null
          || !targetAudience.equals("Voksen") && !targetAudience.equals("Barn")
          && !targetAudience.equals("Teenager") && !targetAudience
          .equals("Familie") && !targetAudience.equals("Ældre")
          && !targetAudience.equals("Studerende") || targetAudience
          .matches("[0-9]+")))
      {
        throw new IllegalArgumentException();
      }
      else
      {
        PreparedStatement stm = connection.prepareStatement(
            "INSERT INTO Material ( title, audience, description_of_the_content, publisher,  language_, release_date, genre, url) values (?,?,?,?,?,?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, title);
        stm.setString(2, targetAudience);
        stm.setString(3, description);
        stm.setString(4, publisher);
        stm.setString(5, language);
        stm.setDate(6, Date.valueOf(releaseDate));
        stm.setString(7, genre);
        stm.setString(8, url);

        stm.executeUpdate();
        ResultSet keys = stm.getGeneratedKeys();
        keys.next();

        if (keywords.contains(", "))
        {
          String[] keywordsArray = keywords.split(", ");
          for (int i = 0; i < keywordsArray.length; i++)
          {
            PreparedStatement stm2 = connection.prepareStatement(
                "INSERT INTO material_keywords (material_id, keyword) VALUES (?,?)");
            stm2.setInt(1, keys.getInt(1));
            stm2.setString(2, keywordsArray[i]);
            stm2.executeUpdate();
          }
        }
        else
        {
          PreparedStatement stm2 = connection.prepareStatement(
              "INSERT INTO material_keywords (material_id, keyword) VALUES (?,?)");
          stm2.setInt(1, keys.getInt(1));
          stm2.setString(2, keywords);
          stm2.executeUpdate();
        }
        connection.commit();
        MaterialCopyDAOImpl.getInstance().create(keys.getInt(1), 1);
        return keys.getInt(1);
      }
    }
  }

  @Override public boolean materialExistInDB(int materialID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      //Checks Database for material with given ID.
      PreparedStatement stm = connection
          .prepareStatement("SELECT * FROM Material where material_id = ?");
      stm.setInt(1, materialID);
      ResultSet result = stm.executeQuery();

      //If we find a match in Database we return true, if not we return false
      return result.next();
    }
  }

  @Override public int getLatestCopyNo(int materialID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT material_copy.copy_no FROM material join material_copy USING (material_id) where material_id = ? ORDER BY copy_no desc LIMIT 1;");
      stm.setInt(1, materialID);
      ResultSet resultSet = stm.executeQuery();
      if (resultSet.next())
      {
        return resultSet.getInt(1);
      }
      else
        return 0;
      // throw new NoSuchElementException( "No material with materialID " + materialID + " exists.");
    }
  }

  //calculate how many copies for each material
  public int getCopyNumberForMaterial(int materialid)
  {
    int copyno = 0;
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT count (*) as copy_no  FROM material_copy where material_id = "
              + materialid);
      ResultSet resultSet = stm.executeQuery();
      if (resultSet.next())
      {
        return resultSet.getInt("copy_no");
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return copyno;
  }

  @Override public int getNumberOfAvailableCopies(int materialid)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT count(*) from material_copy where available = true and material_id = ?");
      stm.setInt(1, materialid);
      ResultSet result = stm.executeQuery();
      result.next();
      return result.getInt(1);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return 0;
  }

  @Override public boolean checkIfCopyAvailable(int materialid)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT *  FROM material_copy where material_id = ? AND available = true");
      stm.setInt(1, materialid);
      ResultSet resultSet = stm.executeQuery();
      if (resultSet.next())
      {
        return true;
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return false;
  }

  @Override public List<String> getKeywordsForMaterial(int materialid)
  {
    List<String> result = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT *  FROM material_keywords where material_id = " + materialid);
      ResultSet resultSet = stm.executeQuery();
      while (resultSet.next())
      {
        result.add(resultSet.getString("keyword"));
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return result;
  }

  @Override public int totalNumberOfCopies(int materialID)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT count(*) FROM material_copy WHERE material_id = ?");
      stm.setInt(1, materialID);
      ResultSet result = stm.executeQuery();
      result.next();
      return result.getInt(1);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return 0;
  }

  @Override public void deleteMaterial(int materialID)
  {
    try (Connection connection = getConnection())
    {
      if (materialID == 0 || materialExistInDB(materialID) == false)
      {
        throw new IllegalArgumentException();
      }
      else
      {
        PreparedStatement stm = connection
            .prepareStatement("DELETE FROM material WHERE material_id = ?",
                PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setInt(1, materialID);
        stm.executeUpdate();
        ResultSet keys = stm.getGeneratedKeys();
        connection.commit();
        keys.next();
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  public boolean deliverMaterial(int materialID, String cpr, int copy_no)
  {

    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "update loan set return_date = now() where material_id = ? and copy_no = ? and cpr_no = ?");
      int res = stm.executeUpdate();
      connection.commit();
      if (res > 0)
      {
        return true;
      }
      return false;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return false;
  }
  //  @Override public Material findByID(int id)
  //  {
  //    return null;
  //  }

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience, String type)
      throws SQLException
  {
    //list where we store the results
    List<Material> ml = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      //get resultSet for each category

      List<String> queryFragments = new ArrayList<>();
      String sql = "SELECT * FROM material " + "join " + type
          + " on material.material_id = " + type + ".material_id  "
          + "join material_copy mt on " + type
          + ".material_id = mt.material_id ";
      if (!type.equals("e_book") && !type.equals("audiobook"))
      {
        sql += "join place p on " + type + ".place_id = p.place_id ";
      }
      if (!type.equals("cd") && !type.equals("dvd"))
      {
        sql +=
            "join material_creator mc on " + type + ".author = mc.person_id ";
      }
      if (!title.isEmpty() || !language.isEmpty() || !genre.isEmpty()
          || !targetAudience.isEmpty())
      {
        sql += "where ";
      }

      if (!title.isEmpty())
      {
        queryFragments
            .add(" LOWER(material.title) LIKE  LOWER('%" + title + "%') ");
      }
      if (!language.isEmpty())
      {
        queryFragments.add(" material.language_  = '" + language + "' ");
      }
      if (!genre.isEmpty())
      {
        queryFragments
            .add(" LOWER(material.genre) LIKE LOWER('%" + genre + "%')");
      }
      if (!targetAudience.isEmpty())
      {
        queryFragments.add(" material.audience = '" + targetAudience + "' ");
      }
      sql += String.join(" and ", queryFragments);
      PreparedStatement stm = connection.prepareStatement(sql);
      ResultSet resultSet = stm.executeQuery();
      while (resultSet.next())
      {
        //find all keywords related to this material id
        List<String> materialKeywordList = this
            .getKeywordsForMaterial(resultSet.getInt("material_id"));
        String materialKeywords = String.join(", ", materialKeywordList);
        boolean match = false;
        if (!keywords.isEmpty())
        { //if keywords were specified in search, compare them to material keywords from DB (materialKeywordList)
          for (int i = 0; i < keywords.split(",").length; i++)
          {
            if (materialKeywords.toLowerCase(Locale.ROOT)
                .contains(keywords.split(",")[i].toLowerCase(Locale.ROOT)))
            {
              match = true; //search keyword matched material keyword - material will be added to result list
              break;
            }
          }
        }
        else
        {
          match = true; //if no keywords were specified by user - just add material keywords from DB to its material
        }

        if (match)
        {
          switch (type)
          {

            case "audiobook":
              AudioBook audiobook = new AudioBook(
                  resultSet.getInt("material_id"), MaterialDAOImpl.getInstance()
                  .getCopyNumberForMaterial(resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"), "",
                  resultSet.getString("audience"),
                  resultSet.getString("language_"), resultSet.getInt("length_"),
                  new MaterialCreator(resultSet.getString("f_name"),
                      resultSet.getString("l_name"),
                      String.valueOf(resultSet.getDate("dob")),
                      resultSet.getString("country")),
                  resultSet.getString("url"));
              audiobook.setMaterialStatus(
                  this.checkIfCopyAvailable(resultSet.getInt("material_id")) ?
                      MaterialStatus.Available :
                      MaterialStatus.NotAvailable);
              audiobook.setKeywords(materialKeywords);
              ml.add(audiobook);
              break;

            case "book":
              Book book = new Book(resultSet.getInt("material_id"),
                  MaterialDAOImpl.getInstance().getCopyNumberForMaterial(
                      resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"), "",
                  resultSet.getString("audience"),
                  resultSet.getString("language_"), resultSet.getString("isbn"),
                  resultSet.getInt("page_no"),
                  new Place(resultSet.getInt("hall_no"),
                      resultSet.getString("department"),
                      resultSet.getString("creator_l_name"),
                      resultSet.getString("genre")),
                  new MaterialCreator(resultSet.getString("f_name"),
                      resultSet.getString("l_name"),
                      String.valueOf(resultSet.getDate("dob")),
                      resultSet.getString("country")));
              book.setMaterialStatus(
                  this.checkIfCopyAvailable(resultSet.getInt("material_id")) ?
                      MaterialStatus.Available :
                      MaterialStatus.NotAvailable);
              book.setKeywords(materialKeywords);
              ;
              System.out.println("Keywords: " + book.getKeywords());
              ml.add(book);
              break;

            case "cd":
              CD cd = new CD(resultSet.getInt("material_id"),
                  MaterialDAOImpl.getInstance().getCopyNumberForMaterial(
                      resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"), "",
                  resultSet.getString("audience"),
                  resultSet.getString("language_"), resultSet.getInt("length_"),
                  new Place(resultSet.getInt("hall_no"),
                      resultSet.getString("department"),
                      resultSet.getString("creator_l_name"),
                      resultSet.getString("genre")),
                  resultSet.getString("url"));
              cd.setMaterialStatus(
                  this.checkIfCopyAvailable(resultSet.getInt("material_id")) ?
                      MaterialStatus.Available :
                      MaterialStatus.NotAvailable);
              cd.setKeywords(materialKeywords);
              ml.add(cd);
              break;

            case "dvd":
              DVD dvd = (new DVD(resultSet.getInt("material_id"),
                  MaterialDAOImpl.getInstance().getCopyNumberForMaterial(
                      resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"), "",
                  resultSet.getString("audience"),
                  resultSet.getString("language_"),
                  resultSet.getString("subtitle_lang"),
                  resultSet.getString("length_"),
                  new Place(resultSet.getInt("hall_no"),
                      resultSet.getString("department"),
                      resultSet.getString("creator_l_name"),
                      resultSet.getString("genre")),
                  resultSet.getString("url")));
              dvd.setMaterialStatus(
                  this.checkIfCopyAvailable(resultSet.getInt("material_id")) ?
                      MaterialStatus.Available :
                      MaterialStatus.NotAvailable);
              dvd.setKeywords(materialKeywords);
              ml.add(dvd);
              break;

            case "e_book":
              EBook eBook = new EBook(resultSet.getInt("material_id"),
                  MaterialDAOImpl.getInstance().getCopyNumberForMaterial(
                      resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"), "",
                  resultSet.getString("audience"),
                  resultSet.getString("language_"), resultSet.getInt("page_no"),
                  resultSet.getString("license_no"),
                  resultSet.getString("genre"),
                  new MaterialCreator(resultSet.getString("f_name"),
                      resultSet.getString("l_name"),
                      String.valueOf(resultSet.getDate("dob")),
                      resultSet.getString("country")));
              eBook.setMaterialStatus(
                  this.checkIfCopyAvailable(resultSet.getInt("material_id")) ?
                      MaterialStatus.Available :
                      MaterialStatus.NotAvailable);
              eBook.setKeywords(materialKeywords);
              ml.add(eBook);
              break;
            default:
              break;
          }
        }
      }
    }

    System.out.println("result size: " + ml.size());
    return ml;
  }

  @Override public boolean returnMaterial(int materialID, String cpr,
      int copy_no)
  {
    return false;
  }
}
