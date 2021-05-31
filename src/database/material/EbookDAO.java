package database.material;

import shared.materials.Material;
import shared.materials.reading.EBook;
import shared.person.MaterialCreator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

//Lilian-Michael-Kasper-Kutaiba
public interface EbookDAO
{
  int create(int material_id, int page_no, MaterialCreator author,
      int license_no) throws SQLException;
  EBook createEBookCopy(int materialID, int copyNo) throws SQLException;

  ResultSet getEBookDetailsByID(int materialID)
      throws SQLException, NoSuchElementException;

  boolean eBookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, int pageCount,
      int licenseNr, String genre, MaterialCreator author)
      throws SQLException;

  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience);

  void deleteEBookCopy(int materialID, int copyNumber) throws SQLException;
}
