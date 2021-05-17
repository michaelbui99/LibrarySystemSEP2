package shared.servers;

import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;

public interface LoanServer extends Remote
{
  /**
   * Registers a new Loan for the given material and loaner.
   *
   * @param material  material is the Material the loaner wants to loan.
   * @param borrower borrower is the owner of the loan which the material is connected to.
   * @throws IllegalStateException if the material is is not available for loan.
   * @throws NoSuchElementException if the material is not registered in the system.
   */
  public void registerLoan(Material material, Borrower borrower) throws IllegalStateException, RemoteException;
  List<Loan> getAllLoansByCPR(String cpr) throws RemoteException;
  void registerReservation(Material material, Borrower borrower) throws RemoteException;
  void endLoan(Loan loan) throws RemoteException;
  void registerClientCallBack(ClientCallback client) throws RemoteException;

}
