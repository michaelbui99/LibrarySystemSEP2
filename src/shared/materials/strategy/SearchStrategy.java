package shared.materials.strategy;

import shared.materials.Material;

import java.io.Serializable;
import java.util.List;

/**
 * Concrete search strategy interface
 *
 * @author Lilian
 * @version 1.0
 */
public interface SearchStrategy extends Serializable
{
  /**
   * Returns a list of materials based on the given arguments
   *
   * @param title          The material's title
   * @param language       The material's language
   * @param keywords       The material's keywords
   * @param genre          The material's genre
   * @param targetAudience The material's target audience
   * @return a list of materials
   */
  List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience);

}
