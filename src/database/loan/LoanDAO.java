package database.loan;

import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Loan data access object
 *
 * @author Michael
 * @version 1.0
 */
public interface LoanDAO
{
  /**
   * Registers a new Loan in the system for the given Material and Borrower.
   * The Loan is bound to a specific copy of the Material unlike Reservations.
   *
   * @param material material is the Material with specific Copy Number the Borrower wants to loan.
   * @param borrower borrower is the owner of the Loan which the Material is bound to.
   * @return a new Loan object
   */
  Loan create(Material material, Borrower borrower);

  /**
   * Returns a list of all Loans that are registered to the Borrower that has the given cpr.
   *
   * @param cpr cpr is the cpr number of the Borrower for which the method must return all Loans for.
   * @return an List of all Loans registered to the cpr.
   * @throws NoSuchElementException if the borrower has no active Loans registered in the system.
   */
  List<Loan> getAllLoansByCPR(String cpr) throws NoSuchElementException;

  /**
   * Ends the Loan by setting the returnDate of the Loan to the current date in "yyyy-MM-dd" format.
   *
   * @param loan is the Loan which is to be ended.
   */
  void endLoan(Loan loan);

  /**
   * Extends the deadline of the loan by 1 month.
   * The Loan can only be extended 2 times total and cannot be extended if the Material of the Loan has any reservations.
   * Loans can at earliest be extended 7 days before deadline.
   *
   * @param loan is the Loan which is to be extended by one month.
   */
  Loan extendLoan(Loan loan);

}
