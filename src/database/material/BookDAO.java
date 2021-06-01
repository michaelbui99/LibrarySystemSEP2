package database.material;

import shared.materials.Material;
import shared.materials.Place;
import shared.materials.reading.Book;
import shared.person.MaterialCreator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Book data access object
 *
 * @author Michael
 * @author Kutaiba
 * @author Kasper
 * @author Lilian
 * @version 1.0
 */
public interface BookDAO
{
  /**
   * Creates a new book using the given parameters
   *
   * @param materialID The material id of a created abstract material to assign it to the book
   * @param isbn       The book's play duration
   * @param author     Object type MaterialCreator which holds the information of the book's author
   * @param place      Object type Place which holds the information of the book's physical location
   * @return an Integer as the book's id
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  int create(int materialID, String isbn, int pageCount, MaterialCreator author,
      Place place) throws SQLException;

  /**
   * Creates a copy of an already existing book by using the book's material id.
   *
   * @param materialID The book material id.
   * @param copyNo     The new copy number
   * @return a new Book object
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  Book createBookCopy(int materialID, int copyNo) throws SQLException;

  /**
   * Check if a material of type book already exists in the system
   *
   * @param title          The book's title
   * @param publisher      The book's publisher
   * @param releaseDate    The book's release date
   * @param description    The book's description
   * @param targetAudience The book's target audience
   * @param language       The book's language
   * @param isbn           The book's serial number (ISBN)
   * @param pageCount      The book's number of pages
   * @param author         The book's author
   * @param genre          The book's genre
   * @return true if the given parameters matches those of an existing book in the system
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean bookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, String isbn,
      int pageCount, MaterialCreator author, String genre) throws SQLException;

  /**
   * Locates a book using it's material id
   *
   * @param materialID The book's material id
   * @return a book id fund
   * @throws SQLException           if the given parameters violates the constraints, or object type in the database
   *                                the exception is thrown also if the connection to the database is not established
   * @throws NoSuchElementException if there is no book with a material id that matches the given material id
   */
  ResultSet getBookDetailsByID(int materialID)
      throws SQLException, NoSuchElementException;

  /**
   * Search the system to locate a material.
   *
   * @param title          The material's title
   * @param language       The material's language
   * @param keywords       The material's keywords
   * @param genre          The material's genre
   * @param targetAudience The material's target audience
   * @return list of material
   */
  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience);

  /**
   * Deletes Book copy based on the given material id and copy number
   *
   * @param materialID The book's material id
   * @param copyNumber The book's copy number
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  void deleteBookCopy(int materialID, int copyNumber) throws SQLException;
}
