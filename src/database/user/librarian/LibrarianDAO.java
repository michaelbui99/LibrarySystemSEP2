package database.user.librarian;

import client.model.user.librarian.Librarian;
import client.model.loan.Address;

import java.sql.SQLException;

public interface LibrarianDAO
{
  /**
   * Create a new Librarian into the system.
   *
   * @param employee_no Employee number.
   * @param firstName   the librarian's first name.
   * @param lastName    the librarian's last name.
   * @param cpr         the librarian's cpr number.
   * @param tlfNumber   the librarian's phone number.
   * @param email       the librarian's email.
   * @param address     object type Address which includes the address info of the librarian.
   * @param password    the librarian's password.
   * @throws SQLException if a librarian can't be added to the database, or the
   *                      system can't establish connection to the database.
   */
  Librarian create(int employee_no, String firstName, String lastName,
      String cpr, String tlfNumber, String email, Address address,
      String password) throws SQLException;

  /**
   * Controls access to the system for use type Librarian
   *
   * @param employee_no And
   * @param password    matches the employee number and password of an existed librarian.
   * @return true if:
   * @throws SQLException if the system can't retrieve info from the database or
   *                      or establish a connection.
   */
  boolean librarianLogin(int employee_no, String password) throws SQLException;
}
