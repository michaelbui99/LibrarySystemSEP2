package client.model.material;

import shared.materials.strategy.SearchStrategy;
import shared.materials.Material;
import shared.person.MaterialCreator;
import shared.materials.Place;
import shared.network.PropertyChangeSubject;

import java.util.List;

//Kutaiba
public interface MaterialModelClient extends PropertyChangeSubject
{

  /**
   * Creates a material of type book in the system
   */
  void registerBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, Place place, MaterialCreator author,
      String genre, String url, String keywords);

  /**
   * Creat a copy of an already existing book.
   * @param materialID is used to define the book which a copy will be added to
   * */
  void createBookCopy(int materialID);

  /**
   * Delete a book copy based on the given parameter
   * @param materialID material id
   * @param copyNo copy number
   * */

  void deleteBookCopy(int materialID, int copyNo);

  /**
   * Check if a material of type book already exists in the system
   * @return true
   * @param title ,
   * @param publisher ,
   * @param releaseDate ,
   * @param description ,
   * @param targetAudience ,
   * @param language ,
   * @param isbn ,
   * @param pageCount ,
   * @param author,
   * @param genre
   * matches those of an already existing material of type book in the system.
   * */
  boolean bookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, String isbn,
      int pageCount, MaterialCreator author, String genre);

  /**
   * Creates a material of type DVD in the system
   */
  void registerDVD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String subtitlesLanguage, int playDuration, Place place, String genre,
      String url);

  /**
   * Creat a copy of an already existing DVD.
   * @param materialID is used to define the DVD which a copy will be added to
   * */
  void createDVDCopy(int materialID);

  /**
   * Delete a DVD copy based on the given parameter
   *@param materialID material id
   *@param copyNo copy number
   * */

  void deleteDVDCopy(int materialID, int copyNo);

  /**
   * Check if a material of type DVD already exists in the system
   * @return true
   * @param title ,
   * @param publisher ,
   * @param releaseDate ,
   * @param description ,
   * @param targetAudience ,
   * @param language ,
   * @param playDuration,
   * @param genre
   * matches those of an already existing material of type DVD in the system.
   * */
  boolean dvdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      String playDuration, String genre);

  /**
   * Creates a material of type CD in the system
   */
  void registerCD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, Place place, String genre, String url);

  /**
   * Creat a copy of an already existing CD.
   * @param materialID is used to define the CD which a copy will be added to
   * */
  void createCDCopy(int materialID);

  /**
   *Delete a CD copy based on the given parameter
   *@param materialID material id
   *@param copyNo copy number
   * */

  void deleteCDCopy(int materialID, int copyNo);

  /**
   * Check if a material of type CD already exists in the system
   * @return true
   * @param title ,
   * @param publisher ,
   * @param releaseDate ,
   * @param description ,
   * @param targetAudience ,
   * @param language ,
   * @param playDuration,
   * @param genre
   * matches those of an already existing material of type CD in the system.
   * */
  boolean cdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      int playDuration, String genre);

  /**
   * Creates a material of type EBook in the system
   */
  void registerEBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int pageCount, int licenseNr, MaterialCreator author, String genre,
      String url);

  /**
   * Creat a copy of an already existing EBook.
   * @param materialID is used to define the EBook which a copy will be added to
   * */
  void createEBookCopy(int materialID);

  /**
   * Delete a Ebook copy based on the given parameter
   *@param materialID material id
   *@param copyNo copy number
   * */
  void deleteEBookCopy(int materialID, int copyNo);

  /**
   * Check if a material of type EBook already exists in the system
   * @return true
   * @param licenseNr,
   * @param author,
 * matches those of an already existing material of type EBook in the system.
   * @param title ,
   * @param publisher ,
   * @param releaseDate ,
   * @param description ,
   * @param targetAudience ,
   * @param language ,
   * @param pageCount ,
   * @param licenseNr
   * @param genre           */
  boolean eBookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, int pageCount,
      int licenseNr, String genre, MaterialCreator author);

  /**
   * Creates a material of type AudioBook in the system
   */
  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, String genre, MaterialCreator author, String url);

  /**
   * Creat a copy of an already existing AudioBook.
   * @param materialID is used to define the AudioBook which a copy will be added to
   * */
  void createAudioBookCopy(int materialID);

  /**
   * Delete a Audiobook copy based on the given parameter
   *@param materialID material id
   *@param copyNo copy number
   * */

  void deleteAudioBookCopy(int materialID, int copyNo);

  /**
   * Check if a material of type AudioBook already exists in the system
   * @return true
   * @param title ,
   * @param publisher ,
   * @param releaseDate ,
   * @param description ,
   * @param targetAudience ,
   * @param language ,
   * @param playDuration ,
   * @param author,
   * @param genre
   * matches those of an already existing material of type AudioBook in the system.
   * */
  boolean audioBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, MaterialCreator author, String genre);

  /**
   * Search the system to locate a material.
   * @return list of material
   * @param title,
   * @param language ,
   * @param keywords ,
   * @param genre ,
   * @param targetAudience ,
   * @param searchStrategy
   * */
  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience, SearchStrategy searchStrategy);

  /**
   * @return Material for a selected material
   * */

  Material getSelectMaterial();


  int numberOfAvailableCopies();

  int totalNumberOfCopies(int materialID);

  void setSelectMaterial(Material material);

  void deleteMaterial(int materialID);

}
