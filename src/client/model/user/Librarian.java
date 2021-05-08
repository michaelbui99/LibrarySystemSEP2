package client.model.user;

import client.model.loan.Address;

public class Librarian
{

  private String firstName, lastName, cpr, tlfNumber, email, password;
  private Address address;
  private int employee_no;


  public Librarian(int employee_no, String firstName, String lastName, String cpr,
      String tlfNumber, String email, Address address, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.cpr = cpr;
    this.tlfNumber = tlfNumber;
    this.address = address;
    this.email = email;
    this.password = password;
    this.employee_no = employee_no;
  }

  public void registerBook()
  {

  }
}
