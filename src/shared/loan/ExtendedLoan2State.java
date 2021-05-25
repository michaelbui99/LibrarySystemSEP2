package shared.loan;

import java.io.Serializable;

public class ExtendedLoan2State implements LoanState, Serializable
{


  @Override public void extendLoan(Loan loan)
  {
    throw new IllegalStateException("Lånet kan ikke forlænges");
  }
}
