package database.material;

import client.model.material.reading.Book;
import database.BaseDAO;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookDAOImpl extends BaseDAO implements BookDAO
{

  private static BookDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static BookDAO getInstance()
  {
    //Double lock check for Thread safety
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new BookDAOImpl();
        }
      }
    }
    return instance;
  }

  @Override public void create(int materialID, String isbn, int pageCount, int authorId,
      int placeID) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "INSERT INTO BOOK(material_id, page_no, author, isbn, place_id) values (?,?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setInt(1, materialID);
      stm.setInt(2, pageCount);
      stm.setInt( 3,authorId);
      stm.setString(4, isbn);
      stm.setInt(5, placeID);
      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      keys.next();
      connection.commit();

    }
  }

  @Override public Book createBookCopy(int materialID, int copyNo)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      //Creates material_copy
      PreparedStatement stm = connection.prepareStatement(
          "INSERT INTO material_copy (material_id, copy_no) VALUES (?,?)");
      stm.setInt(1, materialID);
      stm.setInt(2, copyNo);
      stm.executeUpdate();
      connection.commit();

      //Finds the necessary details to create the Book object from DB.
      ResultSet bookDetails = getBookDetailsByID(materialID);
      if (bookDetails.next())
      {
        //Creates and returns a Book object if a book with given materialID exists.
        return new Book(bookDetails.getInt("material_id"),
            bookDetails.getInt("copy_no"), bookDetails.getString("title"),
            bookDetails.getString("publisher"),
            String.valueOf(bookDetails.getDate("release_date")),
            bookDetails.getString("description_of_the_content"),
            bookDetails.getString("keywords"),
            bookDetails.getString("audience"),
            bookDetails.getString("language_"), bookDetails.getString("isbn"),
            bookDetails.getInt("page_no"), bookDetails.getInt("place_id"), bookDetails.getString("author"));
      }
      return null;
    }
  }

  @Override public ResultSet getBookDetailsByID(int materialID)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM material join material_copy USING (material_id) JOIN book using (material_id) where material_id = ?");
      stm.setInt(1, materialID);
      ResultSet result = stm.executeQuery();
      if (result.next())
      {
        return result;
      }
      else
        throw new NoSuchElementException(
            "No book with materialID " + materialID + " exists.");
    }
  }


}
