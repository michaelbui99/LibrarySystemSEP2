package client.network;

import shared.materials.strategy.SearchStrategy;
import shared.reservation.Reservation;
import shared.person.Address;
import shared.loan.Loan;
import shared.materials.Material;
import shared.materials.Place;
import shared.person.MaterialCreator;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;

import java.util.List;
import java.util.NoSuchElementException;

public interface Client
{
  void startClient();
  /**
   * Registers a new Loan in the system for the given Material and Borrower.
   * The Loan is bound to a specific copy of the Material unlike Reservations.
   *
   * @param material material is the Material with specific Copy Number the Borrower wants to loan.
   * @param borrower borrower is the owner of the Loan which the Material is bound to.
   * @throws IllegalStateException if the material is is not available for loan because the number of available copies
   *                               or the material has reservations and the borrower is NOT the next person to loan the copy
   *                               or the material has reservation and the borrower IS the next person to loan the copy,
   *                               but the reservation is not ready.
   */
  public void registerLoan(Material material, Borrower borrower)
      throws IllegalStateException;
  public void registerReservation(Material material, Borrower borrower)
      throws IllegalStateException;

  List<Loan> getAllLoansByCPR(String cpr) throws NoSuchElementException;
  List<Reservation> getAllReservationsByCPR(String cpr)
      throws NoSuchElementException;
  void deliverMaterial(int loanID);
  void extendLoan(Loan loan);

  int createMaterial(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, String genre,
      String url, String keywords);

  void registerBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, Place place, MaterialCreator author,
      String genre, String url, String keywords);

  void createBookCopy(int materialID);

  void deleteBookCopy(int materialID, int copyNo);

  boolean bookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, String isbn,
      int pageCount, MaterialCreator author, String genre);

  void registerDVD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String subtitlesLanguage, int playDuration, Place placeID, String genre,
      String url);

  void createDVDCopy(int materialID);

  void deleteDVDCopy(int materialID, int copyNo);

  boolean dvdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      String playDuration, String genre);

  void registerCD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, Place place, String genre, String url);

  void createCDCopy(int materialID);

  void deleteCDCopy(int materialID, int copyNo);

  boolean cdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      int playDuration, String genre);

  void registerEBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int pageCount, int licenseNr, MaterialCreator author, String genre,
      String url);

  void createEBookCopy(int materialID);

  void deleteEBookCopy(int materialID, int copyNo);

  boolean eBookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, int pageCount,
      int licenseNr, String genre, MaterialCreator author);

  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, String genre, MaterialCreator author, String url);

  void createAudioBookCopy(int materialID);

  void deleteAudioBookCopy(int materialID, int copyNo);

  boolean audioBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, MaterialCreator author, String genre);

  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience, SearchStrategy searchStrategy);
  public Material getSelectMaterial();
  public void setSelectMaterial(Material material);
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
      String email, String tel_no, Address address, String password);

  /**
   * Controls access to the system for use type Borrower
   *
   * @param cprNo    And
   * @param password matches the cpr number and password of an existed borrower
   * @return true if:
   */
  boolean borrowerLogin(String cprNo, String password);

  /**
   * @return the currently logged in Borrower in the system.
   */
  Borrower getLoginUser();

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
      Address address, String password);

  /**
   * Controls access to the system for use type Librarian
   *
   * @param employee_no And
   * @param password    matches the employee number and password of an existed borrower
   * @return true if:
   */

  boolean librarianLogin(int employee_no, String password);

  /**
   * @return the currently logged in Librarian in the system.
   */
  Librarian getLoginLibrarian();

  void endLoan(Loan loan);

  void endReservation(Reservation reservation);

  int numberOfAvailableCopies();

  int totalNumberOfCopies(int materialID);

  void setBorrowerCpr(String borrowerCpr);

  boolean borrowerCprNumberAlreadyExists(String cpr);

  boolean borrowerEmailAlreadyExists(String email);

  boolean borrowerPhoneNumberAlreadyExists(String phone);

  boolean borrowerAlreadyExists(String cpr, String email, String phone);

  boolean employeeNumberAlreadyExists(int employeeNo);

  boolean librarianCprNumberAlreadyExists(String cpr);

  boolean librarianEmailAlreadyExists(String email);

  boolean librarianPhoneNumberAlreadyExists(String phone);

  boolean librarianAlreadyExists(int employeeNo, String cpr, String email,
      String phone);

  void deleteMaterial(int materialID);

  Borrower getBorrowerByCPR(String cpr) throws NoSuchElementException;
}

