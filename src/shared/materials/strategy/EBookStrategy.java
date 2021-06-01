package shared.materials.strategy;

import database.material.EbookDAOImpl;
import shared.materials.Material;

import java.util.List;

/**
 * Concrete e-book search strategy
 *
 * @author Lilian
 * @version 1.0
 */
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
    return EbookDAOImpl.getInstance()
        .findMaterial(title, language, keywords, genre, targetAudience);
  }

}