package server.model.loan;

import database.DatabaseBuilder;
import database.loan.LoanDAOImpl;
import database.material.MaterialDAOImpl;
import database.reservation.ReservationDAO;
import database.reservation.ReservationDAOImpl;
import shared.loan.ExtendedLoan1State;
import shared.loan.ExtendedLoan2State;
import shared.loan.Loan;

import database.BaseDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.loan.NewLoanState;
import shared.materials.reading.Book;
import shared.person.borrower.Borrower;
import shared.util.EventTypes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LoanModelManagerServerTest extends DatabaseBuilder
{
  private LoanModelServer loanModel;
  private DatabaseBuilder databaseBuilder;
  private Borrower borrower, borrower2;
  private Book book;
  public static Loan extendedLoan;
  @BeforeEach void setup()
  {
  loanModel = new LoanModelManagerServer(LoanDAOImpl.getInstance(),
      ReservationDAOImpl.getInstance(), MaterialDAOImpl.getInstance());
    databaseBuilder = new DatabaseBuilder();
    borrower = new Borrower("111111-1111", "Michael", "Bui",
        "michael@gmail.com", "+4512345678", null, "password");
    borrower2 = new Borrower("111111-2222", "Mik", "Biu", "mik@gmail.com", "+4587654321", null, "password");
    book = new Book(1, 1, "Title1", "Publisher1", "2020-12-12",
        "HELLO DESC", null, "Voksen", "Dansk", "321432432", 200, null ,null);
    extendedLoan = null;
  }

  @Test void getAllLoansByCPRReturnsAllLoansTest() throws SQLException
  {
    //Creates DB with 1 borrower, 1 Loan and 1 Book with 1 copy.
    databaseBuilder.createDummyDatabaseDataWithLoan();
    List<Loan> loans = loanModel.getAllLoansByCPR("111111-1111");
    assertEquals(1, loans.size());
  }


  @Test void getAllLoansByCPRReturnsCorrectLoan() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    List<Loan> loans = loanModel.getAllLoansByCPR("111111-1111");
    Loan loan = loans.get(0);
    assertEquals("Book", loan.getMaterial().getMaterialType());
    assertEquals(1, loan.getLoanID());
    assertEquals(1, loan.getMaterial().getMaterialID());
    assertEquals(1, loan.getMaterial().getCopyNumber());
    assertEquals("111111-1111", loan.getBorrower().getCpr());
  }

  @Test void registerLoanTest() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    assertDoesNotThrow(()->{loanModel.registerLoan(book,borrower);});
    assertEquals(1, loanModel.getAllLoansByCPR(borrower.getCpr()).size());
  }

  @Test void registerLoanMaterialHasNoCopiesAvailableThrowsIllegalStateException()
      throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    loanModel.registerLoan(book,borrower);
    assertThrows(IllegalStateException.class, ()->loanModel.registerLoan(book, borrower2));
  }

  @Test void registerLoanMaterialIsReservedThrowsIllegalStateException()
      throws SQLException
  {
    //Test where material is reserved by other users and the borrower does not have a reservation on the material.
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    databaseBuilder.insertDummyReservation("111111-2222", false, 1);
    assertThrows(IllegalStateException.class, ()->loanModel.registerLoan(book,borrower));
  }

  @Test void registerLoanMaterialIsReservedBorrowerHasReservationThrowsIllegalStateException()
      throws SQLException
  {
    /*Test where material is reserved by other users and the borrower does have a reservation,
     but is not the next in the waiting list.  */
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    databaseBuilder.insertDummyReservation("111111-2222", false, 1);
    databaseBuilder.insertDummyReservation("111111-1111", false, 1);
    assertThrows(IllegalStateException.class, ()->loanModel.registerLoan(book,borrower));
  }

  @Test void registerLoanMaterialIsReservedBorrowerHasReservationAndIsNextThrowsIllegalStateException()
      throws SQLException
  {
    /*Test where material is reserved by other users and the borrower does have a reservation and is
    * the next in waiting list, but reservation is marked as not ready*/
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    databaseBuilder.insertDummyReservation("111111-1111", false, 1);
    assertThrows(IllegalStateException.class, ()->loanModel.registerLoan(book,borrower));
  }

  @Test void registerLoanMaterialIsReservedBorrowerHasReservationAndIsNextDoesNotThrow()
      throws SQLException
  {
    /*Test where material is reserved by other users and the borrower does have a reservation and is
     * the next in waiting list and reservation is marked as ready*/
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    databaseBuilder.insertDummyReservation("111111-1111", true, 1);
    databaseBuilder.insertDummyReservation("111111-2222", false, 1);
    assertDoesNotThrow(()->loanModel.registerLoan(book,borrower));
  }

  @Test void registerLoanMaterialIsReservedBorrowerHasReservationAndIsNextCreatesANewLoan()
      throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    databaseBuilder.insertDummyReservation("111111-1111", true, 1);
    databaseBuilder.insertDummyReservation("111111-2222", false, 1);
    assertDoesNotThrow(()->loanModel.registerLoan(book,borrower));
    assertEquals(1, loanModel.getAllLoansByCPR("111111-1111").size());
    assertEquals("Title1", loanModel.getAllLoansByCPR("111111-1111").get(0).getMaterial().getTitle());
    assertEquals("111111-1111", loanModel.getAllLoansByCPR("111111-1111").get(0).getBorrower().getCpr());
  }


  @Test void endLoanTest() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Book book = new Book(1, 1, "Title1", "Publisher1", "2020-12-12",
        "HELLO DESC", null, "Voksen", "Dansk", "321432432", 200, null ,null);

    Loan loan = new Loan(book, borrower,"2021-12-12", "2021-05-21", null, 1 );
    assertDoesNotThrow(() -> loanModel.endLoan(loan));
  }

  @Test void endLoanUpdatesNumberOfActiveLoans() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Book book = new Book(1, 1, "Title1", "Publisher1", "2020-12-12",
        "HELLO DESC", null, "Voksen", "Dansk", "321432432", 200, null ,null);

    Loan loan = new Loan(book, borrower,"2021-12-12", "2021-05-21", null, 1 );
    //1 Loan is registered to the CPR in the Database
    assertEquals(1, loanModel.getAllLoansByCPR(borrower.getCpr()).size());
    loanModel.endLoan(loan);
    //No loans left after ending
    assertThrows(NoSuchElementException.class, ()->loanModel.getAllLoansByCPR(
        borrower.getCpr()));
  }

  @Test void extendLoanExtendsDeadlineBy1Month() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(1).toString(), LocalDate.now().toString(), null, 1, new NewLoanState());
    Loan extendedLoan = loanModel.extendLoan(loan);
    assertEquals(LocalDate.now().plusDays(1).plusMonths(1).toString(), extendedLoan.getDeadline());
  }

  @Test void extendLoanOnExtendedLoan2StateThrowsIllegalStateException()
      throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(1).toString(), LocalDate.now().toString(), null, 1, new ExtendedLoan2State());
    assertThrows(IllegalStateException.class, ()->loanModel.extendLoan(loan));
  }

  @Test void extendLoanOnExtendedLoan1StateDoesNotThrow() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(1).toString(), LocalDate.now().toString(), null, 1, new ExtendedLoan1State());
    assertDoesNotThrow(()->loanModel.extendLoan(loan));
  }

  @Test void ExtendLoan7DaysFromDeadlineDoesNotThrow() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(7).toString(), LocalDate.now().toString(), null, 1, new ExtendedLoan1State());
    assertDoesNotThrow(()->loanModel.extendLoan(loan));
  }

  @Test void ExtendLoan8DaysFromDeadlineThrowsIllegalStateException()
      throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(8).toString(), LocalDate.now().toString(), null, 1, new ExtendedLoan1State());
    assertThrows(IllegalStateException.class, ()->loanModel.extendLoan(loan));
  }

  @Test void ExtendLoan6DaysFromDeadlineDoesNotThrow() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(6).toString(), LocalDate.now().toString(), null, 1, new ExtendedLoan1State());
    assertDoesNotThrow(()->loanModel.extendLoan(loan));
  }

}