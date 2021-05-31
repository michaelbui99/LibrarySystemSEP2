package database.material;

import shared.materials.Material;
import shared.materials.Place;
import shared.materials.audio.CD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

//Lilian-Michael-Kasper-Kutaiba
public interface CDDAO
{

  int create(int material_id, int length_, Place place) throws SQLException;

  CD createCDCopy(int materialID, int copyNo) throws SQLException;

  ResultSet getCDDetailsByID(int materialID)
      throws SQLException, NoSuchElementException;

  boolean cdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      int playDuration, String genre) throws SQLException;

  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience);

  void deleteCDCopy(int materialID, int copyNumber) throws SQLException;
}
