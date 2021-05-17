package database.address;

import shared.person.Address;
import database.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AddressImpl extends BaseDAO implements AddressDAO
{
  private static AddressImpl address;
  private static final Lock lock = new ReentrantLock();

  private AddressImpl()
  {

  }

  public static AddressImpl getInstence()
  {
    if (address == null)
    {
      synchronized (lock)
      {
        if (address == null)
        {
          return address = new AddressImpl();
        }
      }
    }
    return address;
  }

  @Override public Address create(String city, String streetName, int zipCode,
      String streetNr) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "INSERT INTO address (city, zip_code, street_name, street_no) VALUES (?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setString(1, city);
      stm.setInt(2, zipCode);
      stm.setString(3, streetName);
      stm.setString(4, streetNr);
      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      keys.next();
      connection.commit();
      return new Address(keys.getInt(1), streetName, streetNr, zipCode, city);
    }
  }

  @Override public int getAddressId(String city, String streetName, int zipCode,
      String streetNr) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT address_id FROM address WHERE city = ? AND zip_code = ? AND street_name = ? AND street_no = ?");
      stm.setString(1, city);
      stm.setInt(2, zipCode);
      stm.setString(3, streetName);
      stm.setString(4, streetNr);
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
