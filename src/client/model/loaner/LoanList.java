package client.model.loaner;

import java.util.ArrayList;
import java.util.List;

public class LoanList
{
  List<Loan> loans;

  public LoanList()
  {
    loans = new ArrayList<>();
  }

  public void addLoan(Loan loan)
  {
    loans.add(loan);
  }


}
