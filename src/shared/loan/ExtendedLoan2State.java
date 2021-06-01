package shared.loan;

import java.io.Serializable;

/**
 * Extend loan second state
 *
 * @author Michael
 * @version 1.0
 */
public class ExtendedLoan2State implements LoanState, Serializable
{

  @Override public void extendLoan(Loan loan)
  {
    throw new IllegalStateException(
        "Lånet kan ikke forlænges, lånet er allerede blevet forlænget det maksimale antal gange");
  }
}
