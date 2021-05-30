package database.material;

import shared.materials.DVD;
import shared.materials.Material;
import shared.materials.MaterialStatus;
import shared.materials.Place;
import database.BaseDAO;
import database.place.PlaceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DVDDAOImpl extends BaseDAO implements DVDDAO
{
  private static DVDDAO instance;
  private static final Lock lock = new ReentrantLock();

  private DVDDAOImpl()
  {
  }

  public static DVDDAO getInstance()
  {
    //Double lock check for Thread safety
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new DVDDAOImpl();
        }
      }
    }
    return instance;
  }

  @Override public synchronized int create(int material_id,
      String subtitle_lang, int length_, Place place) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if ((subtitle_lang == null || !subtitle_lang.matches("[a-zA-Z]+")) || (
          length_ <= 0) || (place == null))
      {
        throw new IllegalArgumentException();
      }
      else
      {
        if (PlaceImpl.getInstance()
            .getPlaceID(place.getHallNo(), place.getDepartment(),
                place.getDepartment(), place.getGenre()) == -1)
        {
          Place p = PlaceImpl.getInstance()
              .create(place.getHallNo(), place.getDepartment(),
                  place.getCreatorLName(), place.getGenre());
          PreparedStatement stm = connection.prepareStatement(
              "INSERT INTO DVD (material_id, subtitle_lang, length_, place_id) values (?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
          stm.setInt(1, material_id);
          stm.setString(2, subtitle_lang);
          stm.setInt(3, length_);
          stm.setInt(4, p.getPlaceId());

          stm.executeUpdate();
          ResultSet keys = stm.getGeneratedKeys();
          keys.next();
          connection.commit();
          return keys.getInt(1);
        }
        else
        {
          int pId = PlaceImpl.getInstance()
              .getPlaceID(place.getHallNo(), place.getDepartment(),
                  place.getCreatorLName(), place.getGenre());
          PreparedStatement stm = connection.prepareStatement(
              "INSERT INTO DVD (material_id, subtitle_lang, length_, place_id) values (?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
          stm.setInt(1, material_id);
          stm.setString(2, subtitle_lang);
          stm.setInt(3, length_);
          stm.setInt(4, pId);

          stm.executeUpdate();
          ResultSet keys = stm.getGeneratedKeys();
          keys.next();
          connection.commit();
          return keys.getInt(1);
        }
      }
    }
  }

  @Override public synchronized DVD createDVDCopy(int materialID, int copyNo)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if (copyNo <= 0)
      {
        throw new IllegalArgumentException();
      }
      else
      {
        //Creates material_copy
        PreparedStatement stm = connection.prepareStatement(
            "INSERT INTO material_copy (material_id, copy_no) VALUES (?,?)");
        stm.setInt(1, materialID);
        stm.setInt(2, copyNo);
        stm.executeUpdate();
        connection.commit();

        //Finds the necessary details to create the DVD object from DB.
        ResultSet dvdDetails = getDVDDetailsByID(materialID);
        List<String> materialKeywordList = MaterialDAOImpl.getInstance()
            .getKeywordsForMaterial(dvdDetails.getInt("material_id"));
        String materialKeywords = String.join(", ", materialKeywordList);

        if (dvdDetails.next())
        {
          //Creates and returns a DVD object if a DVD with given materialID exists.
          return new DVD(dvdDetails.getInt("material_id"),
              dvdDetails.getInt("copy_no"), dvdDetails.getString("title"),
              dvdDetails.getString("publisher"),
              String.valueOf(dvdDetails.getDate("release_date")),
              dvdDetails.getString("description_of_the_content"),
              materialKeywords, dvdDetails.getString("audience"),
              dvdDetails.getString("language_"),
              dvdDetails.getString("subtitle_lang"),
              dvdDetails.getString("length_"),
              new Place(dvdDetails.getInt("hall_no"),
                  dvdDetails.getString("department"),
                  dvdDetails.getString("creator_l_name"),
                  dvdDetails.getString("genre")), dvdDetails.getString("url"));
          // i removed the creator from here and i added place_id
        }
        return null;
      }
    }
  }

  @Override public ResultSet getDVDDetailsByID(int materialID)
      throws SQLException, NoSuchElementException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM material join material_copy USING (material_id) JOIN DVD using (material_id) JOIN place p on dvd.place_id = p.place_id WHERE material_id = ?");
      stm.setInt(1, materialID);
      ResultSet result = stm.executeQuery();
      if (result.next())
      {
        return result;
      }
      else
        throw new NoSuchElementException(
            "No DVD with materialID " + materialID + " exists.");
    }
  }

  @Override public boolean dvdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String playDuration, String genre) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM material JOIN dvd d USING (material_id) WHERE title = ? AND audience = ? AND description_of_the_content = ? AND publisher = ? AND language_ = ? AND release_date = ? AND genre = ? AND length_ = ?");
      stm.setString(1, title);
      stm.setString(2, targetAudience);
      stm.setString(3, description);
      stm.setString(4, publisher);
      stm.setString(5, language);
      stm.setDate(6, Date.valueOf(releaseDate));
      stm.setString(7, genre);
      stm.setInt(8, Integer.parseInt(playDuration));
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
          + "join dvd  on material.material_id = dvd.material_id  "
          + "join material_copy mt on dvd.material_id = mt.material_id "
          + "join place p on dvd.place_id = p.place_id ";

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
          DVD dvd = (new DVD(resultSet.getInt("material_id"),
              resultSet.getInt("copy_no"), resultSet.getString("title"),
              resultSet.getString("publisher"),
              String.valueOf(resultSet.getDate("release_date")),
              resultSet.getString("description_of_the_content"),
              materialKeywords, resultSet.getString("audience"),
              resultSet.getString("language_"),
              resultSet.getString("subtitle_lang"),
              resultSet.getString("length_"),
              new Place(resultSet.getInt("hall_no"),
                  resultSet.getString("department"),
                  resultSet.getString("creator_l_name"),
                  resultSet.getString("genre")), resultSet.getString("url")));
          dvd.setMaterialStatus(MaterialDAOImpl.getInstance()
              .checkIfCopyAvailable(resultSet.getInt("material_id")) ?
              MaterialStatus.Available :
              MaterialStatus.NotAvailable);
          dvd.setKeywords(materialKeywords);
          ml.add(dvd);
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

  @Override public synchronized void deleteDVDCopy(int materialID,
      int copyNumber) throws SQLException
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
