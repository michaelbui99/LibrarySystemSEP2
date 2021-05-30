package client.model.user;

import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;
import shared.network.PropertyChangeSubject;

import java.util.NoSuchElementException;

public interface UserModelClient extends PropertyChangeSubject
{
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
   * @return true
   */

  boolean librarianLogin(int employee_no, String password);

  /**
   * @return the currently logged in Librarian in the system.
   */
  Librarian getLoginLibrarian();

  /**
   * Give the cpr number for the borrower a value give in the
   * @param borrowerCpr .
   * */
  void setBorrowerCpr(String borrowerCpr);

  /**
   * Check if the borrower that is beeing created is using
   * the same cpr number.
   * @return true
   * @param cpr maches that of an already existing borrower.
   * */
  boolean borrowerCprNumberAlreadyExists(String cpr);

  /**
   * Check if the borrower that is beeing created is using
   * the same email address.
   * @return true
   * @param email maches that of an already existing borrower.
   * */
  boolean borrowerEmailAlreadyExists(String email);

  /**
   * Check if the borrower that is beeing created is using
   * the same cpr phone number.
   * @return true
   * @param phone maches that of an already existing borrower.
   * */
  boolean borrowerPhoneNumberAlreadyExists(String phone);

  /**
   * Check if the borrower that is beeing created is using
   * the same cpr number, email address and phone number.
   * @return true
   * @param cpr and
   * @param email and
   * @param phone maches that of an already existing borrower.
   * */
  boolean borrowerAlreadyExists(String cpr, String email, String phone);

  /**
   * Check if the librarian that is beeing created is using
   * the same employee number .
   * @return true
   * @param employeeNo maches that of an already existing librarian.
   * */
  boolean employeeNumberAlreadyExists(int employeeNo);

  /**
   * Check if the librarian that is beeing created is using
   * the same cpr number .
   * @return true
   * @param cpr maches that of an already existing librarian.
   * */
  boolean librarianCprNumberAlreadyExists(String cpr);

  /**
   * Check if the librarian that is beeing created is using
   * the same email address .
   * @return true
   * @param email maches that of an already existing librarian.
   * */
  boolean librarianEmailAlreadyExists(String email);

  /**
   * Check if the librarian that is beeing created is using
   * the same phone number .
   * @return true
   * @param phone maches that of an already existing librarian.
   * */
  boolean librarianPhoneNumberAlreadyExists(String phone);

  /**
   * Check if the librarian that is beeing created is using
   * the same employee number, cpr number, email address and phone number.
   * @return true
   * @param employeeNo and
   * @param cpr and
   * @param email and
   * @param phone maches that of an already existing librarian.
   * */
  boolean librarianAlreadyExists(int employeeNo, String cpr, String email,
      String phone);

  Borrower getBorrowerByCPR(String cpr) throws NoSuchElementException;
}
