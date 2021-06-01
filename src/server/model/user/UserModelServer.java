package server.model.user;

import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;
import shared.network.PropertyChangeSubject;

import java.util.NoSuchElementException;

/**
 * User model for server
 *
 * @author Kutaiba
 * @version 1.0
 */
public interface UserModelServer extends PropertyChangeSubject
{
  /**
   * Registers a new Borrower into the system.
   *
   * @param cpr       The Borrower's cpr-number
   * @param firstName The Borrower's first name.
   * @param lastName  The Borrower's last name.
   * @param email     The Borrower's email.
   * @param tlfNumber The Borrower's phone number.
   * @param address   Object type Address which includes the address info of the borrower.
   * @param password  The Borrower's password
   * @return a new Borrower object
   */
  Borrower create(String cpr, String firstName, String lastName, String email,
      String tlfNumber, Address address, String password);

  /**
   * Controls access to the system for use type Borrower
   *
   * @param cprNo    The borrower's cpr-number
   * @param password The borrower's password
   * @return true if the given cpr-number and password matches those of an existing borrower
   */
  boolean logInBorrower(String cprNo, String password);

  /**
   * @return the currently logged in Borrower in the system.
   */
  Borrower getLoginBorrower();

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
   * Locates a borrower using the borrower's cpr-number
   *
   * @param cpr The borrower's cpr-number
   * @return a Borrower object
   * @throws NoSuchElementException if there is no borrower with the given cpr-number
   */
  Borrower getBorrowerByCPR(String cpr) throws NoSuchElementException;

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

}
