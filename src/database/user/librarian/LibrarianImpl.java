package database.user.librarian;

import shared.person.librarian.Librarian;
import shared.person.Address;
import database.BaseDAO;
import database.address.AddressImpl;

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
      if ((employee_no <= 0) || (cpr == null || cpr.getBytes().length != 11 || !cpr.matches(".*\\d.*")
          || !cpr.contains("-")) || (firstName == null) || (lastName == null)
          || (email == null || !email.contains("@")) || tlfNumber == null
          || tlfNumber.getBytes().length != 0 && !tlfNumber.contains("+45") || (
          address == null) || (password == null))
      {
        throw new IllegalArgumentException();
      }
      else
      {
        if (AddressImpl.getInstence()
            .getAddressId(address.getCity(), address.getStreetName(),
                address.getZipCode(), address.getStreetNr()) == -1)
        {
          Address ad = AddressImpl.getInstence()
              .create(address.getCity(), address.getStreetName(),
                  address.getZipCode(), address.getStreetNr());

          PreparedStatement stm = connection.prepareStatement(
              //the table structure needs to change to the values from the query so we can test it
              "INSERT INTO Librarian(employee_no,f_name,l_name,cpr_no,tel_no, email, address_id, password) values (?,?,?,?,?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
          stm.setInt(1, employee_no);
          stm.setString(2, firstName);
          stm.setString(3, lastName);
          stm.setString(4, cpr);
          stm.setString(5, tlfNumber);
          stm.setString(6, email);
          stm.setInt(7, ad.getAddressId());
          stm.setString(8, password);
          stm.executeUpdate();
          ResultSet keys = stm.getGeneratedKeys();
          keys.next();
          connection.commit();
          return new Librarian(employee_no, firstName, lastName, cpr, tlfNumber,
              email, address, password);
        }
        else
        {
          int adId = AddressImpl.getInstence()
              .getAddressId(address.getCity(), address.getStreetName(),
                  address.getZipCode(), address.getStreetNr());
          PreparedStatement stm = connection.prepareStatement(
              //the table structure needs to change to the values from the query so we can test it
              "INSERT INTO Librarian(employee_no,f_name,l_name,cpr_no,tel_no, email, address_id, password) values (?,?,?,?,?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
          stm.setInt(1, employee_no);
          stm.setString(2, firstName);
          stm.setString(3, lastName);
          stm.setString(4, cpr);
          stm.setString(5, tlfNumber);
          stm.setString(6, email);
          stm.setInt(7, adId);
          stm.setString(8, password);
          stm.executeUpdate();
          ResultSet keys = stm.getGeneratedKeys();
          keys.next();
          connection.commit();
          return new Librarian(employee_no, firstName, lastName, cpr, tlfNumber,
              email, address, password);
        }
      }
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

  @Override public boolean employeeNumberAlreadyExists(int employeeNo)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection
          .prepareStatement("SELECT * FROM librarian WHERE employee_no = ?");
      stm.setInt(1, employeeNo);
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }

  @Override public boolean librarianCprNumberAlreadyExists(String cpr)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection
          .prepareStatement("SELECT * FROM librarian WHERE cpr_no = ?");
      stm.setString(1, cpr);
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }

  @Override public boolean librarianEmailAlreadyExists(String email)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection
          .prepareStatement("SELECT * FROM librarian WHERE email = ?");
      stm.setString(1, email);
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }

  @Override public boolean librarianPhoneNumberAlreadyExists(String phone)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection
          .prepareStatement("SELECT * FROM librarian WHERE tel_no = ?");
      stm.setString(1, phone);
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }

  @Override public boolean librarianAlreadyExists(int employeeNo, String cpr,
      String email, String phone) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM librarian WHERE employee_no = ? AND cpr_no = ? AND email = ? AND tel_no = ?");
      stm.setInt(1, employeeNo);
      stm.setString(2, cpr);
      stm.setString(3, email);
      stm.setString(4, phone);
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }
}
