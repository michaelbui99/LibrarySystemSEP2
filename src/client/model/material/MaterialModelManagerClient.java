package client.model.material;

import client.model.material.strategy.SearchStrategy;

import java.sql.SQLException;
import java.util.List;

public class MaterialModelManagerClient implements SearchStrategy
{
  @Override public List<Material> searchAll() throws SQLException
  {
    return null;
  }

  @Override public List<Material> searchTitle(String title) throws SQLException
  {
    return null;
  }

  @Override public List<Material> searchGenre(String genre) throws SQLException
  {
    return null;
  }

  @Override public List<Material> searchTargetAudience(String targetAudience)
      throws SQLException
  {
    return null;
  }

  @Override public List<Material> searchLanguage(String language)
      throws SQLException
  {
    return null;
  }
}
