package client.model.user;

import client.model.loan.Address;
import client.model.user.borrower.Borrower;
import client.model.user.librarian.Librarian;
import shared.PropertyChangeSubject;

import java.sql.SQLException;

public interface UserModelClient extends PropertyChangeSubject
{
  Borrower registerBorrower(String cpr_no, String f_name, String l_name,
      String email, String tel_no, Address address_id, String password);
  boolean borrowerLogin(String cprNo, String password) ;
  Borrower getLoginUser();

  Librarian registerLibrarian(int employee_no, String firstName, String lastName, String cpr,
      String tlfNumber, String email, Address address, String password);
  boolean librarianLogin(int employee_no, String password);
  Librarian getLoginLibrarian();
}
