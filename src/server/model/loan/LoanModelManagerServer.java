package server.model.loan;
//Michael
import database.loan.ReservationDAOImpl;
import shared.loan.Loan;
import shared.loan.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import database.loan.LoanDAOImpl;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    try
    {
      Loan loan = LoanDAOImpl.getInstance()
          .create(material, borrower,null, LocalDate.now().toString());
      //Event is fired and caught in Server. Sever redirects the event to the client using the Client Callback.
      support.firePropertyChange(EventTypes.LOANREGISTERED, null, loan);
    }
    catch (IllegalStateException e)
    {
      throw new IllegalStateException(e.getMessage());
    }
  }


  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    return LoanDAOImpl.getInstance().getAllLoansByCPR(cpr);
  }

  @Override public void endLoan(Loan loan)
  {
    LoanDAOImpl.getInstance().endLoan(loan);
    support.firePropertyChange(EventTypes.LOANENDED + loan.getBorrower().getCpr(), null, loan);
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
