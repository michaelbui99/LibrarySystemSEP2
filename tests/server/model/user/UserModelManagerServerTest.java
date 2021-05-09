package server.model.user;

import client.model.loan.Address;
import client.model.user.Borrower;
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
        new Address("CityTest", "StreetNameTest", 1, 1111), "PasswordTest");
  }

  @Test public void registerBorrowerTest() throws SQLException
  {
    userModelServer.create("111111-1111", "FirstNameTest", "LastNameTest",
        "test@test.domain", "+4511111111",
        new Address("CityTest", "StreetNameTest", 1, 1111), "PasswordTest");
  }

}