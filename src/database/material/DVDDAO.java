package database.material;

import shared.materials.DVD;
import shared.materials.Material;
import shared.materials.Place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface DVDDAO
{

  int create(int material_id, String subtitle_lang, int length_, Place place)
      throws SQLException;
  DVD createDVDCopy(int materialID, int copyNo) throws SQLException;

  ResultSet getDVDDetailsByID(int materialID)
      throws SQLException, NoSuchElementException;

  boolean dvdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      String playDuration, String genre) throws SQLException;

  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience);

  void deleteDVDCopy(int materialID, int copyNumber) throws SQLException;
}


