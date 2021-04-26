package shared;

import client.model.material.Material;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote
{
  void startServer() throws RemoteException, AlreadyBoundException;

  /**
   * Registers a new Loan for the given material and loaner.
   * @param material material is the Material the loaner wants to loan.
   * @param loanerCPR loanerCPR is the CPR which the material will be bound to in the system for the given copy of material.
   * @param deadline deadline is the deadline for when the material must be returned to the library.
   * */
  void registerLoan(Material material, String loanerCPR, String deadline) throws RemoteException;

  /**
  * Registers a client to the server as listener to be used for callbacks.
   * @param ccb ccb is the Clientcallback which is to be registered to the server as listener.
  * */
  void registerClientCallback(ClientCallback ccb) throws RemoteException;

  /**
   * Registers a new BookCopy in the Database and creates a new Book object which is stored in MaterialList.
   *
   * @param materialID materialID is the ID for the material the book is bound to in DB.
   *                   If there does not exist an material with the ID yet
   *                   the system will auto generate a new Material in the Database with given params.
   */
  void registerBook(int materialID, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, int placeID) throws RemoteException;

  void createBookCopy(int materialID);
}
