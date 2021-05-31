package shared.loan;

import java.io.Serializable;

//Michael
public interface LoanState extends Serializable
{

  void extendLoan(Loan loan);
}
