package database;

import client.model.material.reading.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BookCopyDAOTest
{
  private BookCopyDAO bookCopyDAO;
  private int i;

  @BeforeEach
  void setup() throws SQLException
  {
    bookCopyDAO = new BookCopyDAOImpl();
    i = MaterialDAOImpl.getInstance().create("Hello", "Test", "2020-09-14", "TEST DESC", "Hello", "Voksen", "Dansk");
    MaterialCopyDAOImpl.getInstance().create(i, 1);
  }

  @Test void testCreate() throws SQLException
  {
    Book book = bookCopyDAO.create(i, 1);
    assertEquals("Hello", book.getTitle());
  }

}