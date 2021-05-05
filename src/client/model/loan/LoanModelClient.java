package client.model.loan;

import client.model.material.Material;

public interface LoanModelClient
{
  /**
   * Registers a new Loan for the given material and loaner.
   *
   * @param material  material is the Material the loaner wants to loan.
   * @param loanerCPR loanerCPR is the CPR which the material will be bound to in the system for the given copy of material.
   * @param deadline  deadline is the deadline for when the material must be returned to the library.
   * @throws IllegalStateException if the material is is not available for loan.
   */
  Loan registerLoan(Material material, String loanerCPR, String deadline)
      throws IllegalStateException;

  void deliverMaterial(int loanNo);
  void extendLoan();
}
