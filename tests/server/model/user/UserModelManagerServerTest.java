package server.model.user;

import shared.person.Address;
import shared.person.borrower.Borrower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserModelManagerServerTest
{
  private UserModelServer userModelServer;
  private Borrower borrower;

  @BeforeEach public void setUp()
  {
    userModelServer = new UserModelManagerServer();
    borrower = new Borrower("111111-1111", "FirstNameTest", "LastNameTest",
        "test@test.domain", "+4511111111",
        new Address("CityTest", "StreetNameTest", 1, "1111"), "PasswordTest");
  }

  @Test public void registerBorrowerTest() throws SQLException
  {
    userModelServer.create("111111-1111", "FirstNameTest", "LastNameTest",
        "test@test.domain", "+4511111111",
        new Address("CityTest", "StreetNameTest", 1, "1111"), "PasswordTest");
  }

  @Test public void registerBorrowerWithoutCPR() throws SQLException
  {
    assertThrows(NullPointerException.class, ()-> userModelServer.create(null,"FirstNameTest", "LastNameTest",
        "test@test.domain", "+4511111111",
        new Address("CityTest", "StreetNameTest", 1, "1111"), "PasswordTest"));
  }

  @Test public void registerBorrowerWithoutFirstName() throws SQLException
  {
    assertThrows(NullPointerException.class, ()-> userModelServer.create("111111-1111",null, "LastNameTest",
        "test@test.domain", "+4511111111",
        new Address("CityTest", "StreetNameTest", 1, "1111"), "PasswordTest"));
  }
  @Test public void registerBorrowerWithoutLastName() throws SQLException
  {
    assertThrows(NullPointerException.class, ()-> userModelServer.create(null,"FirstNameTest", null,
        "test@test.domain", "+4511111111",
        new Address("CityTest", "StreetNameTest", 1, "1111"), "PasswordTest"));
  }
  @Test public void registerBorrowerWithoutEmail() throws SQLException
  {
    assertThrows(NullPointerException.class, ()-> userModelServer.create(null,"FirstNameTest", "LastNameTest",
        null, "+4511111111",
        new Address("CityTest", "StreetNameTest", 1, "1111"), "PasswordTest"));
  }
  @Test public void registerBorrowerWithoutAddress() throws SQLException
  {
    assertThrows(NullPointerException.class, ()-> userModelServer.create(null,"FirstNameTest", "LastNameTest",
        "test@test.domain", "+4511111111",
       null , "PasswordTest"));
  }

  @Test public void registerBorrowerWithExistedCPR() throws SQLException
  {
    userModelServer.create("111111-1111", "FirstNameTest", "LastNameTest",
        "test@test.domain", "+4511111111",
        new Address("CityTest", "StreetNameTest", 1, "1111"), "PasswordTest");

  }

  @Test public void userLoginTrue() throws SQLException
  {
    borrower = new Borrower("111111-1111", "FirstNameTest", "LastNameTest",
        "test@test.domain", "+4511111111",
        new Address("CityTest", "StreetNameTest", 1, "1111"), "PasswordTest");
    assertTrue(userModelServer.logInBorrower("111111-1111", "PasswordTest"));
  }

  @Test public void userLoginFalseCPR() throws SQLException
  {
    assertFalse(userModelServer.logInBorrower("222222-2222", "PasswordTest"));
  }

  @Test public void userLoginFalsePassword() throws SQLException
  {
    assertFalse(userModelServer.logInBorrower("111111-1111", "testFalse"));
  }

  @Test public void userLoginFalseBothArg() throws SQLException
  {
    assertFalse(userModelServer.logInBorrower("222222-2222", "testFalse"));
  }


}