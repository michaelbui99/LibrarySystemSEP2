package server.model.loan;

import client.model.loan.Loan;
import client.model.material.Material;
import client.model.user.borrower.Borrower;
import database.loan.LoanDAOImpl;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LoanModelManagerServer implements LoanModelServer
{
  private PropertyChangeSupport support;

  public LoanModelManagerServer()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void registerLoan(Material material, Borrower borrower)
  {
    //TODO: CHANGE LOAN DAO CREATE METHOD SIGNATURE
    Loan loan = LoanDAOImpl.getInstance()
        .create(material, borrower,null, calcDateTime());
    support.firePropertyChange(EventTypes.LOANREGISTERED, null, loan);
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    return LoanDAOImpl.getInstance().getAllLoansByCPR(cpr);
  }

  private String calcDateTime()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    return sdf.format(now);
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
