package client.model.loan;

import client.model.material.Material;
import client.model.user.borrower.Borrower;
import shared.PropertyChangeSubject;

import java.util.List;
import java.util.NoSuchElementException;

public interface LoanModelClient extends PropertyChangeSubject
{
  /**
   * Registers a new Loan for the given material and loaner.
   *
   * @param material  material is the Material the loaner wants to loan.
   * @param borrower borrower is the owner of the loan which the material is connected to.
   * @param deadline  deadline is the deadline for when the material must be returned to the library.
   * @throws IllegalStateException if the material is is not available for loan.
   * @throws NoSuchElementException if the material is not registered in the system.
   */
  void registerLoan(Material material, Borrower borrower, String deadline)
      throws IllegalStateException, NoSuchElementException;

  List<Loan> getAllLoansByCPR(String cpr);
  void deliverMaterial(int loanID);
  void extendLoan();
}
