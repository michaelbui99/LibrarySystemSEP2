package client.model.loan.loanstates;

import client.model.loan.Loan;

public class NewLoanState implements LoanState
{


  @Override public void extendLoan(Loan loan)
  {
    loan.setLoanState(new ExtendedLoan1State());
  }
}
