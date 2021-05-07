package client.model.material.strategy;

import client.model.material.Material;
import client.model.material.MaterialList;

import java.sql.SQLException;
import java.util.List;

//navigator

public class SearchStrategyManager  implements SearchStrategy
{
  private String type;
  private SearchStrategy searchStrategy;

  public SearchStrategyManager(){

  }// searchStrategyManager.selectStrategy(bookStrategy)

  public void setMaterialFilterStrategyInterface(
      SearchStrategy searchStrategy)
  {
    this.searchStrategy = searchStrategy;

  }


  public void selectStrategy(SearchStrategy searchStrategy)
  {
    this.searchStrategy = searchStrategy;


  }

  @Override public List<Material> searchAll() throws SQLException
  {
    return this.searchStrategy.searchAll();
  }

  @Override public List<Material> searchTitle(String title) throws SQLException
  {
    return this.searchStrategy.searchTitle(title);
  }

  @Override public List<Material> searchGenre(String genre) throws SQLException
  {
    return this.searchStrategy.searchGenre(genre);
  }

  @Override public List<Material> searchTargetAudience(String targetAudience)
      throws SQLException
  {
    return this.searchStrategy.searchTargetAudience(targetAudience);
  }

  @Override public List<Material> searchLanguage(String language)
      throws SQLException
  {
    return this.searchStrategy.searchLanguage(language);
  }

}

