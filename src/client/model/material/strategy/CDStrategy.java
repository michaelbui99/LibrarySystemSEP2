package client.model.material.strategy;

import database.material.CDDAOImpl;
import shared.materials.Material;
import database.BaseDAO;
import database.material.MaterialDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class CDStrategy extends BaseDAO implements SearchStrategy
{
  private String materialType = "cd";
  private static final long serialVersionUID = -8799770373490598593L;

  public CDStrategy()
  {

  }


  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience)
  {
    return CDDAOImpl.getInstance().findMaterial(title, language, keywords, genre, targetAudience);
  }
}
