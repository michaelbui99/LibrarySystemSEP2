package shared.materials.strategy;

import database.material.BookDAOImpl;
import shared.materials.Material;

import java.util.List;

/**
 * Concrete book search strategy
 *
 * @author Lilian
 * @version 1.0
 */
public class BookStrategy implements SearchStrategy
{
  private String materialType = "book";
  private static final long serialVersionUID = -1663825955959143816L;

  public BookStrategy()
  {

  }

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience)
  {
    return BookDAOImpl.getInstance()
        .findMaterial(title, language, keywords, genre, targetAudience);
  }
}



