package client.model.material.strategy;

import shared.materials.Material;

import java.sql.SQLException;
import java.util.List;

public interface SearchStrategy
{
  //Strategy
  List<Material> searchAll() throws SQLException;
  List<Material> searchTitle(String title) throws SQLException;
  List<Material> searchGenre(String genre) throws SQLException;
  List<Material> searchTargetAudience(String targetAudience) throws SQLException;
  List<Material> searchLanguage(String language) throws SQLException;
}
