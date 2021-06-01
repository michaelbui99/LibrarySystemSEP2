package database.material;

import shared.materials.Material;
import shared.materials.Place;
import shared.materials.audio.CD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * CD data access object
 *
 * @author Michael
 * @author Kutaiba
 * @author Kasper
 * @author Lilian
 * @version 1.0
 */
public interface CdDAO
{
  /**
   * Creates a new book using the given parameters
   *
   * @param material_id The material id of a created abstract material to assign it to the CD
   * @param length_     The CD's play duration
   * @param place       Object type Place which holds the information of the CD's physical location
   * @return an Integer as the CD's id
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  int create(int material_id, int length_, Place place) throws SQLException;

  /**
   * Creates a copy of an already existing CD using the CD's material id
   *
   * @param materialID The CD's material id
   * @param copyNo     The new copy number
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  CD createCDCopy(int materialID, int copyNo) throws SQLException;

  /**
   * Locates a CD using it's material id
   *
   * @param materialID The CD's material id
   * @return a CD if fund
   * @throws SQLException           if the given parameters violates the constraints, or object type in the database
   *                                the exception is thrown also if the connection to the database is not established
   * @throws NoSuchElementException if there is no CD with a material id that matches the given material id
   */
  ResultSet getCDDetailsByID(int materialID)
      throws SQLException, NoSuchElementException;

  /**
   * Check if a material of type CD already exists in the system
   *
   * @param title          The CD's title
   * @param publisher      The CD's publisher
   * @param releaseDate    The CD's release date
   * @param description    The CD's description
   * @param targetAudience The CD's target audience
   * @param language       The CD's language
   * @param playDuration   The CD's play duration
   * @param genre          The CD's genre
   * @return true if the given parameters matches those of an existing CD in the system
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean cdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      int playDuration, String genre) throws SQLException;

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
   * Delete a CD copy based on the given material id and copy number
   *
   * @param materialID The CD's material id
   * @param copyNumber The CD's copy number
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  void deleteCDCopy(int materialID, int copyNumber) throws SQLException;
}
