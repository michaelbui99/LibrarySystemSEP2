package shared.loan;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * New loan state
 *
 * @author Michael
 * @version 1.0
 */
public class NewLoanState implements LoanState
{

  @Override public void extendLoan(Loan loan)
  {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate today = LocalDate.now();
    LocalDate deadlineDate = LocalDate.parse(loan.getDeadline(), formatter);
    //Loans can at earliest be extended when the current day is 7 days before deadline.
    if (today.isBefore(deadlineDate) && today
        .isAfter(deadlineDate.minusDays(8)))
    {
      if (loan.materialHasReservation())
      {
        throw new IllegalStateException("Lånet kan ikke forlænges");
      }
      else
      {
        loan.setLoanState(new ExtendedLoan1State());
        loan.setDeadline(deadlineDate.plusMonths(1).toString());
      }
    }
    else
      throw new IllegalStateException(
          "Lånet kan tidligst blive forlænget 7 dage inden afleveringsfristen");
  }
}
