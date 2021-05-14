package client.model.loan;

import java.io.Serializable;

public class Address implements Serializable
{
  private String city, streetName;
  private int addressId ,zipCode, streetNr;

  public Address(int addressId, String streetName, int streetNr, int zipCode, String city)
  {
    this.addressId = addressId;
    this.streetName = streetName;
    this.streetNr = streetNr;
    this.zipCode = zipCode;
    this.city = city;
  }

  public Address(String city, String streetName, int zipCode, int streetNr)
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

  public int getStreetNr()
  {
    return streetNr;
  }
}
