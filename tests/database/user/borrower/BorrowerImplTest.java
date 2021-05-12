package database.user.borrower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BorrowerImplTest
{

  @Test void loginTest() throws SQLException
  {
    assertTrue(BorrowerImpl.getInstance().loginBorrower("111111-1111", "password"));
  }

}