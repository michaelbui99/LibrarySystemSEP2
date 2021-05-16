package client.model.loan.loanstates;

import shared.loan.Loan;

import java.io.Serializable;

public class NewLoanState implements LoanState, Serializable
{


  @Override public void extendLoan(Loan loan)
  {
    loan.setLoanState(new ExtendedLoan1State());
  }
}
