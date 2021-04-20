package database;

import java.sql.SQLException;

public interface MaterialCopyDAO
{
  void create(int materialID, int copyNr) throws SQLException;
}
