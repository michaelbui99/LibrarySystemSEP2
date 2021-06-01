package client.model.material;

import shared.materials.strategy.SearchStrategy;
import shared.materials.Material;
import shared.person.MaterialCreator;
import shared.materials.Place;
import shared.network.PropertyChangeSubject;

import java.util.List;

/**
 * Material model for client
 *
 * @author Kutaiba
 * @version 1.0
 */
public interface MaterialModelClient extends PropertyChangeSubject
{

  /**
   * Creates a material of type book in the system
   *
   * @param title          The book's title
   * @param publisher      The book's publisher
   * @param releaseDate    The book's release date
   * @param description    The book's description
   * @param tags           The book's tags
   * @param targetAudience The book's target audience
   * @param language       The book's language
   * @param isbn           The book's serial number (ISBN)
   * @param pageCount      The book's number of pages
   * @param place          Object type place which holds the information of the book's physical location
   * @param author         Object type MaterialCreator which holds the information of the book's author
   * @param genre          The book's genre
   * @param url            The book's image link
   * @param keywords       The book's keywords
   */
  void registerBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, Place place, MaterialCreator author,
      String genre, String url, String keywords);

  /**
   * Creates a copy of an already existing book by using the book's material id.
   *
   * @param materialID The book material id.
   */
  void createBookCopy(int materialID);

  /**
   * Deletes a book copy based on the given material id and copy number
   *
   * @param materialID The book material id
   * @param copyNo     The copy number of the book
   */

  void deleteBookCopy(int materialID, int copyNo);

  /**
   * Check if a material of type book already exists in the system
   *
   * @param title          The book's title
   * @param publisher      The book's publisher
   * @param releaseDate    The book's release date
   * @param description    The book's description
   * @param targetAudience The book's target audience
   * @param language       The book's language
   * @param isbn           The book's serial number (ISBN)
   * @param pageCount      The book's number of pages
   * @param author         The book's author
   * @param genre          The book's genre
   * @return true if the given parameters matches those of an existing book in the system
   */
  boolean bookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, String isbn,
      int pageCount, MaterialCreator author, String genre);

  /**
   * Creates a material of type DVD in the system
   *
   * @param title             The DVD's title
   * @param publisher         The DVD's publisher
   * @param releaseDate       The DVD's release date
   * @param description       The DVD's description
   * @param tags              The DVD's tags
   * @param targetAudience    The DVD's target audience
   * @param language          The DVD's language
   * @param subtitlesLanguage The DVD's subtitle language
   * @param playDuration      The DVD's play duration (length in minutes)
   * @param place             Object type place which holds the information of the DVD's physical location
   * @param genre             The DVD's genre
   * @param url               The DVD's image link
   */
  void registerDVD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String subtitlesLanguage, int playDuration, Place place, String genre,
      String url);

  /**
   * Creates a copy of an already existing DVD using the DVD's material id
   *
   * @param materialID The DVD's material id
   */
  void createDVDCopy(int materialID);

  /**
   * Delete a DVD copy based on the given material id and copy number
   *
   * @param materialID The DVD's material id
   * @param copyNo     The DVD's copy number
   */

  void deleteDVDCopy(int materialID, int copyNo);

  /**
   * Check if a material of type DVD already exists in the system
   *
   * @param title          The DVD's title
   * @param publisher      The DVD's publisher
   * @param releaseDate    The DVD's release date
   * @param description    The DVD's description
   * @param targetAudience The DVD's target audience
   * @param language       The DVD's language
   * @param playDuration   The DVD's play duration
   * @param genre          The DVD's genre
   * @return true if the given parameters matches those of an existing book in the system
   */
  boolean dvdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      String playDuration, String genre);

  /**
   * Creates a material of type CD in the system
   *
   * @param title          The CD's title
   * @param publisher      The CD's publisher
   * @param releaseDate    The CD's release date
   * @param description    The CD's description
   * @param tags           The CD's tags
   * @param targetAudience The CD's target audience
   * @param language       The CD's language
   * @param playDuration   The CD's play duration (in minutes)
   * @param place          Object type place which holds the information of the CD's physical location
   * @param genre          the CD's genre
   * @param url            The CD's image link
   */
  void registerCD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, Place place, String genre, String url);

  /**
   * Creates a copy of an already existing CD using the CD's material id
   *
   * @param materialID The CD's material id
   */
  void createCDCopy(int materialID);

  /**
   * Delete a CD copy based on the given material id and copy number
   *
   * @param materialID The CD's material id
   * @param copyNo     The CD's copy number
   */

  void deleteCDCopy(int materialID, int copyNo);

  /**
   * Check if a material of type CD already exists in the system
   *
   * @param title          The CD's title
   * @param publisher      The CD's publisher
   * @param releaseDate    The CD's release date
   * @param description    The CD's description
   * @param targetAudience The CD's target audience
   * @param language       The CD's language
   * @param playDuration   The CD's play duration
   * @param genre          The CD's genre
   * @return true if the given parameters matches those of an existing book in the system
   */
  boolean cdAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language,
      int playDuration, String genre);

  /**
   * Creates a material of type EBook in the system
   *
   * @param title          The E-book's title
   * @param publisher      The E-book's publisher
   * @param releaseDate    The E-book's release date
   * @param description    The E-book's description
   * @param tags           The E-book's tags
   * @param targetAudience The E-book's target audience
   * @param language       The E-book's language
   * @param pageCount      The E-book's number of pages
   * @param licenseNr      The E-book's license number
   * @param author         Object type MaterialCreator which holds the information of the book's author
   * @param genre          The E-book's genre
   * @param url            The E-book's image link
   */
  void registerEBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int pageCount, int licenseNr, MaterialCreator author, String genre,
      String url);

  /**
   * Creates a copy of an already existing EBook using the E-book's material id.
   *
   * @param materialID The E-book's material id
   */
  void createEBookCopy(int materialID);

  /**
   * Deletes a Ebook copy based on the given material id nad copy number
   *
   * @param materialID The E-book's material id
   * @param copyNo     The E-book's copy number
   */
  void deleteEBookCopy(int materialID, int copyNo);

  /**
   * Check if a material of type EBook already exists in the system
   *
   * @param title          The E-book's title
   * @param publisher      The E-book's publisher
   * @param releaseDate    The E-book's release date
   * @param description    The E-book's description
   * @param targetAudience The E-book's target audience
   * @param language       The E-book's language
   * @param pageCount      The E-book's number of pages
   * @param licenseNr      The E-book's license number
   * @param genre          The E-book's genre
   * @param author         The E-book's author
   * @return true if the given parameters matches those of an existing book in the system
   */
  boolean eBookAlreadyExists(String title, String publisher, String releaseDate,
      String description, String targetAudience, String language, int pageCount,
      int licenseNr, String genre, MaterialCreator author);

  /**
   * Creates a material of type AudioBook in the system
   *
   * @param title          The audiobook's title
   * @param publisher      The audiobook's publisher
   * @param releaseDate    The audiobook's release date
   * @param description    The audiobook's description
   * @param tags           The audiobook's tags
   * @param targetAudience The audiobook's target audience
   * @param language       The audiobook's language
   * @param playDuration   The audiobook's play duration (in minutes)
   * @param genre          The audiobook's genre
   * @param author         Object type MaterialCreator which holds the information of the book's author
   * @param url            The audiobook's image link
   */
  void registerAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, String genre, MaterialCreator author, String url);

  /**
   * Creates a copy of an already existing AudioBook using the audiobook's material id.
   *
   * @param materialID The audiobook's material id
   */
  void createAudioBookCopy(int materialID);

  /**
   * Deletes an Audiobook copy based on the given material id and copy number
   *
   * @param materialID The audiobook's material id
   * @param copyNo     The audiobook's copy number
   */

  void deleteAudioBookCopy(int materialID, int copyNo);

  /**
   * Check if a material of type AudioBook already exists in the system
   *
   * @param title          The audiobook's title
   * @param publisher      The audiobook's publisher
   * @param releaseDate    The audiobook's release date
   * @param description    The audiobook's description
   * @param targetAudience The audiobook's target audience
   * @param language       The audiobook's language
   * @param playDuration   The audiobook's play duration
   * @param author         The audiobook's author
   * @param genre          The audiobook's genre
   * @return true if the given parameters matches those of an existing book in the system
   */
  boolean audioBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, MaterialCreator author, String genre);

  /**
   * Search the system to locate a material.
   *
   * @param title          The material's title
   * @param language       The material's language
   * @param keywords       The material's keywords
   * @param genre          The material's genre
   * @param targetAudience The material's target audience
   * @param searchStrategy Search strategy to chose which type of material the method should locate
   * @return list of material
   */
  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience, SearchStrategy searchStrategy);

  /**
   * @return Material
   */
  Material getSelectMaterial();

  /**
   * @return The number of available copies
   */
  int numberOfAvailableCopies();

  /**
   * Get the total number of copies of a material using it's material id
   *
   * @param materialID The material id
   * @return total number of copies
   */
  int totalNumberOfCopies(int materialID);

  /**
   * Updates the value of the selected material
   *
   * @param material The given material
   */
  void setSelectMaterial(Material material);

  /**
   * Deletes a material from the system with all of its copies using the material id
   *
   * @param materialID The material id
   */
  void deleteMaterial(int materialID);

}
