package database.loan;

import client.model.loan.Loan;
import client.model.material.Material;
import client.model.user.Borrower;

import java.util.List;

public interface LoanDAO
{
  Loan create(Material material, Borrower borrower, String deadline,
      String loanDate);
  List<Loan> getAllLoansByCPR(String cpr);
}
