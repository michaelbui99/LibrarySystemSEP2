package database.address;

import shared.person.Address;

import java.sql.SQLException;

//Kutaiba
public interface AddressDAO
{
  Address create(String city, String streetName, int zipCode, String streetNr) throws
      SQLException;

  int getAddressId(String city, String streetName, int zipCode, String streetNr) throws SQLException;
}
