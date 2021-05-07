package client.model.loan;

import client.model.material.Material;
import client.network.Client;

import java.beans.PropertyChangeSupport;
import java.util.List;

public class LoanModelManagerClient implements LoanModelClient
{

  private Client client;
  private PropertyChangeSupport support;

  public LoanModelManagerClient(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
  }

  @Override public Loan registerLoan(Material material, String loanerCPR,
      String deadline) throws IllegalStateException
  {
    return null;
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    //client.getAllLoansByCPR(String cpr)
    return null;
  }

  @Override public void deliverMaterial(int loanNo)
  {

  }

  @Override public void extendLoan()
  {

  }
}
