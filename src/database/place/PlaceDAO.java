package database.place;

import shared.materials.Place;

import java.sql.SQLException;

/**
 * Place data access object
 *
 * @author Kutaiba
 * @version 1.0
 */
public interface PlaceDAO
{
  /**
   * Creates a Place object and add it to the database
   *
   * @param hallNo       The hall number
   * @param department   The Department's letter (one char)
   * @param creatorLName The material creator's last name
   * @param genre        The genre
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  Place create(int hallNo, String department, String creatorLName, String genre)
      throws SQLException;

  /**
   * Gets the place id from the database
   *
   * @param hallNo       The hall number
   * @param department   The Department's letter (one char)
   * @param creatorLName The material creator's last name
   * @param genre        The genre
   * @return an Integer as a place id
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  int getPlaceID(int hallNo, String department, String creatorLName,
      String genre) throws SQLException;

  /**
   * Gets the place id for a specific material based on the given material id and material type
   *
   * @param material_id The material's id
   * @param type        The material's copy
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  int getPlaceIDForMaterial(int material_id, String type) throws SQLException;
}
