package database.address;

import shared.places.Address;

import java.sql.SQLException;

public interface AddressDAO
{
  Address create(String city, String streetName, int zipCode, int streetNr) throws
      SQLException;

  int getAddressId(String city, String streetName, int zipCode, int streetNr) throws SQLException;
}
