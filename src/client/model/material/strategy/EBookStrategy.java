package client.model.material.strategy;

import database.material.EbookDAOImpl;
import shared.materials.Material;
import database.material.MaterialDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class EBookStrategy implements SearchStrategy
{
  private String materialType = "e_book";
  private static final long serialVersionUID = -8799770373490598593L;

  public EBookStrategy()
  {

  }
  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience)
  {
    return EbookDAOImpl.getInstance().findMaterial(title, language, keywords, genre, targetAudience);
  }

}