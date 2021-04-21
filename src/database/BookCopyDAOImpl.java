package database;

import client.model.material.reading.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookCopyDAOImpl extends BaseDAO implements BookCopyDAO
{
  private static BookCopyDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static BookCopyDAO getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new BookCopyDAOImpl();
        }
      }
    }
    return instance;
  }

  @Override public Book create(int materialID, int copyNo, String isbn,
      int pageCount) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      //Inserts a new bookcopy into the DB.
      PreparedStatement stm = connection.prepareStatement(
          "INSERT INTO bogkopi(materialeid, kopino, isbn, sidetal) values (?,?,?,?)");
      stm.setInt(1, materialID);
      stm.setInt(2, copyNo);
      stm.setString(3, isbn);
      stm.setInt(4, pageCount);
      stm.executeUpdate();
      connection.commit();


      /*
       * Uses the getBookDetailsById to find the necessary information to create
       * a java Book object
       * */
      ResultSet bookDetails = getBookDetailsById(materialID);
      if (bookDetails.next())
      {
        return new Book(bookDetails.getInt("materialeid"),
            bookDetails.getInt("kopino"), bookDetails.getString("titel"),
            bookDetails.getString("forlag"),
            String.valueOf(bookDetails.getDate("udgivelsesdato")),
            bookDetails.getString("beskrivelseafindholdet"),
            bookDetails.getString("emneord"),
            bookDetails.getString("målgruppe"), bookDetails.getString("sprog"),
            bookDetails.getString("isbn"), bookDetails.getInt("sidetal"));
      }
      return null;

    }
  }

  @Override public ResultSet getBookDetailsById(int materialID)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * from bogkopi JOIN materialekopi using(materialeid, kopino) join materiale USING (materialeid) where materialeid = ?");
      stm.setInt(1, materialID);
      return stm.executeQuery();
    }
  }
}
