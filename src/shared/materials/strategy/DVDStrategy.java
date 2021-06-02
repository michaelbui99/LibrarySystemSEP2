package shared.materials.strategy;

import database.material.DVDDAOImpl;
import shared.materials.Material;
import database.BaseDAO;

import java.util.List;

/**
 * Concrete DVD search strategy
 *
 * @author Lilian
 * @version 1.0
 */
public class DVDStrategy implements SearchStrategy
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