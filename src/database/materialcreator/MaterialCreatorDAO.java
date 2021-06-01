package database.materialcreator;

import shared.person.MaterialCreator;

import java.sql.SQLException;

/**
 * Material creator data access object
 *
 * @author Kutaiba
 * @version 1.0
 */
public interface MaterialCreatorDAO
{

  /**
   * Creates a MaterialCreator object and add it to the database
   *
   * @param fName   The material creator's first name
   * @param lName   The material creator's last name
   * @param dob     The material creator's date of birth
   * @param country The material creator's country
   * @return a new MaterialCreator object
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  MaterialCreator create(String fName, String lName, String dob, String country)
      throws SQLException;

  /**
   * Gets the material creator's id from the database
   *
   * @param fName   The material creator's first name
   * @param lName   The material creator's last name
   * @param dob     The material creator's date of birth
   * @param country The material creator's country
   * @return an Integer as the material creator's id
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  int getCreatorId(String fName, String lName, String dob, String country)
      throws SQLException;
}
