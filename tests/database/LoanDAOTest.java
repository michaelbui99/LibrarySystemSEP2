package database;

import database.material.MaterialDAOImpl;
import shared.loan.Loan;
import database.loan.LoanDAO;
import database.loan.LoanDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.materials.reading.Book;
import shared.person.borrower.Borrower;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LoanDAOTest
{
  LoanDAO loanDAO;
  DatabaseBuilder databaseBuilder;
  Borrower borrower;
  Book book;

  @BeforeEach void setup() throws SQLException
  {
    loanDAO = new LoanDAOImpl();
    databaseBuilder = new DatabaseBuilder();
    borrower = new Borrower("111111-1111", "Michael", "Bui",
        "michael@gmail.com", "+4512345678", null, "password");
    book = new Book(1, 1, "Title1", "Publisher1", "2020-12-12", "HELLO DESC",
        null, "Voksen", "Dansk", "321432432", 200, null, null);
  }

  @Test void createLoanTest() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    assertDoesNotThrow(() -> {
      loanDAO.create(book, borrower, LocalDate.now().plusMonths(1).toString(),
          LocalDate.now().toString());
    });
    assertEquals(1, loanDAO.getAllLoansByCPR(borrower.getCpr()).size());

  }

  @Test void createLoanUpdatesAvailableNumberOfCopiesTest() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    assertEquals(1, MaterialDAOImpl.getInstance()
        .getNumberOfAvailableCopies(book.getMaterialID()));
    loanDAO.create(book, borrower, LocalDate.now().plusMonths(1).toString(),
        LocalDate.now().toString());
    assertEquals(0, MaterialDAOImpl.getInstance()
        .getNumberOfAvailableCopies(book.getMaterialID()));
  }

  @Test void getAllLoansByCPRReturnsAllLoansTest() throws SQLException
  {
    //Creates DB with 1 borrower, 1 Loan and 1 Book with 1 copy.
    databaseBuilder.createDummyDatabaseDataWithLoan();
    List<Loan> loans = loanDAO.getAllLoansByCPR("111111-1111");
    assertEquals(1, loans.size());
  }

  @Test void getAllLoansByCPRReturnsCorrectLoan() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    List<Loan> loans = loanDAO.getAllLoansByCPR("111111-1111");
    Loan loan = loans.get(0);
    assertEquals("Book", loan.getMaterial().getMaterialType());
    assertEquals(1, loan.getLoanID());
    assertEquals(1, loan.getMaterial().getMaterialID());
    assertEquals(1, loan.getMaterial().getCopyNumber());
    assertEquals("111111-1111", loan.getBorrower().getCpr());
  }

  @Test void getAllLoansByCPRNoLoansThrowsNoSuchElementException()
      throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    assertThrows(NoSuchElementException.class,
        () -> loanDAO.getAllLoansByCPR("111111-1111"));
  }

  @Test void createLoanMaterialNotRegisteredThrowsNoSuchElementExceptionTest()
      throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    Book notRegisteredBook = new Book(10, 10, "Test", "TEST", "2020-12-12",
        "DESC", "Fantasy", "Voksen", "Dansk", "12321321", 200, null, null);
    assertThrows(NoSuchElementException.class, () -> loanDAO
        .create(notRegisteredBook, borrower, "2020-12-12", "2020-12-12"));
  }

  @Test void endLoanTest() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Loan loan = new Loan(book, borrower, "2021-12-12", "2021-05-21", null, 1);
    assertDoesNotThrow(() -> loanDAO.endLoan(loan));
  }

  @Test void endLoanUpdatesAvailableNumberOfCopies() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    Loan loan = loanDAO
        .create(book, borrower, LocalDate.now().plusMonths(1).toString(),
            LocalDate.now().toString());

    assertEquals(0, MaterialDAOImpl.getInstance()
        .getNumberOfAvailableCopies(book.getMaterialID()));
    loanDAO.endLoan(loan);
    assertEquals(1, MaterialDAOImpl.getInstance()
        .getNumberOfAvailableCopies(book.getMaterialID()));
  }

  @Test void extendLoanExtendsDeadlineBy1MonthTest() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Loan loan = new Loan(book, borrower, "2021-12-12", "2021-05-21", null, 1);
    assertEquals("2022-01-12", LoanDAOImpl.getInstance().extendLoan(loan).getDeadline());
  }

  @Test void extendLoan3TimesThrowsIllegalStateException() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    Loan loan = new Loan(book, borrower, "2021-12-12", "2021-05-21", null, 1);
    assertThrows(IllegalStateException.class, () -> {
      LoanDAOImpl.getInstance().extendLoan(loan);
      LoanDAOImpl.getInstance().extendLoan(loan);
      LoanDAOImpl.getInstance().extendLoan(loan);
    });

  }

}