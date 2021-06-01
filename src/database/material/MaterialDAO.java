package database.material;

import shared.materials.Material;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Material data access object
 *
 * @author Michael
 * @author Kutaiba
 * @author Kasper
 * @author Lilian
 * @version 1.0
 */
public interface MaterialDAO
{
  /**
   * Register a new abstract material to the database
   *
   * @param title          The material's title
   * @param publisher      The material's publisher
   * @param releaseDate    The material's release date
   * @param description    The material's description
   * @param targetAudience The material's target audience
   * @param language       The material's language
   * @param genre          The material's genre
   * @param url            The material's image link
   * @param keywords       The material's keywords
   * @return the material id for the registered material
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  int create(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, String genre,
      String url, String keywords) throws SQLException;

  /**
   * Check the database for a material using that material's id
   *
   * @param materialID The material's id
   * @return true if the given material id matches that of an existed material id database
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean materialExistInDB(int materialID) throws SQLException;

  /**
   * Returns the latest copy number of the given material.
   *
   * @param materialID materialID is the ID of the material you want to find the latest copy number of.
   * @return the copy number if fund
   * @throws NoSuchElementException NoSuchElementException is thrown if no Material with given materialID exists.
   * @throws SQLException           if the given parameters violates the constraints, or object type in the database
   *                                the exception is thrown also if the connection to the database is not established
   */
  int getLatestCopyNo(int materialID)
      throws SQLException, NoSuchElementException;

  /**
   * Search the system to locate a material.
   *
   * @param title          The material's title
   * @param language       The material's language
   * @param keywords       The material's keywords
   * @param genre          The material's genre
   * @param targetAudience The material's target audience
   * @param type           The material's type
   * @return list of material
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience, String type) throws SQLException;

  /**
   * Get the total number of copies of a material based on the given material id
   *
   * @param materialId The material's id
   * @return the numbers of copies
   */
  int getCopyNumberForMaterial(int materialId);

  /**
   * Get the total number of available copies of a material based on the given material id
   *
   * @param materialId The material's id
   * @return the numbers of available copies
   */
  int getNumberOfAvailableCopies(int materialId);

  /**
   * Checks if there are any available copies for a material based on the Given material id
   *
   * @param materialId The material's id
   * @return true if there any available copies for that material
   */
  boolean checkIfCopyAvailable(int materialId);

  /**
   * Get all the keywords related to a material based on the given material id
   *
   * @param materialId The material's id
   * @return a list of keywords
   */
  List<String> getKeywordsForMaterial(int materialId);

  /**
   * Get the total number of copies of a material based on the given material id
   *
   * @param materialID The material's id
   * @return the numbers of copies
   */
  int totalNumberOfCopies(int materialID);

  /**
   * Delete a material from database based on the given material id
   *
   * @param materialID The material's id
   */
  void deleteMaterial(int materialID);
}
