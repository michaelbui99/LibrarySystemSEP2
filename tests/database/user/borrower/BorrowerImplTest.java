package database.user.borrower;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BorrowerImplTest
{

  @Test void loginTest() throws SQLException
  {
    assertTrue(BorrowerImpl.getInstance().loginBorrower("111111-1111", "password"));
  }

  @Test void cprAlreadyEsistsTest() throws SQLException
  {
    assertTrue(BorrowerImpl.getInstance().borrowerCprNumberAlreadyExists("111111-1111"));
  }

  @Test void emailAlreadyExistsTest() throws SQLException
  {
    assertTrue(BorrowerImpl.getInstance().borrowerEmailAlreadyExists("Test@test"));
  }

  @Test void phoneNumberAlreadyExistsTest() throws SQLException
  {
    assertTrue(BorrowerImpl.getInstance().borrowerPhoneNumberAlreadyExists("+4511111111"));
  }

  @Test void borrowerAlreadyExistsTest() throws SQLException
  {
    assertTrue(BorrowerImpl.getInstance().borrowerAlreadyExists("111111-1111", "Test@test", "+4511111111"));
  }

}