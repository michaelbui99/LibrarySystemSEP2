package client.model.material;

import client.model.material.strategy.SearchStrategy;

import java.sql.SQLException;

public interface MaterialModelClient extends SearchStrategy
{

  /**
   * Registers a new Material in the system and binds it to a book.
   *
   */
  void registerBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, int placeID, int authorId, String genre,
      String url);

  void createBookCopy(int materialID);

  void registerDVD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage, String playDuration, int placeID, String genre,
      String url);

  void createDVDCopy(int materialID);

  void registerCD(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID, String genre,
      String url);

  void createCDCopy(int materialID);

  void registerEBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, String licenseNr, int authorId, String genre,
      String url);

  void createEBookCopy(int materialID);


  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      double playDuration, String genre, int authorId, String url);

  void createAudioBookCopy(int materialID);


  void findMaterial(String arg, SearchStrategy searchStrategy);
}
