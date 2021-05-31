package database.place;

import shared.materials.Place;

import java.sql.SQLException;

//Kutaiba
public interface PlaceDAO
{
  Place create(int hallNo, String department, String creatorLName, String genre)
      throws SQLException;

  int getPlaceID(int hallNo, String department, String creatorLName, String genre)
      throws SQLException;
  int getPlaceIDForMaterial(int material_id, String type) throws SQLException;
}
