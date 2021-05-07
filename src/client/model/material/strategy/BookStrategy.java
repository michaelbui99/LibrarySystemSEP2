package client.model.material.strategy;

import client.model.material.Material;
import client.model.material.reading.Book;
import client.model.material.MaterialList;
import database.BaseDAO;
import database.material.MaterialDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Concrete strategy
public class BookStrategy implements SearchStrategy
{
  private String materialType = "book";

  public BookStrategy()
  {

  }

  @Override public List<Material> searchAll() throws SQLException
  {
    return MaterialDAOImpl.getInstance()
        .findMaterial("", "", "", "", "", materialType);
  }

  @Override public List<Material> searchTitle(String title) throws SQLException
  {

    return MaterialDAOImpl.getInstance()
        .findMaterial(title, "", "", "", "", materialType);
  }

  @Override public List<Material> searchGenre(String genre) throws SQLException
  {
    return MaterialDAOImpl.getInstance()
        .findMaterial("", "", "", genre, "", materialType);

  }

  @Override public List<Material> searchTargetAudience(String targetAudience)
      throws SQLException
  {
    return MaterialDAOImpl.getInstance()
        .findMaterial("", "", "", "", targetAudience, materialType);

  }

  @Override public List<Material> searchLanguage(String language) throws SQLException
  {
    return MaterialDAOImpl.getInstance()
        .findMaterial("", language, "", "", "", materialType);

  }
}
  /*
  @Override public List<Material> searchAuthor(String author)
  {
 List<Material> ml = new ArrayList<>();
    Connection connection = null;
    try
    {
      connection = getConnection();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    String sql = "SELECT * FROM material " //flytter den til material dao og
        + "join book on material.material_id = book.material_id "
        + "where material.author LIKE  (?) ";
    PreparedStatement stm = null;
    try
    {
      stm = connection.prepareStatement(sql);
      ResultSet resultSet = stm.executeQuery();
      while (resultSet.next())
      {
        Book dan = new Book(resultSet.getInt("material_id"),
            MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id")),
            resultSet.getString("title"), resultSet.getString("publisher"),
            String.valueOf(resultSet.getDate("release_date")),
            resultSet.getString("description_of_the_content"),
            resultSet.getString("keywords"), resultSet.getString("audience"),
            resultSet.getString("language_"), resultSet.getString("isbn"),
            resultSet.getInt("page_no"), resultSet.getInt("place_id"),
            resultSet.getString("author"));

          ml.add(dan);
      }
    }
    catch(SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return ml;
  }
  */



