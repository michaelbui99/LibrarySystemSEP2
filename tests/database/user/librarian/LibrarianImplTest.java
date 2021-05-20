package database.user.librarian;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianImplTest
{
  @Test void employeeNumberAlreadyExistsTest() throws SQLException
  {
    assertTrue(
        LibrarianImpl.getInstance().employeeNumberAlreadyExists(123456789));
  }

  @Test void cprNumberAlreadyExistsTest() throws SQLException
  {
    assertTrue(LibrarianImpl.getInstance().librarianCprNumberAlreadyExists("123456-1234"));
  }

  @Test void emailAlreadyExistsTest() throws SQLException
  {
    assertTrue(LibrarianImpl.getInstance().librarianEmailAlreadyExists("email@test"));
  }

  @Test void phoneNumberAlreadyExistsTest() throws SQLException
  {
    assertTrue(LibrarianImpl.getInstance().librarianPhoneNumberAlreadyExists("+4512345678"));
  }

  @Test void librarianAlreadyExistsTest() throws SQLException
  {
    assertTrue(LibrarianImpl.getInstance().librarianAlreadyExists(123456789, "123456-1234", "email@test", "+4512345678"));
  }
}