package client.model.library;

import client.model.loan.Address;

public class Librarian
{

  private String firstName, lastName, cpr, tlfNumber, email, password;
  private Address address;


  public Librarian(String cpr, String firstName, String lastName, String tlfNumber, Address address, String email, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.cpr = cpr;
    this.tlfNumber = tlfNumber;
    this.address = address;
    this.email = email;
    this.password = password;
  }

  public void registerBook()
  {

  }
}
