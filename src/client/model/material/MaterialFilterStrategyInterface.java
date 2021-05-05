package client.model.material;

import java.sql.SQLException;

public interface MaterialFilterStrategyInterface
{
  //Strategy
  MaterialList findMaterial (String title, String language, String keywords, String genre,String targetAudience)
      throws SQLException;
}
