package client.network;

import client.model.loan.Address;
import client.model.loan.Loan;
import client.model.material.Material;
import client.model.material.Place;
import client.model.material.strategy.MaterialCreator;
import client.model.user.borrower.Borrower;
import client.model.user.librarian.Librarian;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.NoSuchElementException;

public interface RMIClient extends Remote
{
  void startClient() throws RemoteException;
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
  void deliverMaterial(int loanID) throws RemoteException;
  void extendLoan() throws RemoteException;

  void registerBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, Place place, MaterialCreator author, String genre,
      String url) throws RemoteException;

  void createBookCopy(int materialID) throws RemoteException;

  void registerDVD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String subtitlesLanguage, int playDuration, int placeID, String genre,
      String url) throws RemoteException;

  void createDVDCopy(int materialID) throws RemoteException;

  void registerCD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, int placeID, String genre, String url)
      throws RemoteException;

  void createCDCopy(int materialID) throws RemoteException;

  void registerEBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, String licenseNr, int authorId, String genre,
      String url) throws RemoteException;

  void createEBookCopy(int materialID) throws RemoteException;

  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, String genre, int authorId, String url) throws RemoteException;

  void createAudioBookCopy(int materialID) throws RemoteException;

  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience, String type) throws RemoteException;
  public Material getSelectMaterial() throws RemoteException;
  public void setSelectMaterial(Material selectMaterial) throws RemoteException;
  /**
   * Registers a new Borrower into the system.
   *
   * @param
   * @param cpr_no   the cpr number of the Borrower which will be used to identify
   *                 the Borrower and to login in the system.
   * @param f_name   the Borrower's first name.
   * @param l_name   the Borrower's last name.
   * @param email    the Borrower's email.
   * @param tel_no   the Borrower's phone number.
   * @param address  object type Address which includes the address info of the borrower.
   * @param password the Borrower's password, used also to login the system.
   */
  Borrower registerBorrower(String cpr_no, String f_name, String l_name,
      String email, String tel_no, Address address, String password)
      throws RemoteException;

  /**
   * Controls access to the system for use type Borrower
   *
   * @param cprNo    And
   * @param password matches the cpr number and password of an existed borrower
   * @return true if:
   */
  boolean borrowerLogin(String cprNo, String password) throws RemoteException;

  /**
   * @return the currently logged in Borrower in the system.
   */
  Borrower getLoginUser() throws RemoteException;

  /**
   * Registers a new Librarian into the system.
   *
   * @param employee_no Employee number used to identify a user of type Librarian in the system.
   *                    The employee number is given externally to the user. It used to login the system.
   * @param firstName   the librarian's first name.
   * @param lastName    the librarian's last name.
   * @param cpr         the librarian's cpr number.
   * @param tlfNumber   the librarian's phone number.
   * @param email       the librarian's email.
   * @param address     object type Address which includes the address info of the librarian.
   * @param password    the librarian's password.
   */

  Librarian registerLibrarian(int employee_no, String firstName,
      String lastName, String cpr, String tlfNumber, String email,
      Address address, String password) throws RemoteException;

  /**
   * Controls access to the system for use type Librarian
   *
   * @param employee_no And
   * @param password    matches the employee number and password of an existed borrower
   * @return true if:
   */

  boolean librarianLogin(int employee_no, String password)
      throws RemoteException;

  /**
   * @return the currently logged in Librarian in the system.
   */
  Librarian getLoginLibrarian() throws RemoteException;


  void addPropertyChangeListener(String name, PropertyChangeListener listener) throws RemoteException;
  void addPropertyChangeListener(PropertyChangeListener listener) throws RemoteException;
  void removePropertyChangeListener(String name, PropertyChangeListener listener) throws RemoteException;
  void removePropertyChangeListener(PropertyChangeListener listener) throws RemoteException;
}
