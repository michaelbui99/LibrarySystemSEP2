package client.model.loaner;

import client.model.loaner.Address;
import client.model.loaner.Loaner;
import client.model.material.Material;

public class LoanerImpl implements Loaner
{
  private String firstName, lastName, cpr, tlfNumber;
  private Address address;

  public LoanerImpl(String cpr, String firstName, String lastName, String tlfNumber, Address address)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.cpr = cpr;
    this.tlfNumber = tlfNumber;
    this.address = address;
  }

  @Override public void loanMaterial(Material material, String cpr)
  {

  }
}
