package client.model.material;

import client.model.material.audio.AudioBook;
import client.model.material.audio.CD;
import client.model.material.reading.Book;
import client.model.material.reading.EBook;
import database.BaseDAO;
import database.material.MaterialDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AllStrategy extends BaseDAO implements
    MaterialFilterStrategyInterface
{
  private static AllStrategy instance;
  private static final Lock lock = new ReentrantLock();

  private static String[] materialCategoryList = new String [] {"all", "audiobook", "book", "cd", "dvd", "e_book"};
  public static AllStrategy getInstance()
  {

    //Double lock check for Thread safety
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new AllStrategy();
        }
      }
    }
    return instance;
  }

  @Override public MaterialList findMaterial(String title, String language,
      String keywords, String genre, String targetAudience) throws SQLException
  {
    //list where we store the results
    MaterialList ml = new MaterialList();
    try(Connection connection = getConnection())
    {
      //get resultSet for each category
      for (int i = 0; i < materialCategoryList.length; i++)
      {
        String sql = "SELECT * FROM material join " + materialCategoryList[i] + " on material.material_id = " +  materialCategoryList[i]+ ".material_id  "
            + "where  material.title LIKE  ('%" + title + "%')";
        if(language != ""){
          sql += " and material.language LIKE  ('%" + language + "%' ) ";
        }
        if(keywords != ""){
          sql += " and material.keywords LIKE  ('%" + keywords + "%' ) ";
        }
        if(genre != ""){
          sql += " and material.genre LIKE  ('%" + genre + "%' ) ";
        }
        if(targetAudience != ""){
          sql += " and material.targetAudience LIKE  ('%" + targetAudience + "%' ) ";
        }
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet resultSet = stm.executeQuery();
        while (resultSet.next()){
          switch (materialCategoryList[i]){

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
                  resultSet.getString("length_"));
              ml.addMaterial(mat);
              break;

            case "book" :
              ml.addMaterial(new Book(resultSet.getInt("material_id"),
                  MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
                  resultSet.getString("title"),
                  resultSet.getString("publisher"),
                  String.valueOf(resultSet.getDate("release_date")),
                  resultSet.getString("description_of_the_content"),
                  resultSet.getString("keywords"),
                  resultSet.getString("audience"),
                  resultSet.getString("language_"),
                  resultSet.getString("isbn"),
                  resultSet.getInt("page_no"), resultSet.getInt("place_id")));
              break;

            case "cd":
              ml.addMaterial(new CD(resultSet.getInt("material_id"),
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
              break;

            case "dvd":
              ml.addMaterial(new DVD(resultSet.getInt("material_id"),
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
              ml.addMaterial(new EBook(resultSet.getInt("material_id"),
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
                  resultSet.getString("author"),
                  resultSet.getString("genre")));
              break;
          }
        }
      }
    }
    return ml;
  }
}
