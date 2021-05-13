package database.material;

import client.model.material.DVD;
import client.model.material.Material;
import client.model.material.audio.AudioBook;
import client.model.material.audio.CD;
import client.model.material.reading.Book;
import client.model.material.reading.EBook;
import database.BaseDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
      String releaseDate, String description, String targetAudience, String language, String genre, String url)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          "INSERT INTO Material ( title, audience, description_of_the_content, publisher,  language_, release_date, genre, url) values (?,?,?,?,?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setString(1, title);
      stm.setString(2, targetAudience);
      stm.setString(3, description);
      stm.setString(4, publisher);
      stm.setString(5, language);
      stm.setDate(6, Date.valueOf(releaseDate));
      stm.setString(7, genre);
      stm.setString(8, url);

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
     // i can not put a question mark because i do not know how many parameters are we searching by and it could be zero and it could be 5
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
                  resultSet.getString("author"), resultSet.getString("url") );
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
                  resultSet.getInt("place_id"), resultSet.getString("url")));
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
                  resultSet.getInt("place_id"), resultSet.getString("url")));
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

  @Override public boolean returnMaterial(int materialID, String cpr,
      int copy_no)
  {
    return false;
  }
}
