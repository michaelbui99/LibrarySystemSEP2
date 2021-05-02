package database;

import client.model.material.DVD;
import client.model.material.Material;
import client.model.material.audio.AudioBook;
import client.model.material.audio.CD;
import client.model.material.reading.Book;
import client.model.material.reading.EBook;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface MaterialDAO
{
  /**
   * Creates a new Material entry in the Database. Method to be used when creating every concrete type of material.
   *
   * @return int returns the autogenerated materialID for the new Material as an int since Material will never be instantiated.
   */
  int create(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String genre, String url) throws SQLException;

  boolean materialExistInDB(int materialID) throws SQLException;

  /**
   * Returns the latest copy number of the given material.
   *
   * @param materialID materialID is the ID of the material you want to find the latest copy number of.
   * @throws NoSuchElementException NoSuchElementException is thrown if no Material with given materialID exists.
   */
  int getLatestCopyNo(int materialID)
      throws SQLException, NoSuchElementException;
  //  Material findByID(int id);

  List<Material> getAllMaterialByTitle(String title) throws SQLException;
  List<Book> getAllBooksByTitle(String title) throws SQLException;
  List<DVD> getAllDVDsByTitle(String title) throws SQLException;
  List<CD> getAllCDsByTitle(String title) throws SQLException;
  List<AudioBook> getAllAudioBooksByTitle(String title) throws SQLException;
  List<EBook> getAllEBooksByTitle(String title) throws SQLException;
  List<Book> getAllBooks() throws SQLException;
  List<EBook> getAllEbooks() throws SQLException;
  List<AudioBook> getAllAudioBooks() throws SQLException;
  List<DVD> getAllDVDs() throws SQLException;
  List<CD> getAllCDs() throws SQLException;




}
