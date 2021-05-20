package database.loan;

import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.util.List;
import java.util.NoSuchElementException;

public interface LoanDAO
{
  Loan create(Material material, Borrower borrower, String deadline,
      String loanDate) throws IllegalStateException, NoSuchElementException;
  List<Loan> getAllLoansByCPR(String cpr) throws NoSuchElementException;
  void endLoan(Loan loan);

}
