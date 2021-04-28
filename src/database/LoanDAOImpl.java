package database;

import client.model.loan.Loan;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoanDAOImpl extends BaseDAO implements LoanDAO
{
  private static LoanDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static LoanDAO getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new LoanDAOImpl();
        }
      }
    }
    return instance;
  }

  @Override public Loan create(int materialID, int copyNumber, String cpr,
      String materialType, String loanDate, String deadline) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement( //the table structure needs to change to the values from the query so we can test it
          "INSERT INTO laaner(laandato,deadline,afleveringsdato, cprnr, materialeid, kopinr) values (?,?,?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setDate(1, Date.valueOf(loanDate));
      stm.setDate(2, Date.valueOf(deadline));
      stm.setDate(3, null);
      stm.setString(4, cpr);
      stm.setInt(5, materialID);
      stm.setInt(6,copyNumber);
      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      keys.next();
      connection.commit();
      return new Loan(keys.getInt("laannr"),materialID,copyNumber,cpr,null,loanDate,deadline);
    }
  }
}
