package database;

import client.model.material.Material;
import client.model.material.reading.Book;

import java.sql.*;
import java.util.ArrayList;
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
    ArrayList<Material> returnList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      //Find all books with matching title
      ResultSet bookResult = getQueryResultByTypeTitle("book", title);
      ResultSet dvdResult = getQueryResultByTypeTitle("dvd", title);
      ResultSet cdResult = getQueryResultByTypeTitle("cd", title);
      ResultSet audioBookResult = getQueryResultByTypeTitle("audiobook", title);
      ResultSet eBookResult = getQueryResultByTypeTitle("e_book", title);

      if (!bookResult.next() && !dvdResult.next() && !cdResult.next()
          && !audioBookResult.next() && !eBookResult.next())
      {
        //Throw exception if no material was found
        throw new NoSuchElementException("No material was found");
      }
      else
      {
        while (bookResult.next())
        {
          //Add all found books to arraylist
          Book book = new Book(bookResult.getInt("material_id"),
              bookResult.getInt("copy_no"), bookResult.getString("title"),
              bookResult.getString("publisher"),
              String.valueOf(bookResult.getDate("release_date")),
              bookResult.getString("description_of_the_content"),
              bookResult.getString("keywords"),
              bookResult.getString("audience"),
              bookResult.getString("language_"), bookResult.getString("isbn"),
              bookResult.getInt("page_no"), bookResult.getInt("place_id"));
          returnList.add(book);
        }
      }

    }

    return null;
  }

  private ResultSet getQueryResultByTypeTitle(String type, String title)
      throws SQLException
  {
    //Utility method created for getAllMaterialByTitle
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM material JOIN ? USING (material_id) JOIN material_copy USING (material_id) where title = ?");
      stm.setString(1, type);
      stm.setString(2, title);
      return stm.executeQuery();
    }
  }

  //  @Override public Material findByID(int id)
  //  {
  //    return null;
  //  }
}
