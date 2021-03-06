package server.model.loan;

import database.loan.LoanDAO;
import database.material.MaterialDAO;
import database.reservation.ReservationDAO;
import database.reservation.ReservationDAOImpl;
import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.reservation.Reservation;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Loan model implementation for server
 *
 * @author Michael
 * @version 1.0
 */
public class LoanModelManagerServer implements LoanModelServer
{
  private PropertyChangeSupport support;
  private ReservationDAO reservationDAO;
  private LoanDAO loanDAO;
  private MaterialDAO materialDAO;

  public LoanModelManagerServer(LoanDAO loanDAO, ReservationDAO reservationDAO,
      MaterialDAO materialDAO)
  {
    this.reservationDAO = reservationDAO;
    this.loanDAO = loanDAO;
    this.materialDAO = materialDAO;
    support = new PropertyChangeSupport(this);
  }

  @Override public void registerLoan(Material material, Borrower borrower)
  {
       /*Checks if the material has any reservations and if the borrower
        is the next person with the right to borrow the material.
        * */

    if (reservationDAO.hasReservations(material.getMaterialID())
        && !ReservationDAOImpl.getInstance()
        .getNextWaitingBorrowerCPRForMaterial(material.getMaterialID())
        .equals(borrower.getCpr()))
    {
      throw new IllegalStateException(
          "Materialet er reserveret af andre lånere og kan derfor ikke lånes");
    }
    /*Ends the borrowers reservation if material has reservation and borrower is the next on waiting list
     * */

    if (reservationDAO.hasReservations(material.getMaterialID())
        && reservationDAO
        .getNextWaitingBorrowerCPRForMaterial(material.getMaterialID())
        .equals(borrower.getCpr()) && reservationDAO.reservationIsReady(
        reservationDAO.getReservationIDByBorrowerMaterial(borrower.getCpr(),
            material.getMaterialID())) && materialDAO
        .checkIfCopyAvailable(material.getMaterialID()))
    {
      Reservation reservation = new Reservation(null, null, null, reservationDAO
          .getReservationIDByBorrowerMaterial(borrower.getCpr(),
              material.getMaterialID()), true);
      reservationDAO.endReservation(reservation);
      System.out.println("Ending reservation..."); //debugging
    }
    /*Throws exception If the material has reservations and the borrower is next on waiting list, but
     * the reservation is not marked as ready for pickup
     * */

    else if (reservationDAO.hasReservations(material.getMaterialID())
        && reservationDAO
        .getNextWaitingBorrowerCPRForMaterial(material.getMaterialID())
        .equals(borrower.getCpr()) && !reservationDAO.reservationIsReady(
        reservationDAO.getReservationIDByBorrowerMaterial(borrower.getCpr(),
            material.getMaterialID()))

    )
    {
      throw new IllegalStateException("Materialet er ikke klar endnu");
    }

    //Checks if an copy is available

    if (materialDAO.checkIfCopyAvailable(material.getMaterialID()))
    {
      Loan loan = loanDAO.create(material, borrower);
      System.out.println("Creating loan..."); //debugging
      //Event is fired and caught in Server. Sever redirects the event to the client using the Client Callback.
      support.firePropertyChange(EventTypes.LOANREGISTERED, null, loan);
      //Ends borrowers reservation If the material has reservation by the borrower and the borrower trying to borrow the material is next in waiting queue
    }
    else
      throw new IllegalStateException(
          "Ingen tilgængelige kopier, materialet kan reserveres i stedet");
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    try
    {
      List<Loan> loans = loanDAO.getAllLoansByCPR(cpr);
      for (Loan loan : loans)
      {
        //Loops through the loan and checks if the material has a loan.

        if (reservationDAO.hasReservations(loan.getMaterial().getMaterialID()))
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
    loanDAO.endLoan(loan);
    support.firePropertyChange(EventTypes.LOANENDED, null, loan);
  }

  @Override public Loan extendLoan(Loan loan)
  {
    try
    {
      //Checks if the Material of the Loan has any reservations and updates it's field variable before trying to extend the loan.
      loan.setMaterialHasReservation(
          reservationDAO.hasReservations(loan.getMaterial().getMaterialID()));

      //Stores a copy of the original state of the Loan to be used in DAO call.
      Loan temp = new Loan(loan.getMaterial(), loan.getBorrower(),
          loan.getDeadline(), loan.getLoanDate(), loan.getReturnDate(),
          loan.getLoanID());

      //Extends the loan on the object, if no exceptions are thrown, the loan is eligible for extension and will be updated in in DAO.
      loan.extendLoan();
      Loan extendedLoan = loanDAO.extendLoan(temp);
      support.firePropertyChange(EventTypes.LOANEXTENDED, null, extendedLoan);
      return extendedLoan;
    }
    catch (IllegalStateException e)
    {
      /*Redirects the error message in event, if the Loan cannot be extended. Loan is passed as old
       * value to be used for CPR check, such that the error is only shown to the specific user.
       * */

      support
          .firePropertyChange(EventTypes.LOANEXTENDERROR, loan, e.getMessage());
      throw new IllegalStateException(e.getMessage());
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
