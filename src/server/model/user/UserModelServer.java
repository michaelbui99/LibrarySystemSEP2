package server.model.user;

import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;
import shared.servers.PropertyChangeSubject;

import java.util.NoSuchElementException;

public interface UserModelServer extends PropertyChangeSubject
{
  /**
   * Registers a new Borrower into the system.
   *
   * @param
   * @param cpr       the cpr number of the Borrower which will be used to identify
   *                  the Borrower and to login in the system.
   * @param firstName the Borrower's first name.
   * @param lastName  the Borrower's last name.
   * @param email     the Borrower's email.
   * @param tlfNumber the Borrower's phone number.
   * @param address   object type Address which includes the address info of the borrower.
   * @param password  the Borrower's password, used also to login the system.
   */
  Borrower create(String cpr, String firstName, String lastName, String email,
      String tlfNumber, Address address, String password);

  /**
   * Controls access to the system for use type Borrower
   *
   * @param cprNo    And
   * @param password matches the cpr number and password of an existed borrower
   * @return true if:
   */
  boolean logInBorrower(String cprNo, String password);

  /**
   * @return the currently logged in Borrower in the system.
   */
  Borrower getLoginBorrower();

  /**
   * Registers a new Librarian into the system.
   *
   * @param
   * @param employee_no Employee number used to identify a user of type Librarian in the system.
   *                    The employee number is given externally to the user. It used to login the system.
   * @param firstName   the librarian's first name.
   * @param lastName    the librarian's last name.
   * @param cpr         the librarian's cpr number.
   * @param tlfNumber   the librarian's phone number.
   * @param email       the librarian's email.
   * @param address     object type Address which includes the address info of the librarian.
   * @param password    the librarian's password, used also to login the system.
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

  Borrower getBorrowerByCPR(String cpr) throws NoSuchElementException;

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

}
