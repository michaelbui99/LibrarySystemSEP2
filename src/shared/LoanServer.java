package shared;

import client.model.loan.Loan;
import client.model.material.Material;

import java.rmi.RemoteException;
import java.util.List;

public interface LoanServer
{
  /**
   * Registers a new Loan for the given material and loaner.
   * @param material material is the Material the loaner wants to loan.
   * @param loanerCPR loanerCPR is the CPR which the material will be bound to in the system for the given copy of material.
   * @param deadline deadline is the deadline for when the material must be returned to the library.
   * */
  void registerLoan(Material material, String loanerCPR, String deadline) throws
      RemoteException;
  List<Loan> getAllLoansByCPR(String cpr);
}
