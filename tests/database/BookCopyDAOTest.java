package database;

import client.model.material.Material;
import client.model.material.reading.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BookCopyDAOTest
{
  private BookCopyDAO bookCopyDAO;
  private Book i;
  @BeforeEach
  void setup() throws SQLException
  {
    bookCopyDAO = new BookCopyDAOImpl();
    i = new Book(1,1,"Hello","Jorden Pederson","1998-02-21","12 Rules of life",
        "psychology","Voksen","Dansk","321321-TEST",300);

  }

  @Test void testCreate() throws SQLException
  {
    Book book = bookCopyDAO.create(1,1);
    assertEquals("Hello", book.getTitle());
  }

  @Test void testGetBookDetailsById() throws SQLException{
    bookCopyDAO.create(1, 1);
    ResultSet resultSet = bookCopyDAO.getBookDetailsById(1);
    assertEquals("Hello", resultSet.getString("titel"));
  }

}