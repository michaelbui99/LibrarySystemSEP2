package database.material;

import client.model.material.DVD;
import client.model.material.Material;
import client.model.material.MaterialList;
import client.model.material.audio.AudioBook;
import client.model.material.audio.CD;
import client.model.material.reading.Book;
import client.model.material.reading.EBook;
import database.BaseDAO;

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

  //calculate how many copies for each material
  public int getCopyNumberForMaterial(int materialid){
    int copyno = 0;
    try(Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement("SELECT count (*) as copy_no  FROM material_copy where material_id = " + materialid);
      ResultSet resultSet = stm.executeQuery();
      if(resultSet.next()){
        return resultSet.getInt("copy_no");
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return copyno;
  }



 /* @Override public List<Material> getAllMaterialsByTitle(String title, String materialType)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      List<Material> returnList = new ArrayList<>();
      ResultSet resultSet = getQueryResultByTypeTitle(materialType, title);
      if (resultSet.next())
      {
        while (resultSet.next())
        {
          if(materialType.equals("book")){
            //Add all found books to arraylist
            Book book = new Book(resultSet.getInt("material_id"),
                resultSet.getInt("copy_no"), resultSet.getString("title"),
                resultSet.getString("publisher"),
                String.valueOf(resultSet.getDate("release_date")),
                resultSet.getString("description_of_the_content"),
                resultSet.getString("keywords"), resultSet.getString("audience"),
                resultSet.getString("language_"), resultSet.getString("isbn"),
                resultSet.getInt("page_no"), resultSet.getInt("place_id"),
                resultSet.getString("author"));
            returnList.add(book);
          }
          else if(materialType.equals("audiobook")){
            AudioBook audioBook = new AudioBook(resultSet.getInt("material_id"),
                MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
                resultSet.getString("title"),
                resultSet.getString("publisher"),
                String.valueOf(resultSet.getDate("release_date")),
                resultSet.getString("description_of_the_content"),
                resultSet.getString("keywords"),
                resultSet.getString("audience"),
                resultSet.getString("language_"),
                resultSet.getString("length_"));
            returnList.add(audioBook);
          }
          else if(materialType.equals("e_book")){
            EBook eBook = new EBook(resultSet.getInt("material_id"),
                MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
                resultSet.getString("title"),
                resultSet.getString("publisher"),
                String.valueOf(resultSet.getDate("release_date")),
                resultSet.getString("description_of_the_content"),
                resultSet.getString("keywords"),
                resultSet.getString("audience"),
                resultSet.getString("language_"),
                resultSet.getInt("page_no"),
                resultSet.getString("license_no"),
                resultSet.getString("genre"),
                resultSet.getString("author"));
             returnList.add(eBook);
          }
          else if(materialType.equals("dvd")){
            DVD dvd = new DVD(resultSet.getInt("material_id"),
                MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
                resultSet.getString("title"),
                resultSet.getString("publisher"),
                String.valueOf(resultSet.getDate("release_date")),
                resultSet.getString("description_of_the_content"),
                resultSet.getString("keywords"),
                resultSet.getString("audience"),
                resultSet.getString("language_"),
                resultSet.getString("subtitle_lang"),
                resultSet.getString("length_"),
                resultSet.getInt("place_id"));
            returnList.add(dvd);
          }
          else if(materialType.equals("cd")){
            CD cd = (new CD(resultSet.getInt("material_id"),
                MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
                resultSet.getString("title"),
                resultSet.getString("publisher"),
                String.valueOf(resultSet.getDate("release_date")),
                resultSet.getString("description_of_the_content"),
                resultSet.getString("keywords"),
                resultSet.getString("audience"),
                resultSet.getString("language_"),
                resultSet.getString("length_"),
                resultSet.getInt("place_id")));
            returnList.add(cd);
        }
        }
      }
      else
      {
        throw new NoSuchElementException("No material was found");
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
              bookResult.getInt("page_no"), bookResult.getInt("place_id"),
              bookResult.getString("author"));
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
            dvdResult.getString("length_"), dvdResult.getInt("place_id"));
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
            cdResult.getString("language_"), cdResult.getString("length_"),
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

  public List<AudioBook> getAllAudioBooksByTitle(String title)
      throws SQLException
  {
    List<AudioBook> returnList = new ArrayList<>();
    ResultSet audioBookResult = getQueryResultByTypeTitle("audiobook", title);
    if (audioBookResult.next())
    {
      while (audioBookResult.next())
      {
        System.out.println("audiobook found"); //Debugging

        //Add all found audiobooks to arraylist.
        AudioBook audioBook = new AudioBook(
            audioBookResult.getInt("material_id"),
            audioBookResult.getInt("copy_no"),
            audioBookResult.getString("title"),
            audioBookResult.getString("publisher"),
            String.valueOf(audioBookResult.getDate("release_date")),
            audioBookResult.getString("description_of_the_content"),
            audioBookResult.getString("keywords"), audioBookResult.getString("audience"),
            audioBookResult.getString("language_"), audioBookResult.getString("length_"));
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
            eBookResult.getString("language_"),
            eBookResult.getInt("page_no"),
            String.valueOf(eBookResult.getInt("license_no")),
            eBookResult.getString("genre"),
            eBookResult.getString("author"));
        returnList.add(eBook);
      }
    }
    else
    {
      throw new NoSuchElementException("No material was found");
    }
    return returnList;
  }



  public List<Book> getAllBooks() throws SQLException
  {
    List<Book> returnList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      ResultSet bookResult = getQueryResultByType("book");
      if (bookResult.next())
      {
        while (bookResult.next())
        {
          Book book = new Book(bookResult.getInt("material_id"),
              bookResult.getInt("copy_no"), bookResult.getString("title"),
              bookResult.getString("publisher"),
              String.valueOf(bookResult.getDate("release_date")),
              bookResult.getString("description_of_the_content"),
              bookResult.getString("keywords"),
              bookResult.getString("audience"),
              bookResult.getString("language_"), bookResult.getString("isbn"),
              bookResult.getInt("page_no"), bookResult.getInt("place_id"),
              bookResult.getString("author"));
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

  public List<EBook> getAllEbooks() throws SQLException
  {
    List<EBook> returnList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      ResultSet ebookResult = getQueryResultByType("ebook");
      if (ebookResult.next())
      {
        while (ebookResult.next())
        {
          EBook ebook = new EBook(ebookResult.getInt("material_id"),
              ebookResult.getInt("copy_no"), ebookResult.getString("title"),
              ebookResult.getString("publisher"),
              String.valueOf(ebookResult.getDate("release_date")),
              ebookResult.getString("description_of_the_content"),
              ebookResult.getString("keywords"),
              ebookResult.getString("audience"),
              ebookResult.getString("language_"), ebookResult.getInt("page_no"),
              String.valueOf(ebookResult.getInt("license_no")),
              String.valueOf(ebookResult.getInt("author")),
              ebookResult.getString("genre"));
          returnList.add(ebook);
        }
      }
      else
      {
        throw new NoSuchElementException("No material was found");
      }
      return returnList;
    }
  }

  public List<AudioBook> getAllAudioBooks() throws SQLException
  {
    List<AudioBook> returnList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      ResultSet audiobookResult = getQueryResultByType("audiobook");
      if (audiobookResult.next())
      {
        while (audiobookResult.next())
        {
          AudioBook audioBook = new AudioBook(
              audiobookResult.getInt("material_id"),
              audiobookResult.getInt("copy_no"),
              audiobookResult.getString("title"),
              audiobookResult.getString("publisher"),
              String.valueOf(audiobookResult.getDate("release_date")),
              audiobookResult.getString("description_of_the_content"),
              audiobookResult.getString("keywords"),
              audiobookResult.getString("audience"),
              audiobookResult.getString("language_"),
              String.valueOf(audiobookResult.getDouble("length_")));
          returnList.add(audioBook);
        }
      }
      else
      {
        throw new NoSuchElementException("No material was found");
      }
      return returnList;
    }

  }

  public List<DVD> getAllDVDs() throws SQLException
  {
    List<DVD> returnList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      ResultSet dvdResult = getQueryResultByType("dvd");
      if (dvdResult.next())
      {
        while (dvdResult.next())
        {
          DVD dvd = new DVD(dvdResult.getInt("material_id"),
              dvdResult.getInt("copy_no"), dvdResult.getString("title"),
              dvdResult.getString("publisher"),
              String.valueOf(dvdResult.getDate("release_date")),
              dvdResult.getString("description_of_the_content"),
              dvdResult.getString("keywords"), dvdResult.getString("audience"),
              dvdResult.getString("language_"),
              dvdResult.getString("subtitle_lang"),
              String.valueOf(dvdResult.getDouble("length_")),
              dvdResult.getInt("place_id"));
          returnList.add(dvd);
        }
      }
      else
      {
        throw new NoSuchElementException("No material was found");
      }
      return returnList;
    }
  }

  public List<CD> getAllCDs() throws SQLException
  {
    List<CD> returnList = new ArrayList<>();
    try (Connection connection = getConnection())
    {
      ResultSet cdResult = getQueryResultByType("cd");
      if (cdResult.next())
      {
        while (cdResult.next())
        {
          CD cd = new CD(cdResult.getInt("material_id"),
              cdResult.getInt("copy_no"), cdResult.getString("title"),
              cdResult.getString("publisher"),
              String.valueOf(cdResult.getDate("release_date")),
              cdResult.getString("description_of_the_content"),
              cdResult.getString("keywords"), cdResult.getString("audience"),
              cdResult.getString("language_"),
              String.valueOf(cdResult.getDouble("length_")), cdResult.getInt("place_id"));
          returnList.add(cd);
        }
      }
      else
      {
        throw new NoSuchElementException("No material was found");
      }
    }
    return returnList;
  }

  private ResultSet getQueryResultByType(String type) throws SQLException
  {
    String[] safeTables = {"book", "audiobook", "cd", "dvd", "e_book"};
    if (!Arrays.asList(safeTables).contains(type))
    {
      throw new IllegalArgumentException("Illegal material type");
    }

    try (Connection connection = getConnection())
    {
      ResultSet resultSet = null;
      if (type.equals("books") || type.equals("audiobook") || type.equals("e_book"))
      {
        PreparedStatement stm = connection.prepareStatement(
            "SELECT * FROM material JOIN " + type + " USING (material_id) JOIN material_copy USING (material_id) join material_creator mc on mc.person_id = " + type+".author");
        return stm.executeQuery();
      }else
      {
        PreparedStatement stm = connection.prepareStatement(
            "SELECT * FROM material JOIN " + type + " USING (material_id) JOIN material_copy USING (material_id)");
        return stm.executeQuery();
      }

    }
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
          "SELECT * FROM material JOIN " + type
              + " USING (material_id) JOIN material_copy USING (material_id) where title = ? ");
      stm.setString(1, title);
      return stm.executeQuery();
    }
  }
  */

  public boolean deliverMaterial(int materialID, String cpr, int copy_no){

    try(Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement("update loan set return_date = now() where "
          + " material_id = " + materialID +
          " and copy_no = " + copy_no + " and cpr_no = '" + cpr + "';");
      int res = stm.executeUpdate();
      connection.commit();
      if (res > 0){
        return true;
      }
      return false;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return false;
  }
  //  @Override public Material findByID(int id)
  //  {
  //    return null;
  //  }


 @Override public List<Material> findMaterial(String title, String language, String keywords, String genre, String targetAudience, String type) throws SQLException
  {

    //list where we store the results
    List<Material> ml = new ArrayList<>();
    try(Connection connection = getConnection())
    {
      //get resultSet for each category

        List<String> queryFragments = new ArrayList<>();
        String sql = "SELECT * FROM material join " + type + " on material.material_id = " +  type + ".material_id  ";

        if(!title.isEmpty() ||
            !language.isEmpty() ||
            !keywords.isEmpty() ||
            !genre.isEmpty() ||
            !targetAudience.isEmpty()) {
          sql += "where ";
        }

        if(!title.isEmpty()){
          queryFragments.add(" material.title LIKE  ('%" + title + "%') ");
        }
        if(!language.isEmpty()){
          queryFragments.add(" material.language_ LIKE  ('%" + language + "%' ) ");
        }
        if(!keywords.isEmpty()){
          queryFragments.add(" material.keywords LIKE  ('%" + keywords + "%' ) ");
        }
        if(!genre.isEmpty()){
          queryFragments.add(" material.genre LIKE  ('%" + genre + "%' ) ");
        }
        if(!targetAudience.isEmpty()){
          queryFragments.add(" material.audience LIKE  ('%" + targetAudience + "%' ) ");
        }

        sql += String.join(" and ", queryFragments);

        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet resultSet = stm.executeQuery();
        while (resultSet.next()){
          switch (type){

            case "audiobook" :
              AudioBook mat = new AudioBook(resultSet.getInt("material_id"),
                  MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"),
                  resultSet.getString("keywords"),
                  resultSet.getString("audience"),
                  resultSet.getString("language_"),
                  resultSet.getInt("length_"),
                  resultSet.getString("author"));
              ml.add(mat);
              break;

            case "book" :
              ml.add(new Book(resultSet.getInt("material_id"),
                  MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"),
                  resultSet.getString("keywords"),
                  resultSet.getString("audience"),
                  resultSet.getString("language_"),
                  resultSet.getString("isbn"),
                  resultSet.getInt("page_no"), resultSet.getInt("place_id"),
                  resultSet.getString("author")));
              break;

            case "cd":
              ml.add(new CD(resultSet.getInt("material_id"),
                  MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"),
                  resultSet.getString("keywords"),
                  resultSet.getString("audience"),
                  resultSet.getString("language_"),
                  resultSet.getInt("length_"),
                  resultSet.getInt("place_id")));
              break;

            case "dvd":
              ml.add(new DVD(resultSet.getInt("material_id"),
                  MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"),
                  resultSet.getString("keywords"),
                  resultSet.getString("audience"),
                  resultSet.getString("language_"),
                  resultSet.getString("subtitle_lang"),
                  resultSet.getString("length_"),
                  resultSet.getInt("place_id")));
              break;

            case "e_book":
              ml.add(new EBook(resultSet.getInt("material_id"),
                  MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"),
                  resultSet.getString("keywords"),
                  resultSet.getString("audience"),
                  resultSet.getString("language_"),
                  resultSet.getInt("page_no"),
                  resultSet.getString("license_no"),
                  resultSet.getString("genre"),
                  resultSet.getString("author")));
              break;
          }
        }
      }

    return ml;
  }
}
