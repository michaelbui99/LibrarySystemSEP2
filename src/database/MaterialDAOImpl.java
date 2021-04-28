package database;

import java.sql.*;
import java.util.NoSuchElementException;
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

  @Override public int create( String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String genre, String url) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "INSERT INTO Material ( title, audience, description_of_the_content, keywords, publisher,  language_, release_date, genre, url) values (?,?,?,?,?,?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setString(1, title);
      stm.setString(2, targetAudience);
      stm.setString(3, description);
      stm.setString(4, tags);
      stm.setString(5, publisher);
      stm.setString(6, language);
      stm.setDate(7, Date.valueOf(releaseDate));
      stm.setString(8, genre);
      stm.setString(9, url);

      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      keys.next();
      connection.commit();
      return keys.getInt(1);
    }
  }
  @Override public boolean materialExistInDB(int materialID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      //Checks Database for material with given ID.
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM Material where material_id = ?");
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
      else return 0;
       // throw new NoSuchElementException( "No material with materialID " + materialID + " exists.");
    }
  }

  //  @Override public Material findByID(int id)
//  {
//    return null;
//  }
}
