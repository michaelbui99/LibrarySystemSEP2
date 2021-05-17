package database.place;

import shared.materials.Place;
import database.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlaceImpl extends BaseDAO implements PlaceDAO
{
  private static PlaceImpl place;
  private static final Lock lock = new ReentrantLock();

  private PlaceImpl()
  {
  }

  public static PlaceImpl getInstance()
  {
    if (place == null)
    {
      synchronized (lock)
      {
        if (place == null)
        {
          place = new PlaceImpl();
        }
      }
    }
    return place;
  }

  @Override public Place create(int hallNo, String department,
      String creatorLName, String genre) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "INSERT INTO place (hall_no, department, creator_l_name, genre) VALUES (?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setInt(1, hallNo);
      stm.setString(2, department);
      stm.setString(3, creatorLName);
      stm.setString(4, genre);
      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      keys.next();
      connection.commit();
      return new Place(keys.getInt(1), hallNo, department, creatorLName, genre);
    }
  }

  @Override public int getPlaceID(int hallNo, String department,
      String creatorLName, String genre) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT place_id FROM place WHERE hall_no = ? AND department = ? AND creator_l_name = ? AND genre = ?");
      stm.setInt(1, hallNo);
      stm.setString(2, department);
      stm.setString(3, creatorLName);
      stm.setString(4, genre);
      ResultSet result = stm.executeQuery();

      if (result.next())
      {
        return result.getInt(1);
      }
      else
      {
        return -1;
      }
    }
  }

  @Override public int getPlaceIDForMaterial(int material_id, String type)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT place_id FROM ?  WHERE material_id = ?");
      stm.setInt(1, material_id);
      stm.setString(2, type);
      ResultSet result = stm.executeQuery();

      if (result.next())
      {
        return result.getInt(1);
      }
      else
      {
        return -1;
      }
    }
  }
}
