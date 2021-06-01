package database.material;

import shared.materials.Material;
import shared.materials.audio.AudioBook;
import shared.person.MaterialCreator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Audio book data access object
 *
 * @author Michael
 * @author Kutaiba
 * @author Kasper
 * @author Lilian
 * @version 1.0
 */
public interface AudioBookDAO
{

  /**
   * Creates a new audio book using the given parrameters
   *
   * @param material_id The material id of a created abstact material to assign it to the audio book
   * @param length_     The audio book's play duration
   * @param author      The audio book's author
   * @return an Integer as the audio book's id
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  int create(int material_id, int length_, MaterialCreator author)
      throws SQLException;

  /**
   * Creates a new copy of an existing audio book using a material id and copy number
   *
   * @param materialID The audio book's material id
   * @param copyNo     The new copy's number
   * @return a new AudioBook object
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  AudioBook createAudioBookCopy(int materialID, int copyNo) throws SQLException;

  /**
   * Checks if a material of type AudioBook already exists in the database
   *
   * @param title          The audiobook's title
   * @param publisher      The audiobook's publisher
   * @param releaseDate    The audiobook's release date
   * @param description    The audiobook's description
   * @param targetAudience The audiobook's target audience
   * @param language       The audiobook's language
   * @param playDuration   The audiobook's play duration
   * @param author         The audiobook's author
   * @param genre          The audiobook's genre
   * @return true if the given parameters matches those of an existing book in the system
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  boolean audioBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, MaterialCreator author, String genre)
      throws SQLException;

  /**
   * Locates an audio book using it's material id
   *
   * @param materialID The audio book's material id
   * @return an audiobook if found
   * @throws SQLException           if the given parameters violates the constraints, or object type in the database
   *                                the exception is thrown also if the connection to the database is not established
   * @throws NoSuchElementException if there is no audio book with a material id that matches the given material id
   */
  ResultSet getAudioBookDetailsByID(int materialID)
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
   * Deletes an Audiobook copy based on the given material id and copy number
   *
   * @param materialID The audiobook's material id
   * @param copyNumber     The audiobook's copy number
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  void deleteAudioBookCopy(int materialID, int copyNumber) throws SQLException;
}
