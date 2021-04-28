package database;

import client.model.material.reading.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BookCopyDAO
{

  /**
  * Creates a new BookCopy entry in the Database and returns a new Book object.
  * @param materialID materialID is the ID of the Material the book is bound to in the Database.
   * @param copyNo copyNo is the specific copy of the material.
   * @param isbn isbn is the unique ISBN code for the book.
   * @param pageCount pageCount is the total amount of pages in the book.
   * @return Book object with the details connected to the materialID.
  * */
  Book create(int materialID, int copyNo/*, String isbn, int pageCount*/ ) throws
      SQLException;

  /**
  * Returns all columns that are generated when joining BookCopy, Book, BookCopy on materialID.
   * @param materialID materialID is the ID of the Book copy that is to be joined on.
   * @return All columns generated from the join as a ResultSet
  * */
  ResultSet getBookDetailsById(int materialID) throws SQLException;
}
