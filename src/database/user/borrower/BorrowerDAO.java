package database.user.borrower;

import client.model.loan.Address;
import client.model.user.borrower.Borrower;

import java.sql.SQLException;

public interface BorrowerDAO
{
  Borrower create(String cpr, String firstName, String lastName, String email,
      String tlfNumber, Address address, String password) throws SQLException;

  boolean logInBorrower(String cprNo, String password) throws SQLException;
}
