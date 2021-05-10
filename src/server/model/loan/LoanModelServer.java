package server.model.loan;

import client.model.loan.Loan;
import client.model.material.Material;
import client.model.user.borrower.Borrower;
import shared.PropertyChangeSubject;

import java.util.List;

public interface LoanModelServer extends PropertyChangeSubject
{
   public void registerLoan(Material material, Borrower borrower,
      String deadline);
   List<Loan> getAllLoansByCPR(String cpr);
}
