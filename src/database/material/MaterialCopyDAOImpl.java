package database.material;

import database.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
  @Override public void create(int materialID, int copyNr) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement("INSERT INTO MaterialeKopi(materialeid,kopino) values(?,?)");
      stm.setInt(1, materialID);
      stm.setInt(2,copyNr);
      stm.executeUpdate();
      connection.commit();
    }
  }
}
