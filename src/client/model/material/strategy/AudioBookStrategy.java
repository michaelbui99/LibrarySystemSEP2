package client.model.material.strategy;

import database.material.AudioBookDAOImpl;
import shared.materials.Material;
import database.material.MaterialDAOImpl;

import java.sql.SQLException;
import java.util.List;

//Concrete strategy
public class AudioBookStrategy  implements SearchStrategy
{
  private String materialType = "audiobook";
  private static final long serialVersionUID = -8799770373490598593L;

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience)
  {
    return AudioBookDAOImpl.getInstance().findMaterial(title, language, keywords, genre, targetAudience);
  }
}
