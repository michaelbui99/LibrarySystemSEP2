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
   *
   *
   */
  void registerBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, int placeID, int authorId, String genre,
      String url) throws RemoteException;

  void createBookCopy(int materialID) throws RemoteException;

  void registerDVD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage, String playDuration, int placeID, String genre,
      String url) throws RemoteException;

  void createDVDCopy(int materialID) throws RemoteException;

  void registerCD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID, String genre,
      String url) throws RemoteException;

  void createCDCopy(int materialID) throws RemoteException;

  void registerEBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, String licenseNr, int authorId, String genre,
      String url) throws RemoteException;

  void createEBookCopy(int materialID) throws RemoteException;


  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      double playDuration, String genre, int authorId, String url) throws RemoteException;

  void createAudioBookCopy(int materialID) throws RemoteException;


}
