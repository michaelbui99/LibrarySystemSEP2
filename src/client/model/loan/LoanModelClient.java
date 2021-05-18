package client.model.loan;

import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.servers.PropertyChangeSubject;

import java.util.List;
import java.util.NoSuchElementException;

public interface LoanModelClient extends PropertyChangeSubject
{
  /**
   * Registers a new Loan in the system for the given Material and Borrower.
   * The Loan is bound to a specific copy of the Material unlike Reservations.
   * @param material  material is the Material with specific Copy Number the Borrower wants to loan.
   * @param borrower borrower is the owner of the Loan which the Material is bound to.
   * @throws IllegalStateException if the material is is not available for loan.
   * @throws NoSuchElementException if the material is not registered in the system.
   */
  void registerLoan(Material material, Borrower borrower)
      throws IllegalStateException, NoSuchElementException;



  /**
  * Returns a list of all Loans that are registered to the Borrower that has the given cpr.
   * @param cpr cpr is the cpr number of the Borrower for which the method must return all Loans for.
   * @return an List of all Loans registered to the cpr.
  * */
  List<Loan> getAllLoansByCPR(String cpr);

  /**
  * Return material ends the Loan with the specific loanID by updating the Loan with the current date as returnDate.
   * @param loanID loanID of the Loan that the material was registered to.
  * */
  void returnMaterial(int loanID);

  /**
   * Ends the Loan by setting the returnDate of the Loan to the current date in "yyyy-MM-dd" format.
   * @param loan is the Loan which is to be ended.
   * */
  void endLoan(Loan loan);
  void extendLoan();
}
