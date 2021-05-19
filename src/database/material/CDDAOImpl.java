package database.material;

import shared.materials.Material;
import shared.materials.MaterialStatus;
import shared.materials.Place;
import shared.materials.audio.AudioBook;
import shared.materials.audio.CD;
import database.BaseDAO;
import database.place.PlaceImpl;
import shared.person.MaterialCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CDDAOImpl extends BaseDAO implements CDDAO
{
  private static CDDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static CDDAO getInstance()
  {
    //Double lock check for Thread safety
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new CDDAOImpl();
        }
      }
    }
    return instance;
  }

  @Override public void create(int material_id, int length_, Place place)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if (PlaceImpl.getInstance()
          .getPlaceID(place.getHallNo(), place.getDepartment(),
              place.getCreatorLName(), place.getGenre()) == -1)
      {
        Place p = PlaceImpl.getInstance()
            .create(place.getHallNo(), place.getDepartment(),
                place.getCreatorLName(), place.getGenre());
        PreparedStatement stm = connection.prepareStatement(
            "INSERT INTO CD (material_id, length_, place_id) values (?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setInt(1, material_id);
        stm.setInt(2, length_);
        stm.setInt(3, p.getPlaceId());

        stm.executeUpdate();
        ResultSet keys = stm.getGeneratedKeys();
        keys.next();
        connection.commit();
      }
      else
      {
        int pId = PlaceImpl.getInstance()
            .getPlaceID(place.getHallNo(), place.getDepartment(),
                place.getCreatorLName(), place.getGenre());
        PreparedStatement stm = connection.prepareStatement(
            "INSERT INTO CD (material_id, length_, place_id) values (?,?,?)",
            PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setInt(1, material_id);
        stm.setInt(2, length_);
        stm.setInt(3, pId);

        stm.executeUpdate();
        ResultSet keys = stm.getGeneratedKeys();
        keys.next();
        connection.commit();
      }
    }
  }

  @Override public CD createCDCopy(int materialID, int copyNo)
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
      connection.commit();

      //Finds the necessary details to create the CD object from DB.
      ResultSet cdDetails = getCDDetailsByID(materialID);
      if (cdDetails.next())
      {
        //Creates and returns a CD object if a CD with given materialID exists.
        return new CD(cdDetails.getInt("material_id"),
            cdDetails.getInt("copy_no"), cdDetails.getString("title"),
            cdDetails.getString("publisher"),
            String.valueOf(cdDetails.getDate("release_date")),
            cdDetails.getString("description_of_the_content"),
            cdDetails.getString("keywords"), cdDetails.getString("audience"),
            cdDetails.getString("language_"), cdDetails.getInt("length_"),
            new Place(cdDetails.getInt("hall_no"),
                cdDetails.getString("department"),
                cdDetails.getString("creator_l_name"),
                cdDetails.getString("genre")), cdDetails.getString("url"));
        // i added the place_id
      }
      return null;
    }
  }

  @Override public ResultSet getCDDetailsByID(int materialID)
      throws SQLException, NoSuchElementException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM material join material_copy USING (material_id) JOIN CD using (material_id) JOIN place p on cd.place_id = p.place_id WHERE material_id = ?");
      stm.setInt(1, materialID);
      ResultSet result = stm.executeQuery();
      if (result.next())
      {
        return result;
      }
      else
        throw new NoSuchElementException(
            "No CD with materialID " + materialID + " exists.");
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
          + "join cd  on material.material_id = cd.material_id  "
          + "join material_copy mt on cd.material_id = mt.material_id "
          + "join place p on cd.place_id = p.place_id "
          + "join material_keywords mk on cd.material_id = mk.material_id ";

      if (!title.isEmpty() || !language.isEmpty() || !genre.isEmpty() || !targetAudience.isEmpty())
      {
        sql += "where ";
      }

      if (!title.isEmpty())
      {
        queryFragments.add(" LOWER(material.title) LIKE  LOWER('%" + title + "%') ");
      }
      if (!language.isEmpty())
      {
        queryFragments.add(" material.language_  = '" + language + "' ");
      }
      if (!genre.isEmpty())
      {
        queryFragments.add(" LOWER(material.genre) LIKE LOWER('%" + genre + "%')");
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
            if (materialKeywords.toLowerCase(Locale.ROOT).contains(
                keywords.split(",")[i].toLowerCase(Locale.ROOT)))
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

          CD cd = new CD(resultSet.getInt("material_id"),
              MaterialDAOImpl.getInstance()
                  .getCopyNumberForMaterial(resultSet.getInt("material_id")),
              resultSet.getString("title"),
              resultSet.getString("publisher"),
              String.valueOf(resultSet.getDate("release_date")),
              resultSet.getString("description_of_the_content"),
              "",
              resultSet.getString("audience"),
              resultSet.getString("language_"),
              resultSet.getInt("length_"),
              new Place(resultSet.getInt("place_id"),
                  resultSet.getInt("hall_no"),
                  resultSet.getString("department"),
              resultSet.getString("creator_l_name"),
              resultSet.getString("genre")),
          resultSet.getString("url"));
          cd.setMaterialStatus(MaterialDAOImpl.getInstance().checkIfCopyAvailable(
               resultSet.getInt("material_id")) ? MaterialStatus.Available : MaterialStatus.NotAvailable);
          cd.setKeywords(materialKeywords);
          ml.add(cd);
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

}
