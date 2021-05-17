package shared.servers;

import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserServer extends Remote
{
  /**
   * Registers a new Borrower into the system.
   *
   * @param cpr       the cpr number of the Borrower which will be used to identify
   *                  the Borrower and to login in the system.
   * @param firstName the Borrower's first name.
   * @param lastName  the Borrower's last name.
   * @param email     the Borrower's email.
   * @param tlfNumber the Borrower's phone number.
   * @param address   object type Address which includes the address info of the borrower.
   * @param password  the Borrower's password, used also to login the system.
   * @throws RemoteException .
   */
  Borrower registerBorrower(String cpr, String firstName, String lastName,
      String email, String tlfNumber, Address address, String password)
      throws RemoteException;

  /**
   * Controls access to the system for use type Borrower
   *
   * @param cprNo    And
   * @param password matches the cpr number and password of an existed borrower.
   * @return true if:
   * @throws RemoteException .
   */
  boolean Login(String cprNo, String password) throws RemoteException;

  /**
   * @return the currently logged in Borrower in the system.
   * @throws RemoteException .
   */
  Borrower getLoginBorrower() throws RemoteException;

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
   * @throws RemoteException .
   */
  Librarian registerLibrarian(int employee_no, String firstName,
      String lastName, String cpr, String tlfNumber, String email,
      Address address, String password) throws RemoteException;

  /**
   * Controls access to the system for use type Librarian
   *
   * @param employee_no And
   * @param password    matches the employee number and password of an existed borrower.
   * @return true if:
   * @throws RemoteException .
   */
  boolean librarianLogin(int employee_no, String password)
      throws RemoteException;

  /**
   * @return the currently logged in Librarian in the system.
   * @throws RemoteException .
   */
  Librarian getLoginLibrarian() throws RemoteException;
}
