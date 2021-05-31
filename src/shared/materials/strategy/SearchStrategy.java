package shared.materials.strategy;

import shared.materials.Material;

import java.io.Serializable;
import java.util.List;

public interface SearchStrategy extends Serializable
{
  //Strategy
  List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience);

}
