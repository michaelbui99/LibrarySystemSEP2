package database;

import java.sql.*;
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
      String targetAudience, String language) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "INSERT INTO Materiale (titel, målgruppe, beskrivelseAfIndholdet, emneord, forlag,sprog, udgivelsesDato) values (?,?,?,?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setString(1, title);
      stm.setString(2, targetAudience);
      stm.setString(3, description);
      stm.setString(4, tags);
      stm.setString(5, publisher);
      stm.setString(6, language);
      stm.setDate(7, Date.valueOf(releaseDate));

      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      keys.next();
      connection.commit();
      return keys.getInt(1);
    }
  }

  @Override public void create(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, Connection connection)
  {

  }

  @Override public boolean materialExistInDB(int materialID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      //Checks Database for material with given ID.
      PreparedStatement stm = connection.prepareStatement("SELECT * FROM Materiale where materialeid = ?");
      stm.setInt(1, materialID);
      ResultSet result = stm.executeQuery();

      //If we find a match in Database we return true, if not we return false
      if (result.next())
      {
        return true;
      }
      return false;
    }
  }

  //  @Override public Material findByID(int id)
//  {
//    return null;
//  }
}
