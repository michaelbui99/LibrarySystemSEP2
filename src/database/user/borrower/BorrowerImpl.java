package database.user.borrower;

import shared.person.Address;
import shared.person.borrower.Borrower;
import database.BaseDAO;
import database.address.AddressImpl;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BorrowerImpl extends BaseDAO implements BorrowerDAO
{

  private static BorrowerDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static BorrowerDAO getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new BorrowerImpl();
        }
      }
    }
    return instance;
  }

  @Override public Borrower create(String cpr, String firstName,
      String lastName, String email, String tlfNumber, Address address,
      String password) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if ((cpr == null || cpr.getBytes().length != 11 || !cpr.matches(".*\\d.*")
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
              "INSERT INTO Borrower(cpr_no,f_name,l_name, email, tel_no, address_id, password) values (?,?,?,?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
          stm.setString(1, cpr);
          stm.setString(2, firstName);
          stm.setString(3, lastName);
          stm.setString(4, email);
          stm.setString(5, tlfNumber);
          stm.setInt(6, ad.getAddressId());
          stm.setString(7, password);
          stm.executeUpdate();
          ResultSet keys = stm.getGeneratedKeys();
          keys.next();
          connection.commit();
          return new Borrower(cpr, firstName, lastName, email, tlfNumber,
              address, password);
        }
        else
        {
          int adId = AddressImpl.getInstence()
              .getAddressId(address.getCity(), address.getStreetName(),
                  address.getZipCode(), address.getStreetNr());
          PreparedStatement stm = connection.prepareStatement(
              //the table structure needs to change to the values from the query so we can test it
              "INSERT INTO Borrower(cpr_no,f_name,l_name, email, tel_no, address_id, password) values (?,?,?,?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
          stm.setString(1, cpr);
          stm.setString(2, firstName);
          stm.setString(3, lastName);
          stm.setString(4, email);
          stm.setString(5, tlfNumber);
          stm.setInt(6, adId);
          stm.setString(7, password);
          stm.executeUpdate();
          ResultSet keys = stm.getGeneratedKeys();
          keys.next();
          connection.commit();
          return new Borrower(cpr, firstName, lastName, email, tlfNumber,
              address, password);
        }
      }
    }
  }

  @Override public boolean loginBorrower(String cprNo, String password)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if ((cprNo == null || cprNo.getBytes().length != 11 || !cprNo.matches(".*\\d.*")
          || !cprNo.contains("-")) || (password == null))
      {
        throw new IllegalArgumentException();
      }
      else
      {
      // writing the sql query
      PreparedStatement stm = connection.prepareStatement(
          "SELECT cpr_no, password FROM borrower WHERE cpr_no = ? AND password = ?;");
      stm.setString(1, cprNo);
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

  @Override public Borrower getBorrower(String sprNo) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM borrower JOIN address a on a.address_id = borrower.address_id WHERE cpr_no = ?");
      stm.setString(1, sprNo);
      ResultSet result = stm.executeQuery();
      if (result.next())
      {
        return new Borrower(result.getString("cpr_no"),
            result.getString("f_name"), result.getString("l_name"),
            result.getString("email"), result.getString("tel_no"),
            new Address(result.getInt("address_id"),
                result.getString("street_name"), result.getString("street_no"),
                result.getInt("zip_code"), result.getString("city")),
            result.getString("password"));
      }
      else
      {
        throw new NoSuchElementException("Ingen låner med CPR: " + sprNo + " er registreret i systemet");
      }
    }
  }

  @Override public boolean borrowerCprNumberAlreadyExists(String cpr)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection
          .prepareStatement("SELECT * FROM borrower WHERE cpr_no = ?");
      stm.setString(1, cpr);
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }

  @Override public boolean borrowerEmailAlreadyExists(String email)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection
          .prepareStatement("SELECT * FROM borrower WHERE email = ?");
      stm.setString(1, email);
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }

  @Override public boolean borrowerPhoneNumberAlreadyExists(String phone)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection
          .prepareStatement("SELECT * FROM borrower WHERE tel_no = ?");
      stm.setString(1, phone);
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }

  @Override public boolean borrowerAlreadyExists(String cpr, String email,
      String phone) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM borrower WHERE cpr_no = ? AND email = ? AND tel_no = ?");
      stm.setString(1, cpr);
      stm.setString(2, email);
      stm.setString(3, phone);
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }
}
