package server.model.loan;

import database.DatabaseBuilder;
import database.loan.LoanDAOImpl;
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
  private Borrower borrower;
  private Book book;
  public static Loan extendedLoan;
  @BeforeEach void setup()
  {
  loanModel = new LoanModelManagerServer();
    databaseBuilder = new DatabaseBuilder();
    borrower = new Borrower("111111-1111", "Michael", "Bui",
        "michael@gmail.com", "+4512345678", null, "password");
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

  @Test void registerLoanMaterialNotRegisteredThrowsNoSuchElementExceptionTest() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    Book notRegisteredBook = new Book(10, 10, "Test", "TEST", "2020-12-12", "DESC",
        "Fantasy", "Voksen", "Dansk", "12321321", 200, null, null);
    assertThrows(NoSuchElementException.class,
        () -> loanModel.registerLoan(notRegisteredBook, borrower));
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

  @Test void registerLoanTest()
  {
    assertDoesNotThrow(()->{loanModel.registerLoan(book,borrower);});
    assertEquals(1, loanModel.getAllLoansByCPR(borrower.getCpr()).size());
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



}