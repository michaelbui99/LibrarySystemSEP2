package database;

import shared.util.PostgresUserPasswords;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO
{
  protected Connection getConnection() throws SQLException
  {
    Connection result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=librarySystem", "postgres",
        PostgresUserPasswords.KUTAIBA);
    result.setAutoCommit(false);
    return result;
  }
}
