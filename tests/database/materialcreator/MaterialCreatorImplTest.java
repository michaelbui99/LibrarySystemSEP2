package database.materialcreator;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MaterialCreatorImplTest
{
  @Test void addPlace()
  {
    assertDoesNotThrow(()-> MaterialCreatorImpl.getInstance().create("Kutaiba", "Kashmar", "1994-06-18", "Syria"));
  }

  @Test void getCreatorId() throws SQLException
  {
    assertEquals(2, MaterialCreatorImpl.getInstance().getCreatorId("Kutaiba", "Kashmar", "1994-06-18", "Syria"));
  }
}