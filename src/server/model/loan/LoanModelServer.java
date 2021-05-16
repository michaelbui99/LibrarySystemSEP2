package server.model.loan;

import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.servers.PropertyChangeSubject;

import java.util.List;

public interface LoanModelServer extends PropertyChangeSubject
{
   public void registerLoan(Material material, Borrower borrower);
   public void registerReservation(Material material, Borrower borrower);
   List<Loan> getAllLoansByCPR(String cpr);
   void endLoan(Loan loan);

}
