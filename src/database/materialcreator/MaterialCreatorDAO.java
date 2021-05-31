package database.materialcreator;

import shared.person.MaterialCreator;

import java.sql.SQLException;

//Kutaiba
public interface MaterialCreatorDAO
{
  MaterialCreator create(String fName, String lName, String dob, String country) throws
      SQLException;

  int getCreatorId(String fName, String lName, String dob, String country) throws SQLException;
}
