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

/**
 * General client interface
 *
 * @author Kutaiba
 * @author Lilian
 * @author Michael
 * @author Kasper
 * @version 1.0
 */
public interface Client
{

  /**
   * Starts the client
   */
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
  void registerLoan(Material material, Borrower borrower)
      throws IllegalStateException;

  /**
   * Registers a new reservation in the system based on a given material and borrower
   *
   * @param material The material to be reserved
   * @param borrower The borrower (the owner) of the reservation
   * @throws IllegalStateException if the number of available copies of the material is greater than 0
   */
  void registerReservation(Material material, Borrower borrower)
      throws IllegalStateException;

  /**
   * Gather all of the loans of a borrower using that borrower's cpr-number
   *
   * @param cpr The borrower's cpr-number
   * @return a list of loans for the given cpr-number
   * @throws NoSuchElementException if there is no loans for the given cpr-number
   */
  List<Loan> getAllLoansByCPR(String cpr) throws NoSuchElementException;

  /**
   * Gather all of the reservation of a borrower using that borrower's cpr-number
   *
   * @param cpr The borrower's cpr-number
   * @throws NoSuchElementException if there are no reservation for the given cpr-number
   */
  List<Reservation> getAllReservationsByCPR(String cpr)
      throws NoSuchElementException;

  /**
   * Change a material copy status from not available to available by using a loan id
   *
   * @param loanID The loan id to identify the loan and the material copy
   */
  void deliverMaterial(int loanID);

  /**
   * Extend the deadline of a loaned material copy
   *
   * @param loan The loan which to be extended
   */
  void extendLoan(Loan loan);

  /**
   * Register a new abstract material to the system
   *
   * @param title          The material's title
   * @param publisher      The material's publisher
   * @param releaseDate    The material's release date
   * @param description    The material's description
   * @param targetAudience The material's target audience
   * @param language       The material's language
   * @param genre          The material's genre
   * @param url            The material's image link
   * @param keywords       The material's keywords
   * @return the material id for the registered material
   */
  int createMaterial(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, String genre,
      String url, String keywords);

  /**
   * Creates a material of type book in the system
   *
   * @param title          The book's title
   * @param publisher      The book's publisher
   * @param releaseDate    The book's release date
   * @param description    The book's description
   * @param tags           The book's tags
   * @param targetAudience The book's target audience
   * @param language       The book's language
   * @param isbn           The book's serial number (ISBN)
   * @param pageCount      The book's number of pages
   * @param place          Object type place which holds the information of the book's physical location
   * @param author         Object type MaterialCreator which holds the information of the book's author
   * @param genre          The book's genre
   * @param url            The book's image link
   * @param keywords       The book's keywords
   */
  void registerBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, Place place, MaterialCreator author,
      String genre, String url, String keywords);

  /**
   * Creates a copy of an already existing book by using the book's material id.
   *
   * @param materialID The book material id.
   */
  void createBookCopy(int materialID);

  /**
   * Deletes a book copy based on the given material id and copy number
   *
   * @param materialID The book material id
   * @param copyNo     The copy number of the book
   */
  void deleteBookCopy(int materialID, int copyNo);

  /**
   * Check if a material of type book already exists in the system
   *
   * @param title          The book's title
   * @param publisher      The book's publisher
   * @param releaseDate    The book's release date
   * @param description    The book's description
   * @param targetAudience The book's target audience
   * @param language       The book's language
   * @param isbn           The book's serial number (ISBN)
   * @param pageCount      The book's number of pages
   * @param author         The book's author
   * @param genre          The book's genre
   * @return true if the given parameters matches those of an existing book in the system
   */
  boolean bookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, String isbn,
      int pageCount, MaterialCreator author, String genre);

  /**
   * Creates a material of type DVD in the system
   *
   * @param title             The DVD's title
   * @param publisher         The DVD's publisher
   * @param releaseDate       The DVD's release date
   * @param description       The DVD's description
   * @param tags              The DVD's tags
   * @param targetAudience    The DVD's target audience
   * @param language          The DVD's language
   * @param subtitlesLanguage The DVD's subtitle language
   * @param playDuration      The DVD's play duration (length in minutes)
   * @param placeID           Object type place which holds the information of the DVD's physical location
   * @param genre             The DVD's genre
   * @param url               The DVD's image link
   */
  void registerDVD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String subtitlesLanguage, int playDuration, Place placeID, String genre,
      String url);

  /**
   * Creates a copy of an already existing DVD using the DVD's material id
   *
   * @param materialID The DVD's material id
   */
  void createDVDCopy(int materialID);

  /**
   * Delete a DVD copy based on the given material id and copy number
   *
   * @param materialID The DVD's material id
   * @param copyNo     The DVD's copy number
   */

  void deleteDVDCopy(int materialID, int copyNo);

  /**
   * Check if a material of type DVD already exists in the system
   *
   * @param title          The DVD's title
   * @param publisher      The DVD's publisher
   * @param releaseDate    The DVD's release date
   * @param description    The DVD's description
   * @param targetAudience The DVD's target audience
   * @param language       The DVD's language
   * @param playDuration   The DVD's play duration
   * @param genre          The DVD's genre
   * @return true if the given parameters matches those of an existing book in the system
   */
  boolean dvdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      String playDuration, String genre);

  /**
   * Creates a material of type CD in the system
   *
   * @param title          The CD's title
   * @param publisher      The CD's publisher
   * @param releaseDate    The CD's release date
   * @param description    The CD's description
   * @param tags           The CD's tags
   * @param targetAudience The CD's target audience
   * @param language       The CD's language
   * @param playDuration   The CD's play duration (in minutes)
   * @param place          bject type place which holds the information of the CD's physical location
   * @param genre          the CD's genre
   * @param url            The CD's image link
   */
  void registerCD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, Place place, String genre, String url);

  /**
   * Creates a copy of an already existing CD using the CD's material id
   *
   * @param materialID The CD's material id
   */
  void createCDCopy(int materialID);

  /**
   * Delete a CD copy based on the given material id and copy number
   *
   * @param materialID The CD's material id
   * @param copyNo     The CD's copy number
   */
  void deleteCDCopy(int materialID, int copyNo);

  /**
   * Check if a material of type CD already exists in the system
   *
   * @param title          The CD's title
   * @param publisher      The CD's publisher
   * @param releaseDate    The CD's release date
   * @param description    The CD's description
   * @param targetAudience The CD's target audience
   * @param language       The CD's language
   * @param playDuration   The CD's play duration
   * @param genre          The CD's genre
   * @return true if the given parameters matches those of an existing book in the system
   */
  boolean cdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      int playDuration, String genre);

  /**
   * Creates a material of type EBook in the system
   *
   * @param title          The E-book's title
   * @param publisher      The E-book's publisher
   * @param releaseDate    The E-book's release date
   * @param description    The E-book's description
   * @param tags           The E-book's tags
   * @param targetAudience The E-book's target audience
   * @param language       The E-book's language
   * @param pageCount      The E-book's number of pages
   * @param licenseNr      The E-book's license number
   * @param author         Object type MaterialCreator which holds the information of the book's author
   * @param genre          The E-book's genre
   * @param url            The E-book's image link
   */
  void registerEBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int pageCount, int licenseNr, MaterialCreator author, String genre,
      String url);

  /**
   * Creates a copy of an already existing EBook using the E-book's material id.
   *
   * @param materialID The E-book's material id
   */
  void createEBookCopy(int materialID);

  /**
   * Deletes a Ebook copy based on the given material id nad copy number
   *
   * @param materialID The E-book's material id
   * @param copyNo     The E-book's copy number
   */
  void deleteEBookCopy(int materialID, int copyNo);

  /**
   * Check if a material of type EBook already exists in the system
   *
   * @param title          The E-book's title
   * @param publisher      The E-book's publisher
   * @param releaseDate    The E-book's release date
   * @param description    The E-book's description
   * @param targetAudience The E-book's target audience
   * @param language       The E-book's language
   * @param pageCount      The E-book's number of pages
   * @param licenseNr      The E-book's license number
   * @param genre          The E-book's genre
   * @param author         The E-book's author
   * @return true if the given parameters matches those of an existing book in the system
   */
  boolean eBookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, int pageCount,
      int licenseNr, String genre, MaterialCreator author);

  /**
   * Creates a material of type AudioBook in the system
   *
   * @param title          The audiobook's title
   * @param publisher      The audiobook's publisher
   * @param releaseDate    The audiobook's release date
   * @param description    The audiobook's description
   * @param tags           The audiobook's tags
   * @param targetAudience The audiobook's target audience
   * @param language       The audiobook's language
   * @param playDuration   The audiobook's play duration (in minutes)
   * @param genre          The audiobook's genre
   * @param author         Object type MaterialCreator which holds the information of the book's author
   * @param url            The audiobook's image link
   */
  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, String genre, MaterialCreator author, String url);

  /**
   * Creates a copy of an already existing AudioBook using the audiobook's material id.
   *
   * @param materialID The audiobook's material id
   */
  void createAudioBookCopy(int materialID);

  /**
   * Deletes an Audiobook copy based on the given material id and copy number
   *
   * @param materialID The audiobook's material id
   * @param copyNo     The audiobook's copy number
   */
  void deleteAudioBookCopy(int materialID, int copyNo);

  /**
   * Check if a material of type AudioBook already exists in the system
   *
   * @param title          The audiobook's title
   * @param publisher      The audiobook's publisher
   * @param releaseDate    The audiobook's release date
   * @param description    The audiobook's description
   * @param targetAudience The audiobook's target audience
   * @param language       The audiobook's language
   * @param playDuration   The audiobook's play duration
   * @param author         The audiobook's author
   * @param genre          The audiobook's genre
   * @return true if the given parameters matches those of an existing book in the system
   */
  boolean audioBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, MaterialCreator author, String genre);

  /**
   * Search the system to locate a material.
   *
   * @param title          The material's title
   * @param language       The material's language
   * @param keywords       The material's keywords
   * @param genre          The material's genre
   * @param targetAudience The material's target audience
   * @param searchStrategy Search strategy to chose which type of material the method should locate
   * @return list of material
   */
  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience, SearchStrategy searchStrategy);

  /**
   * @return Material
   */
  Material getSelectMaterial();

  /**
   * Updates the value of the selected material
   *
   * @param material The given material
   */
  void setSelectMaterial(Material material);

  /**
   * Registers a new Borrower into the system.
   *
   * @param cpr_no   The Borrower's cpr-number
   * @param f_name   The Borrower's first name.
   * @param l_name   The Borrower's last name.
   * @param email    The Borrower's email.
   * @param tel_no   The Borrower's phone number.
   * @param address  Object type Address which includes the address info of the borrower.
   * @param password The Borrower's password
   * @return a new Borrower object
   */
  Borrower registerBorrower(String cpr_no, String f_name, String l_name,
      String email, String tel_no, Address address, String password);

  /**
   * Controls access to the system for use type Borrower
   *
   * @param cprNo    The borrower's cpr-number
   * @param password The borrower's password
   * @return true if the given cpr-number and password matches those of an existing borrower
   */
  boolean borrowerLogin(String cprNo, String password);

  /**
   * @return the currently logged in Borrower in the system.
   */
  Borrower getLoginUser();

  /**
   * Registers a new Librarian into the system.
   *
   * @param employee_no The librarians employee number
   * @param firstName   The librarian's first name.
   * @param lastName    The librarian's last name.
   * @param cpr         The librarian's cpr number.
   * @param tlfNumber   The librarian's phone number.
   * @param email       The librarian's email.
   * @param address     Object type Address which includes the address info of the librarian.
   * @param password    The librarian's password.
   * @return A new Librarian object
   */

  Librarian registerLibrarian(int employee_no, String firstName,
      String lastName, String cpr, String tlfNumber, String email,
      Address address, String password);

  /**
   * Controls access to the system for use type Librarian
   *
   * @param employee_no The librarian's employee number
   * @param password    The librarian's password
   * @return true if the given employee number and password matches those of an existing librarian
   */

  boolean librarianLogin(int employee_no, String password);

  /**
   * @return the currently logged in Librarian in the system.
   */
  Librarian getLoginLibrarian();

  /**
   * Ends the Loan by setting the returnDate of the Loan to the current date in "yyyy-MM-dd" format.
   *
   * @param loan is the Loan which is to be ended.
   */
  void endLoan(Loan loan);

  /**
   * Ends the reservation
   *
   * @param reservation The reservation to be ended
   */
  void endReservation(Reservation reservation);

  /**
   * @return The number of available copies
   */
  int numberOfAvailableCopies();

  /**
   * Get the total number of copies of a material using it's material id
   *
   * @param materialID The material id
   * @return total number of copies
   */
  int totalNumberOfCopies(int materialID);

  /**
   * Give the cpr-number for the borrower
   *
   * @param borrowerCpr The borrower's cpr-number
   */
  void setBorrowerCpr(String borrowerCpr);

  /**
   * Check if the borrower that is being created is using a used cpr-number.
   *
   * @param cpr The borrower's cpr-number
   * @return true if the given cpr-number matches a cpr-number that exists in the system
   */
  boolean borrowerCprNumberAlreadyExists(String cpr);

  /**
   * Check if the borrower that is being created is using a used email address
   *
   * @param email The borrower's email address
   * @return true if the given email address matches an email that already exist
   */
  boolean borrowerEmailAlreadyExists(String email);

  /**
   * Check if the borrower that is being created is using a used phone number
   *
   * @param phone The borrower's phone number
   * @return true if the given phone number matches a phone number that already exist
   */
  boolean borrowerPhoneNumberAlreadyExists(String phone);

  /**
   * Check if the borrower that is being created is using a used cpr-number, email, and phone number
   *
   * @param cpr   The borrower's cpr-number
   * @param email The borrower's email address
   * @param phone The borrower's phone number
   * @return true if the give cpr number , email address or phone number matches a
   * cpr-number, email, and phone number that already exists
   */
  boolean borrowerAlreadyExists(String cpr, String email, String phone);

  /**
   * Check if the librarian that is being created is using a used employee number
   *
   * @param employeeNo The librarian's employee number
   * @return true f the given employee number matches an employee number that already exists
   */
  boolean employeeNumberAlreadyExists(int employeeNo);

  /**
   * Check if the librarian that is being created is using a used cpr-number
   *
   * @param cpr The librarian's cpr-number
   * @return true if the given cpr-number matches a cpr-number that already exists
   */
  boolean librarianCprNumberAlreadyExists(String cpr);

  /**
   * Check if the librarian that is being created is using a used email address
   *
   * @param email The librarian's email address
   * @return true if the given email matches an email that already exists
   */
  boolean librarianEmailAlreadyExists(String email);

  /**
   * Check if the librarian that is being created is using a used phone number
   *
   * @param phone The librarian's phone number
   * @return true if the given phone number matches a phone number that already exists
   */
  boolean librarianPhoneNumberAlreadyExists(String phone);

  /**
   * Check if the librarian that is being created is using a used employee number, cpr-number, email, and phone number
   *
   * @param employeeNo The Librarian's employee number
   * @param cpr        The Librarian's cpr-number
   * @param email      The Librarian's email address
   * @param phone      The Librarian's phone number
   * @return true if the give employee number, cpr number , email address or phone number matches an employee number,
   * cpr-number, email, and phone number that already exists
   */
  boolean librarianAlreadyExists(int employeeNo, String cpr, String email,
      String phone);

  /**
   * Deletes a material from the system with all of its copies using the material id
   *
   * @param materialID The material id
   */
  void deleteMaterial(int materialID);

  /**
   * Locates a borrower using the borrower's cpr-number
   *
   * @param cpr The borrower's cpr-number
   * @return a Borrower object
   * @throws NoSuchElementException if there is no borrower with the given cpr-number
   */
  Borrower getBorrowerByCPR(String cpr) throws NoSuchElementException;
}

