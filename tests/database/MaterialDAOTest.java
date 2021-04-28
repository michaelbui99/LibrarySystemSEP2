package database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MaterialDAOTest
{
  MaterialDAO materialDAO;

  @BeforeEach
  void setup()
  {
    materialDAO = new MaterialDAOImpl();
  }

  @Test
  void testCreate() throws SQLException
  {
    materialDAO.create("Hello", "Test", "2020-09-14", "TEST DESC", "Hello", "Voksen", "Dansk", "sdsd", null);
  }

  @Test
  void testMaterialExistInDB() throws SQLException
  {
    assertTrue(materialDAO.materialExistInDB(1));
    assertFalse(materialDAO.materialExistInDB(10));
  }
}