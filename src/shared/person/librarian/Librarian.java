package shared.person.librarian;

import shared.person.Address;

import java.io.Serializable;

public class Librarian implements Serializable
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

  public int getEmployee_no()
  {
    return employee_no;
  }

  public void registerBook()
  {

  }
}
