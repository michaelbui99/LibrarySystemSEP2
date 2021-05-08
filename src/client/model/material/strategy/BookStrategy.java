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



