package client.model.user;

import client.model.loan.Address;
import client.model.material.Material;

public class Borrower
{
  private String firstName, lastName, cpr, tlfNumber, email, password;
  private Address address;

  public Borrower(String cpr, String firstName, String lastName, String tlfNumber, Address address, String email, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.cpr = cpr;
    this.tlfNumber = tlfNumber;
    this.address = address;
    this.email = email;
    this.password = password;
  }

  public void loanMaterial(Material material, String cpr)
  {

  }
}
