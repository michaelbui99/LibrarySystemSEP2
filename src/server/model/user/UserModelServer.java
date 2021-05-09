package server.model.user;

import client.model.loan.Address;
import client.model.user.Borrower;
import shared.PropertyChangeSubject;

import java.sql.SQLException;

public interface UserModelServer extends PropertyChangeSubject
{
  Borrower create(String cpr, String firstName, String lastName, String email,
      String tlfNumber, Address address, String password) throws SQLException;
  boolean logInBorrower(String cprNo, String password) throws SQLException;
}
