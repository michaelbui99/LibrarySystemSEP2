package client.model.user.borrower;

import client.model.loan.Address;
import client.model.material.Material;

public class Borrower
{
  private String firstName, lastName, cpr, tlfNumber, email, password;
  private Address address;

  public Borrower(String cpr_no, String f_name, String l_name, String email,
      String tel_no, Address address_id, String password)
  {
    this.firstName = f_name;
    this.lastName = l_name;
    this.cpr = cpr_no;
    this.tlfNumber = tel_no;
    this.address = address_id;
    this.email = email;
    this.password = password;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public String getCpr()
  {
    return cpr;
  }

  public String getTlfNumber()
  {
    return tlfNumber;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public Address getAddress()
  {
    return address;
  }
}
