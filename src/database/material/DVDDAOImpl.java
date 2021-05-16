package database.material;

import shared.materials.DVD;
import shared.places.Place;
import database.BaseDAO;
import database.place.PlaceImpl;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DVDDAOImpl extends BaseDAO implements DVDDAO
{
  private static DVDDAO instance;
  private static final Lock lock = new ReentrantLock();

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

  @Override public void create(int material_id, String subtitle_lang,
      int length_, Place place) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if (PlaceImpl.getInstance().getPlaceID(place.getHallNo(),
          place.getDepartment(), place.getDepartment(), place.getGenre()) == -1)
      {
        Place p = PlaceImpl.getInstance().create(place.getHallNo(),
            place.getDepartment(), place.getCreatorLName(), place.getGenre());
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
      }
      else
      {
        int pId = PlaceImpl.getInstance().getPlaceID(place.getHallNo(),
            place.getDepartment(), place.getCreatorLName(), place.getGenre());
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
      }
    }
  }

  @Override public DVD createDVDCopy(int materialID, int copyNo)
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

      //Finds the necessary details to create the DVD object from DB.
      ResultSet dvdDetails = getDVDDetailsByID(materialID);
      if (dvdDetails.next())
      {
        //Creates and returns a DVD object if a DVD with given materialID exists.
        return new DVD(dvdDetails.getInt("material_id"),
            dvdDetails.getInt("copy_no"),
            dvdDetails.getString("title"),
            dvdDetails.getString("publisher"),
            String.valueOf(dvdDetails.getDate("release_date")),
            dvdDetails.getString("description_of_the_content"),
            dvdDetails.getString("keywords"),
            dvdDetails.getString("audience"),
            dvdDetails.getString("language_"),
            dvdDetails.getString("subtitle_lang"),
            dvdDetails.getString("length_"),
            new Place(dvdDetails.getInt("hall_no"),
                dvdDetails.getString("department"),
                dvdDetails.getString("creator_l_name"),
                dvdDetails.getString("genre")),
            dvdDetails.getString("url"));
        // i removed the creator from here and i added place_id
      }
      return null;
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

}
