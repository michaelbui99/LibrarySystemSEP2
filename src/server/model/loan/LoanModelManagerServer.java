package server.model.loan;

import client.model.loan.Loan;
import client.model.material.Material;
import client.model.user.Borrower;
import database.loan.LoanDAOImpl;

import java.util.List;

public class LoanModelManagerServer implements LoanModelServer
{

  @Override public Loan registerLoan(Material material, Borrower borrower,
      String deadline)
  {
    return null;
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    return LoanDAOImpl.getInstance().getAllLoansByCPR(cpr);
  }
}
