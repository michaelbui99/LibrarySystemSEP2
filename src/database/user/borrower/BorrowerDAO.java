package database.user.borrower;

import shared.person.Address;
import shared.person.borrower.Borrower;

import java.sql.SQLException;

public interface BorrowerDAO
{
  /**
   * Creates a new Borrower object.
   *
   * @param cpr       the cpr number of the Borrower.
   * @param firstName the Borrower's first name.
   * @param lastName  the Borrower's last name.
   * @param email     the Borrower's email.
   * @param tlfNumber the Borrower's phone number.
   * @param address   object type Address which includes the address info of the borrower.
   * @param password  the Borrower's password.
   * @throws SQLException if a borrower can't be added to the database, or the
   *                      system can't establish connection to the database.
   */
  Borrower create(String cpr, String firstName, String lastName, String email,
      String tlfNumber, Address address, String password) throws SQLException;

  /**
   * Controls access to the system for use type Borrower
   *
   * @param cprNo    And
   * @param password matches the cpr number and password of an existed borrower.
   * @return true if:
   * @throws SQLException if the system can't retrieve info from the database or
   *                      or establish a connection.
   */
  boolean loginBorrower(String cprNo, String password) throws SQLException;

  Borrower getBorrower(String sprNo) throws SQLException;
}
