package client.model.loan.loanstates;

import shared.loan.Loan;

import java.io.Serializable;

public class NewLoanState implements LoanState, Serializable
{


  @Override public void extendLoan(Loan loan)
  {
   if (loan.materialHasReservation())
   {
      throw new IllegalStateException("Lånet kan ikke forlænges");
   }
   else
   {
     loan.setLoanState(new ExtendedLoan1State());
   }
  }
}
