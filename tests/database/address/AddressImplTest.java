package database.address;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AddressImplTest
{

  @Test void getID() throws SQLException
  {
    assertEquals(-1, AddressImpl.getInstence().getAddressId("sad", "sad", 5597, 788));
  }

  @Test void createAddress()
  {
    assertDoesNotThrow(()-> AddressImpl.getInstence().create("sad", "asd", 2222, 5));
  }
}