package server.model.loan;

import client.model.loan.Loan;
import client.model.material.Material;
import client.model.user.Borrower;

import java.util.List;

public interface LoanModelServer
{
   public Loan registerLoan(Material material, Borrower borrower,
      String deadline);
   List<Loan> getAllLoansByCPR(String cpr);
}
