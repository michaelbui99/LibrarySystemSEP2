package database.material;

import client.model.material.reading.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface BookDAO
{
    /**
     * Method is used to create a new Book in the Database.
     * The method will create a Book object,
     * use createBookCopy method to create a Book object.
     * @param materialID materialID is the ID of the material that the book is connected to.
     * @param isbn isbn is the unique ISBN code of the book. Must have a length of 10 or 13.
     * @param pageCount pageCount is the total amount pages in the book.
     * @param placeID placeID is the ID of the physical placement of the book.
    * */
  void create(int materialID, String isbn, int pageCount, int authorId, int placeID) throws SQLException;

  Book createBookCopy(int materialID, int copyNo) throws SQLException;

  /**
  * Returns all the Book details of if the book exists.
   * @param materialID materialID is the Id of the material that the book is connected to.
   * @exception NoSuchElementException NoSuchElementException is thrown if no book with given materialID exists.
  * */
  ResultSet getBookDetailsByID(int materialID) throws SQLException,
      NoSuchElementException;


}
