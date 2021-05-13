package database.place;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PlaceImplTest
{
  @Test void createPlace()
  {
    assertDoesNotThrow(()-> PlaceImpl.getInstance().create(1, "d", "ggg", "dd"));
  }

  @Test void  getID() throws SQLException
  {
    assertEquals(1, PlaceImpl.getInstance().getPlaceID(1, "d", "ggg", "dd"));
  }
}