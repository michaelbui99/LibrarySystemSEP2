package database;

import client.model.material.DVD;
import client.model.material.Material;
import client.model.material.MaterialList;
import client.model.material.audio.AudioBook;
import client.model.material.audio.CD;
import client.model.material.reading.Book;
import client.model.material.reading.EBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SearchMaterial extends BaseDAO // to reach the connection to db
{
  private static SearchMaterial instance;
  private static final Lock lock = new ReentrantLock();
  //Array with the all typies in db
  private static String[] materialCategoryList = new String [] {"audiobook", "book", "cd", "dvd", "e_book"};



  public static SearchMaterial getInstance()
  {
    //Double lock check for Thread safety
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new SearchMaterial();
        }
      }
    }
    return instance;
  }

  public MaterialList findMaterial(String title) throws SQLException
  {
    //list where we store the results
     MaterialList ml = new MaterialList();
     try(Connection connection = getConnection())
     {
       //get resultSet for each category
       for (int i = 0; i < materialCategoryList.length; i++)
       {
         PreparedStatement stm = connection.prepareStatement("SELECT * FROM material "
             + "join " + materialCategoryList[i] + " on material.material_id = " +  materialCategoryList[i]+ ".material_id "
             + "where  material.title LIKE  ('%" + title + "%' )");
         ResultSet resultSet = stm.executeQuery();
         while (resultSet.next()){
           switch (materialCategoryList[i]){

             case "audiobook" :
               AudioBook mat = new AudioBook(resultSet.getInt("material_id"),
                   this.getCopyNumberForMaterial(resultSet.getInt("material_id")),
                   //resultSet.getInt("copy_no"),
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
                   this.getCopyNumberForMaterial(resultSet.getInt("material_id")),
                   //resultSet.getInt("copy_no"),
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
                   this.getCopyNumberForMaterial(resultSet.getInt("material_id")),
                   //resultSet.getInt("copy_no"),
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
                   this.getCopyNumberForMaterial(resultSet.getInt("material_id")),
                   //resultSet.getInt("copy_no"),
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
                     this.getCopyNumberForMaterial(resultSet.getInt("material_id")),
                     //resultSet.getInt("copy_no"),
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

  public int getCopyNumberForMaterial(int materialid){
    int copyno = 0;
    try(Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement("SELECT copy_no FROM material_copy where material_id = " + materialid);
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
}
