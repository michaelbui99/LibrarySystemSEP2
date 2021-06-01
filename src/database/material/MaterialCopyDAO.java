package database.material;

import java.sql.SQLException;

/**
 * Material copy data access object
 *
 * @author Michael
 * @author Kutaiba
 * @author Kasper
 * @author Lilian
 * @version 1.0
 */
public interface MaterialCopyDAO
{
  /**
   * Creates a new material copy based on the given parameter
   *
   * @param materialID The material's id
   * @param copyNr     The material's copy number
   * @throws SQLException if the given parameters violates the constraints, or object type in the database
   *                      the exception is thrown also if the connection to the database is not established
   */
  void create(int materialID, int copyNr) throws SQLException;

  /**
   * Get the first available copy of a material based on a given material id
   *
   * @param materialID The wanted material's id
   * @return the copy number of the first available copy
   */
  int getFirstAvailableCopyNo(int materialID);
}
