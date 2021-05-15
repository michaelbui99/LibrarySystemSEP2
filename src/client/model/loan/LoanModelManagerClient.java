package client.model.loan;

import client.model.material.Material;
import client.model.user.borrower.Borrower;
import client.network.Client;
import client.network.RMIClient;
import client.network.RMIClientImpl;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;

public class LoanModelManagerClient implements LoanModelClient
{

  private Client client;
  private PropertyChangeSupport support;

  public LoanModelManagerClient(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    try
    {
      ((RMIClient) client).addPropertyChangeListener(EventTypes.LOANREGISTERED, evt -> support.firePropertyChange(evt));
      ((RMIClient) client).addPropertyChangeListener(EventTypes.LOANENDED, evt -> support.firePropertyChange(evt));
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void registerLoan(Material material, Borrower borrower)
      throws IllegalStateException
  {
        client.registerLoan(material, borrower);
  }

  @Override public void registerReservation(Material material,
      Borrower borrower) throws IllegalStateException, NoSuchElementException
  {
     client.registerReservation(material, borrower);
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    return client.getAllLoansByCPR(cpr);
  }

  @Override public void returnMaterial(int loanID)
  {

  }

  @Override public void endLoan(Loan loan)
  {
    client.endLoan(loan);
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
