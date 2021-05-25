package database.material;

import shared.materials.Material;
import shared.materials.MaterialStatus;
import shared.materials.Place;
import shared.materials.reading.Book;
import shared.person.MaterialCreator;
import database.BaseDAO;
import database.materialcreator.MaterialCreatorImpl;
import database.place.PlaceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

  @Override public void create(int materialID, String isbn, int pageCount,
      MaterialCreator author, Place place) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      if ((isbn == null || !isbn.matches(".*\\d.*")) || (pageCount <= 0) || (
          author == null) || (place == null))
      {
        throw new IllegalArgumentException();
      }
      else
      {
        if ((MaterialCreatorImpl.getInstance()
            .getCreatorId(author.getfName(), author.getlName(), author.getDob(),
                author.getCountry()) == -1) || (PlaceImpl.getInstance()
            .getPlaceID(place.getHallNo(), place.getDepartment(),
                place.getCreatorLName(), place.getGenre())) == -1)
        {
          MaterialCreator mc = MaterialCreatorImpl.getInstance()
              .create(author.getfName(), author.getlName(), author.getDob(),
                  author.getCountry());
          Place p = PlaceImpl.getInstance()
              .create(place.getHallNo(), place.getDepartment(),
                  place.getCreatorLName(), place.getGenre());
          PreparedStatement stm = connection.prepareStatement(
              "INSERT INTO BOOK(material_id, page_no, author, isbn, place_id) values (?,?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
          stm.setInt(1, materialID);
          stm.setInt(2, pageCount);
          stm.setInt(3, mc.getPersonId());
          stm.setString(4, isbn);
          stm.setInt(5, p.getPlaceId());
          stm.executeUpdate();
          ResultSet keys = stm.getGeneratedKeys();
          keys.next();
          connection.commit();
        }
        else
        {
          int mcId = MaterialCreatorImpl.getInstance()
              .getCreatorId(author.getfName(), author.getlName(),
                  author.getDob(), author.getCountry());
          int pId = PlaceImpl.getInstance()
              .getPlaceID(place.getHallNo(), place.getDepartment(),
                  place.getCreatorLName(), place.getGenre());

          PreparedStatement stm = connection.prepareStatement(
              "INSERT INTO BOOK(material_id, page_no, author, isbn, place_id) values (?,?,?,?,?)",
              PreparedStatement.RETURN_GENERATED_KEYS);
          stm.setInt(1, materialID);
          stm.setInt(2, pageCount);
          stm.setInt(3, mcId);
          stm.setString(4, isbn);
          stm.setInt(5, pId);
          stm.executeUpdate();
          ResultSet keys = stm.getGeneratedKeys();
          keys.next();
          connection.commit();
        }
      }
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
      //ResultSet keys = stm.getGeneratedKeys();
      connection.commit();

      //Finds the necessary details to create the Book object from DB.
      ResultSet bookDetails = getBookDetailsByID(materialID);
      if (bookDetails.next())
      {
        //Creates and returns a Book object if a book with given materialID exists.
        List<String> materialKeywordList = MaterialDAOImpl.getInstance()
            .getKeywordsForMaterial(bookDetails.getInt("material_id"));
        String materialKeywords = String.join(", ", materialKeywordList);

        return new Book(bookDetails.getInt("material_id"),
            bookDetails.getInt("copy_no"), bookDetails.getString("title"),
            bookDetails.getString("publisher"),
            String.valueOf(bookDetails.getDate("release_date")),
            bookDetails.getString("description_of_the_content"),
            materialKeywords, bookDetails.getString("audience"),
            bookDetails.getString("language_"), bookDetails.getString("isbn"),
            bookDetails.getInt("page_no"),
            new Place(bookDetails.getInt("hall_no"),
                bookDetails.getString("department"),
                bookDetails.getString("creator_l_name"),
                bookDetails.getString("genre")),
            new MaterialCreator(bookDetails.getString("f_name"),
                bookDetails.getString("l_name"),
                String.valueOf(bookDetails.getDate("dob")),
                bookDetails.getString("country")));
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
          "SELECT * FROM material join material_copy USING (material_id) JOIN book using (material_id) join material_creator mc on book.author = mc.person_id join place p on book.place_id = p.place_id where material_id = ?");
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

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience)

  {
    //list where we store the results
    List<Material> ml = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      List<String> queryFragments = new ArrayList<>();
      String sql = "SELECT * FROM material "
          + "join book  on material.material_id = book.material_id  "
          + "join material_copy mt on book.material_id = mt.material_id "
          + "join place p on book.place_id = p.place_id "
          + "join material_creator mc on book.author = mc.person_id ";
      if (!title.isEmpty() || !language.isEmpty() || !genre.isEmpty()
          || !targetAudience.isEmpty())
      {
        sql += "where ";
      }

      if (!title.isEmpty())
      {
        queryFragments
            .add(" LOWER(material.title) LIKE  LOWER('%" + title + "%') ");
      }
      if (!language.isEmpty())
      {
        queryFragments.add(" material.language_  = '" + language + "' ");
      }
      if (!genre.isEmpty())
      {
        queryFragments
            .add(" LOWER(material.genre) LIKE LOWER('%" + genre + "%')");
      }
      if (!targetAudience.isEmpty())
      {
        queryFragments.add(" material.audience = '" + targetAudience + "' ");
      }
      sql += String.join(" and ", queryFragments);
      PreparedStatement stm = connection.prepareStatement(sql);
      ResultSet resultSet = stm.executeQuery();
      while (resultSet.next())
      {
        //find all keywords related to this material id
        List<String> materialKeywordList = MaterialDAOImpl.getInstance()
            .getKeywordsForMaterial(resultSet.getInt("material_id"));
        String materialKeywords = String.join(", ", materialKeywordList);
        boolean match = false;
        if (!keywords.isEmpty())
        { //if keywords were specified in search, compare them to material keywords from DB (materialKeywordList)
          for (int i = 0; i < keywords.split(",").length; i++)
          {
            if (materialKeywords.toLowerCase(Locale.ROOT)
                .contains(keywords.split(",")[i].toLowerCase(Locale.ROOT)))
            {
              match = true; //search keyword matched material keyword - material will be added to result list
              break;
            }
          }
        }
        else
        {
          match = true; //if no keywords were specified by user - just add material keywords from DB to its material
        }
        if (match)
        {

          Book book = new Book(resultSet.getInt("material_id"),
              MaterialDAOImpl.getInstance()
                  .getCopyNumberForMaterial(resultSet.getInt("material_id")),
              resultSet.getString("title"), resultSet.getString("publisher"),
              String.valueOf(resultSet.getDate("release_date")),
              resultSet.getString("description_of_the_content"), "",
              resultSet.getString("audience"), resultSet.getString("language_"),
              resultSet.getString("isbn"), resultSet.getInt("page_no"),
              new Place(resultSet.getInt("hall_no"),
                  resultSet.getString("department"),
                  resultSet.getString("creator_l_name"),
                  resultSet.getString("genre")),
              new MaterialCreator(resultSet.getString("f_name"),
                  resultSet.getString("l_name"),
                  String.valueOf(resultSet.getDate("dob")),
                  resultSet.getString("country")));
          book.setMaterialStatus(MaterialDAOImpl.getInstance()
              .checkIfCopyAvailable(MaterialDAOImpl.getInstance()
                  .getCopyNumberForMaterial(resultSet.getInt("material_id"))) ?
              MaterialStatus.Available :
              MaterialStatus.NotAvailable);
          book.setKeywords(materialKeywords);
          System.out.println("Keywords: " + book.getKeywords());
          ml.add(book);
        }
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    System.out.println("result size: " + ml.size());
    return ml;
  }

  @Override public void deleteBookCopy(int materialID, int copyNumber)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "DELETE FROM material_copy WHERE material_id = ? AND copy_no = ?",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setInt(1, materialID);
      stm.setInt(2, copyNumber);
      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      connection.commit();
      keys.next();
    }
  }

  @Override public boolean bookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String isbn, int pageCount, MaterialCreator author,
      String genre) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM material JOIN book b USING (material_id) JOIN material_creator mc ON b.author = mc.person_id WHERE title = ? AND audience = ? AND description_of_the_content = ? AND publisher = ? AND language_ = ? AND release_date = ? AND genre = ? AND page_no = ? AND isbn = ? AND f_name = ? AND l_name = ? AND dob = ? AND country = ?");
      stm.setString(1, title);
      stm.setString(2, targetAudience);
      stm.setString(3, description);
      stm.setString(4, publisher);
      stm.setString(5, language);
      stm.setDate(6, Date.valueOf(releaseDate));
      stm.setString(7, genre);
      stm.setInt(8, pageCount);
      stm.setString(9, isbn);
      stm.setString(10, author.getfName());
      stm.setString(11, author.getlName());
      stm.setDate(12, Date.valueOf(author.getDob()));
      stm.setString(13, author.getCountry());
      ResultSet result = stm.executeQuery();
      return result.next();
    }
  }
}
