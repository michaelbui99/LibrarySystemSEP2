package database.address;

import shared.person.Address;

import java.sql.SQLException;

/**
 * Address data access object
 *
 * @author Kutaiba
 * @version 1.0
 */
public interface AddressDAO
{
  /**
   * Creates a new address
   *
   * @param city       The city's name
   * @param streetName The street name
   * @param zipCode    The zip code
   * @param streetNr   The street number
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  Address create(String city, String streetName, int zipCode, String streetNr)
      throws SQLException;

  /**
   * Locate the required address using the given parameters
   *
   * @param city       The city's name
   * @param streetName The street name
   * @param zipCode    The zip code
   * @param streetNr   The street number
   * @return the address id
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  int getAddressId(String city, String streetName, int zipCode, String streetNr)
      throws SQLException;
}
