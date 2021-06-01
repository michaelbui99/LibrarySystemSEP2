package database.material;

import shared.materials.DVD;
import shared.materials.Material;
import shared.materials.Place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * DVD data access object
 *
 * @author Michael
 * @author Kutaiba
 * @author Kasper
 * @author Lilian
 * @version 1.0
 */
public interface DvdDAO
{
  /**
   * Creates a new DVD using the given parameters
   *
   * @param material_id   The material id of a created abstract material to assign it to the DVD
   * @param subtitle_lang The DVD's play duration
   * @param length_       The DVD's play duration
   * @param place         Object type Place which holds the information of the DVD's physical location
   * @return an Integer as the DVD's id
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  int create(int material_id, String subtitle_lang, int length_, Place place)
      throws SQLException;

  /**
   * Creates a copy of an already existing DVD using the DVD's material id
   *
   * @param materialID The DVD's material id
   * @param copyNo     The new copy number
   * @return a new DVD object
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  DVD createDVDCopy(int materialID, int copyNo) throws SQLException;

  /**
   * Locates a DVD using it's material id
   *
   * @param materialID The DVD's material id
   * @return a DVD if fund
   * @throws SQLException           if the given parameters violates the constraints, or object type in the database
   *                                the exception is thrown also if the connection to the database is not established
   * @throws NoSuchElementException if there is no DVD with a material id that matches the given material id
   */
  ResultSet getDVDDetailsByID(int materialID)
      throws SQLException, NoSuchElementException;

  /**
   * Check if a material of type DVD already exists in the system
   *
   * @param title          The DVD's title
   * @param publisher      The DVD's publisher
   * @param releaseDate    The DVD's release date
   * @param description    The DVD's description
   * @param targetAudience The DVD's target audience
   * @param language       The DVD's language
   * @param playDuration   The DVD's play duration
   * @param genre          The DVD's genre
   * @return true if the given parameters matches those of an existing book in the system
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean dvdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      String playDuration, String genre) throws SQLException;

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
   * Delete a DVD copy based on the given material id and copy number
   *
   * @param materialID The DVD's material id
   * @param copyNumber The DVD's copy number
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  void deleteDVDCopy(int materialID, int copyNumber) throws SQLException;
}


