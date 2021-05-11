package database.user.librarian;

import client.model.user.librarian.Librarian;
import client.model.loan.Address;
import database.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LibrarianImpl extends BaseDAO implements LibrarianDAO
{

  private static LibrarianDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static LibrarianDAO getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new LibrarianImpl();
        }
      }
    }
    return instance;
  }

  @Override public Librarian create(int employee_no, String firstName,
      String lastName, String cpr, String tlfNumber, String email,
      Address address, String password) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          //the table structure needs to change to the values from the query so we can test it
          "INSERT INTO Librarian(employee_no,f_name,l_name,cpr_no,tel_no, email, address_id, password) values (?,?,?,?,?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setInt(1, employee_no);
      stm.setString(3, firstName);
      stm.setString(4, lastName);
      stm.setString(2, cpr);
      stm.setString(5, tlfNumber);
      stm.setString(7, email);
      stm.setObject(6, address);
      stm.setString(8, password);
      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      keys.next();
      connection.commit();
      return new Librarian(employee_no, firstName, lastName, cpr, tlfNumber,
          email, address, password);
    }
  }

  @Override public boolean librarianLogin(int employee_no, String password)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT employee_no, password " + "From librarian "
              + "Where employee_no = ? AND password = ?");
      stm.setInt(1, employee_no);
      stm.setString(2, password);
      ResultSet result = stm.executeQuery();

      if (result.next())
      {
        return true;
      }
    }
    return false;
  }
}
