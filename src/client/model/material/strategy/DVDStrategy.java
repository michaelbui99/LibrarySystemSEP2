package client.model.material.strategy;

import database.material.DVDDAOImpl;
import shared.materials.Material;
import database.BaseDAO;
import database.material.MaterialDAOImpl;

import java.sql.SQLException;
import java.util.List;

//Concrete strategy
public class DVDStrategy extends BaseDAO implements SearchStrategy
{
  private String materialType = "dvd";
  private static final long serialVersionUID = -8799770373490598593L;

  public DVDStrategy()
  {

  }


  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience)
  {
    return DVDDAOImpl.getInstance().findMaterial(title, language, keywords, genre, targetAudience);
  }
}