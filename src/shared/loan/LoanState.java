package shared.loan;

import java.io.Serializable;

/**
 * Loan state pattern interface
 *
 * @author Michael
 * @version 1.0
 */
public interface LoanState extends Serializable
{

  /**
   * Extends the loan deadline based on a a given loan
   *
   * @param loan The targeted loan
   */
  void extendLoan(Loan loan);
}
