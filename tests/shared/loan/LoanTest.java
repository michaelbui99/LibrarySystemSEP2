package shared.loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.materials.reading.Book;
import shared.person.borrower.Borrower;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Loan test
 *
 * @author Michael
 * @version 1.0
 */
class LoanTest
{
  private Borrower borrower;
  private Book book;

  @BeforeEach void setup()
  {
    borrower = new Borrower("111111-1111", "Michael", "Bui",
        "michael@gmail.com", "+4512345678", null, "password");
    book = new Book(1, 1, "Title1", "Publisher1", "2020-12-12", "HELLO DESC",
        null, "Voksen", "Dansk", "321432432", 200, null, null);
  }

  //Constructor argument tests
  @Test void LoanConstructorTest()
  {
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(1).toString(),
        LocalDate.now().toString(), null, 1);
    assertNotEquals(null, loan);
  }

  @Test void LoanWithNoMaterialThrowsIllegalArgumentException()
  {
    assertThrows(IllegalArgumentException.class,
        () -> new Loan(null, borrower, LocalDate.now().plusMonths(1).toString(),
            LocalDate.now().toString(), null, 1));
  }

  @Test void LoanWithNoBorrowerThrowsIllegalArgumentException()
  {
    assertThrows(IllegalArgumentException.class,
        () -> new Loan(book, null, LocalDate.now().plusMonths(1).toString(),
            LocalDate.now().toString(), null, 1));
  }

  @Test void LoanWithNoDeadlineThrowsIllegalArgumentException()
  {
    assertThrows(IllegalArgumentException.class,
        () -> new Loan(book, borrower, null, LocalDate.now().toString(), null,
            1));
  }

  @Test void LoanWithNoLoanDateThrowsIllegalArgumentException()
  {
    assertThrows(IllegalArgumentException.class,
        () -> new Loan(book, borrower, LocalDate.now().plusMonths(1).toString(),
            null, null, 1));
  }

  @Test void LoanWithLoanIDL0ThrowsIllegalArgumentException()
  {
    assertThrows(IllegalArgumentException.class,
        () -> new Loan(book, borrower, LocalDate.now().plusMonths(1).toString(),
            LocalDate.now().toString(), null, 0));
  }

  @Test void LoanWithLoanIDLMinus1ThrowsIllegalArgumentException()
  {
    assertThrows(IllegalArgumentException.class,
        () -> new Loan(book, borrower, LocalDate.now().plusMonths(1).toString(),
            LocalDate.now().toString(), null, -1));
  }

  //extendLoan tests
  @Test void LoanWithNewLoanStateCannotBeExtendedWithReservation()
  {
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(1).toString(),
        LocalDate.now().toString(), null, 1);
    loan.setMaterialHasReservation(true);
    loan.setLoanState(new NewLoanState());
    assertThrows(IllegalStateException.class, loan::extendLoan);
  }

  @Test void ExtendLoanWithWithNewLoanStateNoReservationExtendsDeadlineBy1Month()
  {
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(4).toString(),
        LocalDate.now().toString(), null, 1);
    loan.setMaterialHasReservation(false);
    loan.setLoanState(new NewLoanState());
    loan.extendLoan();
    System.out.println(LocalDate.now().plusDays(4).plusMonths(1).toString());
    assertEquals(LocalDate.now().plusDays(4).plusMonths(1).toString(),
        loan.getDeadline());
  }

  @Test void LoanWithExtendedLoan1StateCannotBeExtendedWithReservation()
  {
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(1).toString(),
        LocalDate.now().toString(), null, 1);
    loan.setMaterialHasReservation(true);
    loan.setLoanState(new ExtendedLoan1State());
    assertThrows(IllegalStateException.class, loan::extendLoan);
  }

  @Test void ExtendLoanWithWithExtendedLoan1StateNoReservationExtendsDeadlineBy1Month()
  {
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(1).toString(),
        LocalDate.now().toString(), null, 1);
    loan.setMaterialHasReservation(false);
    loan.setLoanState(new ExtendedLoan1State());
    loan.extendLoan();
    assertEquals(LocalDate.now().plusDays(1).plusMonths(1).toString(),
        loan.getDeadline());
  }

  @Test void LoanWithExtendedLoan2StateCannotBeExtended()
  {
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(1).toString(),
        LocalDate.now().toString(), null, 1);
    loan.setMaterialHasReservation(false);
    loan.setLoanState(new ExtendedLoan2State());
    assertThrows(IllegalStateException.class, loan::extendLoan);
    loan.setMaterialHasReservation(true);
    assertThrows(IllegalStateException.class, loan::extendLoan);
  }

  @Test void LoanCannotBeExtendedIfDeadlineIsMoreThan7DaysAwayFromCurrentDate()
  {
    //Boundary value for the difference between today and deadline for extend loan is 7 days
    //Test for Boundary value + 1
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(8).toString(),
        LocalDate.now().toString(), null, 1);
    assertThrows(IllegalStateException.class, loan::extendLoan);
  }

  @Test void LoanCanBeExtendedIfDeadlineIs7DaysAwayFromCurrentDate()
  {
    //Boundary value for the difference between today and deadline for extend loan is 7 days
    //Test for Boundary value
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(7).toString(),
        LocalDate.now().toString(), null, 1);
    assertDoesNotThrow(loan::extendLoan);
  }

  @Test void LoanCanBeExtendedIfDeadlineIs6DaysAwayFromCurrentDate()
  {
    //Boundary value for the difference between today and deadline for extend loan is 7 days
    //Test for Boundary value -1
    Loan loan = new Loan(book, borrower, LocalDate.now().plusDays(6).toString(),
        LocalDate.now().toString(), null, 1);
    assertDoesNotThrow(loan::extendLoan);
  }

  @Test void LoanCannotBeExtended1DayPastDeadline()
  {
    Loan loan = new Loan(book, borrower,
        LocalDate.now().minusDays(1).toString(), LocalDate.now().toString(),
        null, 1);
    assertThrows(IllegalStateException.class, loan::extendLoan);
  }

  @Test void LoanCannotBeExtended1WeekPastDeadline()
  {
    Loan loan = new Loan(book, borrower,
        LocalDate.now().minusDays(7).toString(), LocalDate.now().toString(),
        null, 1);
    assertThrows(IllegalStateException.class, loan::extendLoan);
  }

}