package server.model.user;

import client.model.loan.Address;
import client.model.user.borrower.Borrower;
import client.model.user.librarian.Librarian;
import shared.PropertyChangeSubject;

import java.sql.SQLException;

public interface UserModelServer extends PropertyChangeSubject
{
  Borrower create(String cpr, String firstName, String lastName, String email,
      String tlfNumber, Address address, String password);
  boolean logInBorrower(String cprNo, String password);
  Borrower getLoginBorrower();

  Librarian registerLibrarian(int employee_no, String firstName, String lastName, String cpr,
      String tlfNumber, String email, Address address, String password);
  boolean librarianLogin(int employee_no, String password);
  Librarian getLoginLibrarian();
}
