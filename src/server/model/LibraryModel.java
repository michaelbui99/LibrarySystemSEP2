package server.model;

import client.model.material.Material;
import shared.PropertyChangeSubject;

public interface LibraryModel extends PropertyChangeSubject
{
  /**
   * Registers a new Loan for the given material and loaner.
   *
   * @param material  material is the Material the loaner wants to loan.
   * @param loanerCPR loanerCPR is the CPR which the material will be bound to in the system for the given copy of material.
   * @param deadline  deadline is the deadline for when the material must be returned to the library.
   * @throws IllegalStateException if the material is is not available for loan.
   */
  void registerLoan(Material material, String loanerCPR, String deadline)
      throws IllegalStateException;

  /**
   * Registers a new Material in the system and binds it to a book.
   *
   */
  void registerBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, int placeID);

  void createBookCopy(int materialID);

  void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage, double playDuration, int placeID);

  void createDVDCopy(int materialID);

  void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID);

  void createCDCopy(int materialID);

  void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, String licenseNr, String author, String genre);

  void createEBookCopy(int materialID);


  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      double playDuration);

  void createAudioBookCopy(int materialID);
  Material searchMaterial(String arg);
}
