package shared.loan;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExtendedLoan1State implements LoanState, Serializable
{
  @Override public void extendLoan(Loan loan)
  {
    if (loan.materialHasReservation())
    {
      throw new IllegalStateException("Lånet kan ikke forlænges");
    }
    else
    {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate deadlineDate = LocalDate.parse(loan.getDeadline(), formatter);
      loan.setLoanState(new ExtendedLoan2State());
      loan.setDeadline(deadlineDate.plusMonths(1).toString());
    }
  }
}
