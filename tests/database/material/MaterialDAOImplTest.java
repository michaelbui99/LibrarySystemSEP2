package database.material;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MaterialDAOImplTest
{
  @Test void addMaterial()
  {
    assertDoesNotThrow(() -> MaterialDAOImpl.getInstance()
        .create("test", "test", "2002-12-12", "test", "Barn", "Dansk", "test",
            null));
  }

  @Test void materialIsIn() throws SQLException
  {
    assertTrue(MaterialDAOImpl.getInstance().materialExistInDB(1));
  }

  @Test void getLatestCopy()
  {
    assertDoesNotThrow(()-> MaterialDAOImpl.getInstance().getLatestCopyNo(1));
  }

  @Test void getCopyNumber()
  {
    assertEquals(1, MaterialDAOImpl.getInstance().getCopyNumberForMaterial(1));
  }

  @Test void getNumberOfAvailableCopies()
  {
    assertEquals(1, MaterialDAOImpl.getInstance().getNumberOfAvailableCopies(1));
  }


}