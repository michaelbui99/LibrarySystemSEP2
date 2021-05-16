package database.material;

import shared.places.Place;
import shared.materials.audio.CD;
import database.BaseDAO;
import database.place.PlaceImpl;

import java.sql.*;
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

}
