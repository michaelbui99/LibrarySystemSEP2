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
import java.util.NoSuchElementException;

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
          .create(material, borrower, null, LocalDate.now().toString());
      //Event is fired and caught in Server. Sever redirects the event to the client using the Client Callback.
      support.firePropertyChange(EventTypes.LOANREGISTERED, null, loan);
    }
    catch (IllegalStateException e)
    {
      System.out.println("got here koi");
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    try
    {
      List<Loan> loans = LoanDAOImpl.getInstance().getAllLoansByCPR(cpr);
      for (Loan loan : loans)
      {
        //Loops through the loan and checks if the material has a loan.
        if (ReservationDAOImpl.getInstance()
            .hasReservations(loan.getMaterial().getMaterialID()))
        {
          loan.setMaterialHasReservation(true);
        }
      }
      return loans;
    }
    catch (NoSuchElementException e)
    {
      throw new NoSuchElementException(e.getMessage());
    }
  }

  @Override public void endLoan(Loan loan)
  {
    LoanDAOImpl.getInstance().endLoan(loan);
    support.firePropertyChange(EventTypes.LOANENDED, null, loan);
  }

  @Override public void extendLoan(Loan loan)
  {
    try
    {
      //Checks if the Material of the Loan has any reservations and updates it's field variable before trying to extend the loan.
      loan.setMaterialHasReservation(ReservationDAOImpl.getInstance()
          .hasReservations(loan.getMaterial().getMaterialID()));
      loan.extendLoan();
      Loan extendedLoan = LoanDAOImpl.getInstance().extendLoan(loan);
      support.firePropertyChange(EventTypes.LOANEXTENDED, null, extendedLoan);
    }
    catch (IllegalStateException e)
    {
      /*Redirects the error message in event, if the Loan cannot be extended. Loan is passed as old
      * value to be used for CPR check, such that the error is only shown to the specific user.
      * */
      support.firePropertyChange(EventTypes.LOANEXTENDERROR, loan, e.getMessage());
    }
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
