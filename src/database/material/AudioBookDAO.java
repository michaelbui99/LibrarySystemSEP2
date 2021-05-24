package database.material;

import shared.materials.Material;
import shared.materials.audio.AudioBook;
import shared.person.MaterialCreator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface AudioBookDAO
{

  void create(int material_id, int length_, MaterialCreator author)
      throws SQLException;
  AudioBook createAudioBookCopy(int materialID, int copyNo) throws SQLException;

  boolean audioBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, MaterialCreator author, String genre)
      throws SQLException;

  ResultSet getAudioBookDetailsByID(int materialID)
      throws SQLException, NoSuchElementException;

  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience);

  void deleteAudioBookCopy(int materialID, int copyNumber) throws SQLException;
}
