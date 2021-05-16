package database.loan;

import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.util.List;

public interface LoanDAO
{
  Loan create(Material material, Borrower borrower, String deadline,
      String loanDate);
  List<Loan> getAllLoansByCPR(String cpr);
  void endLoan(Loan loan);

}
