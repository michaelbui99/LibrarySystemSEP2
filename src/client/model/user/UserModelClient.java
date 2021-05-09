package client.model.user;

import client.model.loan.Address;
import shared.PropertyChangeSubject;

import java.sql.SQLException;

public interface UserModelClient extends PropertyChangeSubject
{
  Borrower registerBorrower(String cpr_no, String f_name, String l_name,
      String email, String tel_no, Address address_id, String password) throws
      SQLException;
  boolean Login(String cprNo, String password) throws SQLException;
  Borrower getLogInUser();
}
