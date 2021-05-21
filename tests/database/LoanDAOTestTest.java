package database;

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
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LoanDAOTestTest
{
  LoanDAO loanDAO;
  DatabaseBuilder databaseBuilder;

  @BeforeEach void setup() throws SQLException
  {
    loanDAO = new LoanDAOImpl();
    databaseBuilder = new DatabaseBuilder();
  }

  @Test void getAllLoansByCprTest() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithLoan();
    List<Loan> loans = loanDAO.getAllLoansByCPR("111111-1111");
    assertEquals(1, loans.size());
    assertEquals("Title1", loans.get(0).getMaterial().getTitle());
    assertEquals(1, loans.get(0).getLoanID());
  }

  @Test void createLoanMaterialNotRegisteredTest() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    Book book = new Book(10, 10, "Test", "TEST", "2020-12-12", "DESC",
        "Fantasy", "Voksen", "Dansk", "12321321", 200, null, null);
    Borrower borrower = new Borrower("111111-1111", "Michael", "Bui",
        "michael@gmail.com", "+4512345678", null, "password");
    assertThrows(NoSuchElementException.class, () ->loanDAO.create(book, borrower, "2020-12-12", "2020-12-12"));
  }

}