package database.place;

import shared.places.Place;

import java.sql.SQLException;

public interface PlaceDAO
{
  Place create(int hallNo, String department, String creatorLName, String genre)
      throws SQLException;

  int getPlaceID(int hallNo, String department, String creatorLName, String genre)
      throws SQLException;
  int getPlaceIDForMaterial(int material_id, String type) throws SQLException;
}
