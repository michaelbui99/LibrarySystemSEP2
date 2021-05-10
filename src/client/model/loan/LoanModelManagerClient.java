package client.model.loan;

import client.model.material.Material;
import client.model.user.borrower.Borrower;
import client.network.Client;

import java.beans.PropertyChangeListener;
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
//    client.addPropertyChangeListener(EventTypes.LOANREGISTERED, evt -> {support.firePropertyChange(evt)})
  }

  @Override public void registerLoan(Material material, Borrower borrower,
      String deadline) throws IllegalStateException
  {
//    client.registerLoan();
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    //client.getAllLoansByCPR(String cpr)
    return null;
  }

  @Override public void deliverMaterial(int loanID)
  {

  }

  @Override public void extendLoan()
  {

  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
  support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
