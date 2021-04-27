package client.network;

import client.model.material.Material;
import shared.PropertyChangeSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote, PropertyChangeSubject
{
  void startClient() throws RemoteException;

  /**
   * Registers a new Loan for the given material and loaner.
   * @param material material is the Material the loaner wants to loan.
   * @param loanerCPR loanerCPR is the CPR which the material will be bound to in the system for the given copy of material.
   * @param deadline deadline is the deadline for when the material must be returned to the library.
   * @exception IllegalStateException if the material is is not available for loan.
   * */
  void registerLoan(Material material, String loanerCPR, String deadline) throws RemoteException;
  void registerBook(int copyNumber, String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      int placeID);
}
