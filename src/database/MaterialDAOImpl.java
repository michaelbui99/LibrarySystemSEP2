package database;

import client.model.material.DVD;
import client.model.material.Material;
import client.model.material.audio.AudioBook;
import client.model.material.audio.CD;
import client.model.material.reading.Book;
import client.model.material.reading.EBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MaterialDAOImpl extends BaseDAO implements MaterialDAO
{

  private static MaterialDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static MaterialDAO getInstance()
  {
    //Double lock check for Thread safety
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new MaterialDAOImpl();
        }
      }
    }
    return instance;
  }

  @Override public int create(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String genre, String url)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "INSERT INTO Material ( title, audience, description_of_the_content, keywords, publisher,  language_, release_date, genre, url) values (?,?,?,?,?,?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setString(1, title);
      stm.setString(2, targetAudience);
      stm.setString(3, description);
      stm.setString(4, tags);
      stm.setString(5, publisher);
      stm.setString(6, language);
      stm.setDate(7, Date.valueOf(releaseDate));
      stm.setString(8, genre);
      stm.setString(9, url);

      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      keys.next();
      connection.commit();
      return keys.getInt(1);
    }
  }

  @Override public boolean materialExistInDB(int materialID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      //Checks Database for material with given ID.
      PreparedStatement stm = connection
          .prepareStatement("SELECT * FROM Material where material_id = ?");
      stm.setInt(1, materialID);
      ResultSet result = stm.executeQuery();

      //If we find a match in Database we return true, if not we return false
      return result.next();
    }
  }

  @Override public int getLatestCopyNo(int materialID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT material_copy.copy_no FROM material join material_copy USING (material_id) where material_id = ? ORDER BY copy_no desc LIMIT 1;");
      stm.setInt(1, materialID);
      ResultSet resultSet = stm.executeQuery();
      if (resultSet.next())
      {
        return resultSet.getInt(1);
      }
      else
        return 0;
      // throw new NoSuchElementException( "No material with materialID " + materialID + " exists.");
    }
  }

  @Override public List<Material> getAllMaterialByTitle(String title)
      throws SQLException
  {
    List<Material> returnList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      try
      {
        returnList.addAll(getAllBooksByTitle(title));
      }
      catch (NoSuchElementException e)
      {
      }
      try
      {
        returnList.addAll(getAllCDsByTitle(title));
      }
      catch (NoSuchElementException e)
      {
      }
      try
      {
        returnList.addAll(getAllDVDsByTitle(title));
      }
      catch (NoSuchElementException e)
      {
      }
      try
      {
        returnList.addAll(getAllAudioBooksByTitle(title));
      }
      catch (NoSuchElementException e)
      {
      }
      try
      {
        returnList.addAll(getAllEBooksByTitle(title));
      }
      catch (NoSuchElementException e)
      {
      }
      return returnList;
    }
  }



  public List<Book> getAllBooksByTitle(String title) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      List<Book> returnList = new ArrayList<>();
      ResultSet bookResult = getQueryResultByTypeTitle("book", title);
      if (bookResult.next())
      {
        while (bookResult.next())
        {
          System.out.println("Book found"); //Debugging
          //Add all found books to arraylist
          Book book = new Book(bookResult.getInt("material_id"),
              bookResult.getInt("copy_no"), bookResult.getString("title"),
              bookResult.getString("publisher"),
              String.valueOf(bookResult.getDate("release_date")),
              bookResult.getString("description_of_the_content"),
              bookResult.getString("keywords"), bookResult.getString("audience"),
              bookResult.getString("language_"), bookResult.getString("isbn"),
              bookResult.getInt("page_no"), bookResult.getInt("place_id"));
          returnList.add(book);
        }
      }
      else
      {
        throw new NoSuchElementException("No material was found");
      }
      return returnList;
    }
  }

  public List<DVD> getAllDVDsByTitle(String title) throws SQLException
  {
    List<DVD> returnList = new ArrayList<>();
    ResultSet dvdResult = getQueryResultByTypeTitle("dvd", title);

    //Add all found dvds to arraylist
    if (dvdResult.next())
    {
      while (dvdResult.next())
      {
    System.out.println("dvd found"); //Debugging
        DVD dvd = new DVD(dvdResult.getInt("material_id"),
            dvdResult.getInt("copy_no"), dvdResult.getString("title"),
            dvdResult.getString("publisher"),
            String.valueOf(dvdResult.getDate("release_date")),
            dvdResult.getString("description_of_the_content"),
            dvdResult.getString("keywords"), dvdResult.getString("audience"),
            dvdResult.getString("language_"),
            dvdResult.getString("subtitle_lang"),
            dvdResult.getDouble("length_"), dvdResult.getInt("place_id"));
        returnList.add(dvd);
      }
    }
    else
    {
      throw new NoSuchElementException("No material was found");
    }
    return returnList;
  }


  public List<CD> getAllCDsByTitle(String title) throws SQLException
  {
    List<CD> returnList = new ArrayList<>();
    ResultSet cdResult = getQueryResultByTypeTitle("cd", title);
    if (cdResult.next())
    {
      while (cdResult.next())
      {
        System.out.println("cd found"); //Debugging

        //Add all found cds to arraylist
        CD cd = new CD(cdResult.getInt("material_id"),
            cdResult.getInt("copy_no"), cdResult.getString("title"),
            cdResult.getString("publisher"),
            String.valueOf(cdResult.getDate("release_date")),
            cdResult.getString("description_of_the_content"),
            cdResult.getString("keywords"), cdResult.getString("audience"),
            cdResult.getString("language_"), cdResult.getDouble("length_"),
            cdResult.getInt("place_id"));
        returnList.add(cd);
      }
    }
    else
    {
      throw new NoSuchElementException("No material was found");
    }
    return returnList;
  }

  public List<AudioBook> getAllAudioBooksByTitle(String title) throws SQLException
  {
    List<AudioBook> returnList = new ArrayList<>();
    ResultSet audioBookResult = getQueryResultByTypeTitle("audiobook", title);
    if (audioBookResult.next())
    {
      while (audioBookResult.next())
      {
        System.out.println("audiobook found"); //Debugging

        //Add all found audiobooks to arraylist.
        AudioBook audioBook = new AudioBook(audioBookResult.getInt("material_id"),
            audioBookResult.getInt("copy_no"), audioBookResult.getString("title"),
            audioBookResult.getString("publisher"),
            String.valueOf(audioBookResult.getDate("release_date")),
            audioBookResult.getString("description_of_the_content"),
            audioBookResult.getString("keywords"), audioBookResult.getString("audience"),
            audioBookResult.getString("language_"), audioBookResult.getDouble("length_"));
        returnList.add(audioBook);
      }
    }
    else
    {
      throw new NoSuchElementException("No material was found");
    }
    return returnList;
  }

  public List<EBook> getAllEBooksByTitle(String title) throws SQLException
  {
    List<EBook> returnList = new ArrayList<>();
    ResultSet eBookResult = getQueryResultByTypeTitle("e_book", title);
    if (eBookResult.next())
    {
      while (eBookResult.next())
      {
        System.out.println("ebook found"); //Debugging

        //TODO: Tilføj attributes i databasen, så databasen matcher Java objektet.
        EBook eBook = new EBook(eBookResult.getInt("material_id"),
            eBookResult.getInt("copy_no"), eBookResult.getString("title"),
            eBookResult.getString("publisher"),
            String.valueOf(eBookResult.getDate("release_date")),
            eBookResult.getString("description_of_the_content"),
            eBookResult.getString("keywords"),
            eBookResult.getString("audience"),
            eBookResult.getString("language_"), eBookResult.getString("isbn"),
            eBookResult.getInt("page_no"), String.valueOf(eBookResult.getInt("license_no")), null, null);
        returnList.add(eBook);
      }
    }
    else
    {
      throw new NoSuchElementException("No material was found");
    }
    return returnList;
  }

  private ResultSet getQueryResultByTypeTitle(String type, String title)
      throws SQLException
  {
    //All valid type input to prevent SQL-injection with string interpolation in query.
    String[] safeTables = {"book", "audiobook", "cd", "dvd", "e_book"};
    if (!Arrays.asList(safeTables).contains(type))
    {
      throw new IllegalArgumentException("Illegal material type");
    }
    //Utility method created for getAllMaterialByTitle
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM material JOIN " + type + " USING (material_id) JOIN material_copy USING (material_id) where title = ? ");
      stm.setString(1, title);
      return stm.executeQuery();
    }
  }

  //  @Override public Material findByID(int id)
  //  {
  //    return null;
  //  }
}
