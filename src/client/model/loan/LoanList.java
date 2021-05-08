package client.model.loan;

import java.util.ArrayList;
import java.util.List;

public class LoanList
{
  private List<Loan> loans;

  public LoanList()
  {
    loans = new ArrayList<>();
  }

  public void addLoan(Loan loan)
  {
    loans.add(loan);
  }

  public List<Loan> getAllLoans()
  {
    return loans;
  }

  public Loan getLoanByID(int loanID)
  {
    for (Loan loan : loans)
    {
      if (loan.getLoanID() == loanID)
      {
        return loan;
      }
    }
    return null;
  }

}
