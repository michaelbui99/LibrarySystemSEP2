package database.material;

import database.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MaterialCopyDAOImpl extends BaseDAO implements MaterialCopyDAO
{
  private static MaterialCopyDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static MaterialCopyDAO getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new MaterialCopyDAOImpl();
        }
      }
    }
    return instance;
  }
  @Override public synchronized void create(int materialID, int copyNr) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement("INSERT INTO material_copy(material_id,copy_no) values(?,?)");
      stm.setInt(1, materialID);
      stm.setInt(2,copyNr);
      stm.executeUpdate();
      connection.commit();
    }
  }

  @Override public int getFirstAvailableCopyNo(int materialID)
  {

    try(Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement("SELECT copy_no from material_copy where material_id = ? order by copy_no ASC limit 1;");
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
}
