package database.user.borrower;

import shared.person.Address;
import shared.person.borrower.Borrower;

import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 * Borrower data access object
 *
 * @author Kutaiba
 * @author Kasper
 * @version 1.0
 */
public interface BorrowerDAO
{
  /**
   * Creates a new Borrower object.
   *
   * @param cpr       The borrower's cpr number
   * @param firstName The borrower's first name.
   * @param lastName  The borrowers last name
   * @param email     the Borrower's email.
   * @param tlfNumber the Borrower's phone number.
   * @param address   object type Address which includes the address information of the borrower.
   * @param password  the Borrower's password.
   * @return a new Borrower object
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  Borrower create(String cpr, String firstName, String lastName, String email,
      String tlfNumber, Address address, String password) throws SQLException;

  /**
   * Controls access to the system for use type Borrower
   *
   * @param cprNo    And
   * @param password matches the cpr number and password of an existed borrower.
   * @return true if:
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean loginBorrower(String cprNo, String password) throws SQLException;

  /**
   * Gets a borrower based on the given cpr-number
   *
   * @param cprNo The borrower's cpr-number
   * @throws NoSuchElementException if there is no borrower with cpr-number that matches the given argument
   * @throws SQLException           if the given parameters violates the constraints, or object type in the database
   *                                the exception is thrown also if the connection to the database is not established
   */
  Borrower getBorrower(String cprNo)
      throws SQLException, NoSuchElementException;

  /**
   * Check if the borrower that is being created is using a used cpr-number.
   *
   * @param cpr The borrower's cpr-number
   * @return true if the given cpr-number matches a cpr-number that exists in the system
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean borrowerCprNumberAlreadyExists(String cpr) throws SQLException;

  /**
   * Check if the borrower that is being created is using a used email address
   *
   * @param email The borrower's email address
   * @return true if the given email address matches an email that already exist
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean borrowerEmailAlreadyExists(String email) throws SQLException;

  /**
   * Check if the borrower that is being created is using a used phone number
   *
   * @param phone The borrower's phone number
   * @return true if the given phone number matches a phone number that already exist
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean borrowerPhoneNumberAlreadyExists(String phone) throws SQLException;

  /**
   * Check if the borrower that is being created is using a used cpr-number, email, and phone number
   *
   * @param cpr   The borrower's cpr-number
   * @param email The borrower's email address
   * @param phone The borrower's phone number
   * @return true if the give cpr number , email address or phone number matches a
   * cpr-number, email, and phone number that already exists
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean borrowerAlreadyExists(String cpr, String email, String phone)
      throws SQLException;
}
