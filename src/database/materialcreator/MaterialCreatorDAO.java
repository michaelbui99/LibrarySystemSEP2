package database.materialcreator;

import client.model.material.strategy.MaterialCreator;

import java.sql.Date;
import java.sql.SQLException;

public interface MaterialCreatorDAO
{
  MaterialCreator create(String fName, String lName, String dob, String country) throws
      SQLException;

  int getCreatorId(String fName, String lName, String dob, String country) throws SQLException;
}
