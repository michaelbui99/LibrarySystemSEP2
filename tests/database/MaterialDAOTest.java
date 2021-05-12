package database;

import database.material.BookDAO;
import database.material.BookDAOImpl;
import database.material.MaterialDAO;
import database.material.MaterialDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MaterialDAOTest
{
  MaterialDAO materialDAO;
  BookDAO bookDAO;

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

  @Test
  void testGetAllBooks() throws SQLException
  {
    bookDAO = new BookDAOImpl();
      bookDAO.create(2,"wtf",9001,69,420);
      bookDAO.create(3,"WTF",9001,69,42069);
    assertEquals(2, materialDAO.getAllBooks().size());
  }

  @Test
  void testGetAllEBooks() throws SQLException
  {

  }

  @Test
  void testGetAllCDs() throws SQLException
  {

  }

  @Test
  void testGetAllDVDs() throws SQLException
  {

  }

  @Test
  void testGetAllAudioBooks() throws SQLException
  {

  }

}