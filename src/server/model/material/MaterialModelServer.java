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
  void registerBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, Place place, MaterialCreator author, String genre,
      String url);

  void createBookCopy(int materialID);

  void registerDVD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage, int playDuration, Place place, String genre,
      String url);

  void createDVDCopy(int materialID);

  void registerCD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, Place place, String genre,
      String url);

  void createCDCopy(int materialID);

  void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount, int licenseNr,
      MaterialCreator author, String genre, String url);

  void createEBookCopy(int materialID);


  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, String genre, MaterialCreator author, String url);

  void createAudioBookCopy(int materialID);


  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience, SearchStrategy searchStrategy);


}
