package database.material;

import shared.materials.Material;
import shared.materials.reading.EBook;
import shared.person.MaterialCreator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Ebook data access object
 *
 * @author Michael
 * @author Kutaiba
 * @author Kasper
 * @author Lilian
 * @version 1.0
 */
public interface EbookDAO
{
  /**
   * Creates a new e-book using the given parameters
   *
   * @param material_id The material id of a created abstract material to assign it to the e-book
   * @param page_no     The e-book's play duration
   * @param author      Object type MaterialCreator which holds the information of the e-book's author
   * @param license_no  The e-book license number
   * @return an Integer as the e-book's id
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  int create(int material_id, int page_no, MaterialCreator author,
      int license_no) throws SQLException;

  /**
   * Creates a copy of an already existing EBook using the E-book's material id.
   *
   * @param materialID The E-book's material id
   * @param copyNo     The new copy number
   * @return a new e-book object
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  EBook createEBookCopy(int materialID, int copyNo) throws SQLException;

  /**
   * Locates a e-book using it's material id
   *
   * @param materialID The e-book's material id
   * @return a e-book id fund
   * @throws SQLException           if the given parameters violates the constraints, or object type in the database
   *                                the exception is thrown also if the connection to the database is not established
   * @throws NoSuchElementException if there is no e-book with a material id that matches the given material id
   */
  ResultSet getEBookDetailsByID(int materialID)
      throws SQLException, NoSuchElementException;

  /**
   * Check if a material of type EBook already exists in the system
   *
   * @param title          The E-book's title
   * @param publisher      The E-book's publisher
   * @param releaseDate    The E-book's release date
   * @param description    The E-book's description
   * @param targetAudience The E-book's target audience
   * @param language       The E-book's language
   * @param pageCount      The E-book's number of pages
   * @param licenseNr      The E-book's license number
   * @param genre          The E-book's genre
   * @param author         The E-book's author
   * @return true if the given parameters matches those of an existing book in the system
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean eBookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, int pageCount,
      int licenseNr, String genre, MaterialCreator author) throws SQLException;

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
   * Deletes an Ebook copy based on the given material id nad copy number
   *
   * @param materialID The E-book's material id
   * @param copyNumber The E-book's copy number
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  void deleteEBookCopy(int materialID, int copyNumber) throws SQLException;
}
