package client.model.material.strategy;

import client.model.material.DVD;
import client.model.material.Material;
import client.model.material.MaterialList;
import database.BaseDAO;
import database.material.MaterialDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//Concrete strategy
public class DVDStrategy extends BaseDAO implements SearchStrategy
{
  private String materialType = "dvd";

  public DVDStrategy()
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