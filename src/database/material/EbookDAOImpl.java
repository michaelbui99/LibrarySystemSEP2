package database.material;

import shared.materials.Material;
import shared.materials.MaterialStatus;
import shared.materials.reading.EBook;
import shared.person.MaterialCreator;
import database.BaseDAO;
import database.materialcreator.MaterialCreatorImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EbookDAOImpl extends BaseDAO implements EbookDAO
{
  private static EbookDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static EbookDAO getInstance()
  {
    //Double lock check for Thread safety
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new EbookDAOImpl();
        }
      }
    }
    return instance;
  }

  @Override public synchronized int create(int material_id, int page_no,
      MaterialCreator author, int license_no) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if (page_no <= 0 || author == null || license_no <= 0)
      {
        throw new IllegalArgumentException();
      }
      else
      {
        if (MaterialCreatorImpl.getInstance()
            .getCreatorId(author.getfName(), author.getlName(), author.getDob(),
                author.getCountry()) == -1)
        {
          MaterialCreator mc = MaterialCreatorImpl.getInstance()
              .create(author.getfName(), author.getlName(), author.getDob(),
                  author.getCountry());

          PreparedStatement stm = connection.prepareStatement(
              "INSERT INTO e_book (material_id, page_no, license_no, author) values (?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
          stm.setInt(1, material_id);
          stm.setInt(2, page_no);
          stm.setInt(3, license_no);
          stm.setInt(4, mc.getPersonId());
          stm.executeUpdate();
          ResultSet keys = stm.getGeneratedKeys();
          keys.next();
          connection.commit();
          return keys.getInt(1);
        }
        else
        {
          int mcId = MaterialCreatorImpl.getInstance()
              .getCreatorId(author.getfName(), author.getlName(),
                  author.getDob(), author.getCountry());

          PreparedStatement stm = connection.prepareStatement(
              "INSERT INTO e_book (material_id, page_no, license_no, author) values (?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
          stm.setInt(1, material_id);
          stm.setInt(2, page_no);
          stm.setInt(3, license_no);
          stm.setInt(4, mcId);
          stm.executeUpdate();
          ResultSet keys = stm.getGeneratedKeys();
          keys.next();
          connection.commit();
          return keys.getInt(1);
        }
      }
    }
  }

  @Override public synchronized EBook createEBookCopy(int materialID, int copyNo)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      //Creates material_copy
      PreparedStatement stm = connection.prepareStatement(
          "INSERT INTO material_copy (material_id, copy_no) VALUES (?,?)");
      stm.setInt(1, materialID);
      stm.setInt(2, copyNo);
      stm.executeUpdate();
      //ResultSet keys = stm.getGeneratedKeys();
      connection.commit();

      //Finds the necessary details to create the EBook object from DB.
      ResultSet eBookDetails = getEBookDetailsByID(materialID);
      if (eBookDetails.next())
      {
        //Creates and returns a EBook object if a EBook with given materialID exists.
        List<String> materialKeywordList = MaterialDAOImpl.getInstance()
            .getKeywordsForMaterial(eBookDetails.getInt("material_id"));
        String materialKeywords = String.join(", ", materialKeywordList);

        return new EBook(eBookDetails.getInt("material_id"),
            eBookDetails.getInt("copy_no"), eBookDetails.getString("title"),
            eBookDetails.getString("publisher"),
            String.valueOf(eBookDetails.getDate("release_date")),
            eBookDetails.getString("description_of_the_content"),
            materialKeywords, eBookDetails.getString("audience"),
            eBookDetails.getString("language_"), eBookDetails.getInt("page_no"),
            eBookDetails.getString("license_no"),
            eBookDetails.getString("genre"),
            //keys.getString("author"),
            new MaterialCreator(eBookDetails.getString("f_name"),
                eBookDetails.getString("l_name"),
                String.valueOf(eBookDetails.getDate("dob")),
                eBookDetails.getString("country")));
        // added author and genre
      }
      return null;
    }
  }

  @Override public ResultSet getEBookDetailsByID(int materialID)
      throws SQLException, NoSuchElementException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM material join material_copy USING (material_id) JOIN e_book using (material_id) JOIN material_creator mc on e_book.author = mc.person_id WHERE material_id = ?");
      stm.setInt(1, materialID);
      ResultSet result = stm.executeQuery();
      if (result.next())
      {
        return result;
      }
      else
        throw new NoSuchElementException(
            "No EBook with materialID " + materialID + " exists.");
    }
  }

  @Override public boolean eBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int pageCount, int licenseNr, String genre,
      MaterialCreator author) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM material JOIN e_book e USING (material_id) JOIN material_creator mc ON e.author = mc.person_id WHERE title = ? AND audience = ? AND description_of_the_content = ? AND publisher = ? AND language_ = ? AND release_date = ? AND genre = ? AND page_no = ? AND license_no = ? AND f_name = ? AND l_name = ? AND dob = ? AND country = ?");
      stm.setString(1, title);
      stm.setString(2, targetAudience);
      stm.setString(3, description);
      stm.setString(4, publisher);
      stm.setString(5, language);
      stm.setDate(6, Date.valueOf(releaseDate));
      stm.setString(7, genre);
      stm.setInt(8, pageCount);
      stm.setInt(9, licenseNr);
      stm.setString(10, author.getfName());
      stm.setString(11, author.getlName());
      stm.setDate(12, Date.valueOf(author.getDob()));
      stm.setString(13, author.getCountry());
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience)
  {
    //list where we store the results
    List<Material> ml = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      List<String> queryFragments = new ArrayList<>();
      String sql = "SELECT * FROM material "
          + "join e_book  on material.material_id = e_book.material_id  "
          + "join material_copy mt on e_book.material_id = mt.material_id "
          + "join material_creator mc on e_book.author = mc.person_id "
          + "join material_keywords mk on e_book.material_id = mk.material_id ";

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
        List<String> materialKeywordList = MaterialDAOImpl.getInstance()
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

          EBook eBook = new EBook(resultSet.getInt("material_id"),
              resultSet.getInt("copy_no"), resultSet.getString("title"),
              resultSet.getString("publisher"),
              String.valueOf(resultSet.getDate("release_date")),
              resultSet.getString("description_of_the_content"),
              resultSet.getString("keyword"), resultSet.getString("audience"),
              resultSet.getString("language_"), resultSet.getInt("page_no"),
              resultSet.getString("license_no"), resultSet.getString("genre"),
              new MaterialCreator(resultSet.getString("f_name"),
                  resultSet.getString("l_name"),
                  String.valueOf(resultSet.getDate("dob")),
                  resultSet.getString("country")));
          eBook.setMaterialStatus(MaterialDAOImpl.getInstance()
              .checkIfCopyAvailable(resultSet.getInt("material_id")) ?
              MaterialStatus.Available :
              MaterialStatus.NotAvailable);
          eBook.setKeywords(materialKeywords);
          ml.add(eBook);
        }
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    System.out.println("result size: " + ml.size());
    return ml;
  }

  @Override public synchronized void deleteEBookCopy(int materialID, int copyNumber)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "DELETE FROM material_copy WHERE material_id = ? AND copy_no = ?",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setInt(1, materialID);
      stm.setInt(2, copyNumber);
      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      connection.commit();
      keys.next();
    }
  }

}
