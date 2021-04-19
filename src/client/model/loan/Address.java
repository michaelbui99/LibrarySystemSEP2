package client.model.loan;

public class Address
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

}
