package shared.person;

import java.io.Serializable;

//Kasper
public class Address implements Serializable
{
  private int addressId ,zipCode;
  private String city, streetName, streetNr;

  public Address(int addressId, String streetName, String streetNr, int zipCode, String city)
  {
    this.addressId = addressId;
    this.streetName = streetName;
    this.streetNr = streetNr;
    this.zipCode = zipCode;
    this.city = city;
  }

  public Address(String city, String streetName, int zipCode, String streetNr)
  {
    this.city = city;
    this.streetName = streetName;
    this.zipCode = zipCode;
    this.streetNr = streetNr;
  }

  public String getCity()
  {
    return city;
  }

  public String getStreetName()
  {
    return streetName;
  }

  public int getAddressId()
  {
    return addressId;
  }

  public int getZipCode()
  {
    return zipCode;
  }

  public String getStreetNr()
  {
    return streetNr;
  }
}
