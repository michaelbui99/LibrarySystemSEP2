package server.model.material;

import shared.materials.Material;
import shared.materials.Place;
import shared.person.MaterialCreator;
import client.model.material.strategy.SearchStrategy;
import shared.servers.PropertyChangeSubject;

import java.sql.SQLException;
import java.util.List;

public interface MaterialModelServer extends PropertyChangeSubject
{
  void registerBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, Place place, MaterialCreator author,
      String genre, String url);

  void createBookCopy(int materialID);

  boolean bookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, String isbn,
      int pageCount, MaterialCreator author, String genre);

  void registerDVD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String subtitlesLanguage, int playDuration, Place place, String genre,
      String url);

  void createDVDCopy(int materialID);

  boolean dvdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      String playDuration, String genre);

  void registerCD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, Place place, String genre, String url);

  void createCDCopy(int materialID);

  boolean cdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      int playDuration, String genre);

  void registerEBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int pageCount, int licenseNr, MaterialCreator author, String genre,
      String url);

  void createEBookCopy(int materialID);

  boolean eBookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, int pageCount,
      String licenseNr, String genre, MaterialCreator author);

  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, String genre, MaterialCreator author, String url);

  void createAudioBookCopy(int materialID);

  boolean audioBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, MaterialCreator author, String genre);

  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience, SearchStrategy searchStrategy);

  int numberOfAvailableCopies();
  Material getSelectMaterial();
  void setSelectedMaterial(Material material);

}
