package client.model.loan;

import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import client.network.Client;
import shared.network.PropertyChangeSubject;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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

      ((PropertyChangeSubject) client).addPropertyChangeListener(EventTypes.LOANREGISTERED, evt -> support.firePropertyChange(evt));
      ((PropertyChangeSubject) client).addPropertyChangeListener(EventTypes.LOANENDED, evt -> support.firePropertyChange(evt));
      ((PropertyChangeSubject) client).addPropertyChangeListener(EventTypes.LOANEXTENDED, evt -> support.firePropertyChange(evt));
      ((PropertyChangeSubject) client).addPropertyChangeListener(EventTypes.LOANEXTENDERROR, evt -> support.firePropertyChange(evt));

  }

  @Override public void registerLoan(Material material, Borrower borrower)
      throws IllegalStateException
  {
    try
    {
      client.registerLoan(material, borrower);
    }
    catch (IllegalStateException e)
    {
      throw new IllegalStateException(e.getMessage());
    }
  }


  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {

    try
    {
      return client.getAllLoansByCPR(cpr);
    }
    catch (NoSuchElementException e)
    {
      throw new NoSuchElementException(e.getMessage());
    }

  }

  @Override public void returnMaterial(int loanID)
  {

  }

  @Override public void endLoan(Loan loan)
  {
    client.endLoan(loan);
  }

  @Override public void extendLoan(Loan loan)
  {
    client.extendLoan(loan);
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
