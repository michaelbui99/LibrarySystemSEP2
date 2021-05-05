package client.model.material;

import client.model.material.audio.AudioBookStrategy;
import client.model.material.audio.CDStrategy;
import client.model.material.reading.BookStrategy;
import client.model.material.reading.EBookStrategy;
import database.BaseDAO;
import database.MaterialDAOImpl;

import java.sql.SQLException;

public class MaterialSearchStrategyNavigator implements MaterialFilterStrategyInterface
{
  private String type;
  private MaterialFilterStrategyInterface materialFilterStrategyInterface;

  private String materialTypes[] = new String[]{"all","audiobook", "book", "cd", "dvd", "e_book"};

  public MaterialSearchStrategyNavigator(String type)
  {
    this.type = type;
  }

  public void setMaterialFilterStrategyInterface(
      MaterialFilterStrategyInterface materialFilterStrategyInterface)
  {
    this.materialFilterStrategyInterface = materialFilterStrategyInterface;
  }

  public void setType(String type){
    this.type = type;
  }

  @Override public MaterialList findMaterial(String title, String language,
      String keywords, String genre, String targetAudience) throws SQLException
  {
    materialFilterStrategyInterface.findMaterial(title,language,keywords,genre,targetAudience);
    MaterialList ml = new MaterialList();

    switch (this.type){
      case "all":
        ml = AllStrategy.getInstance().findMaterial(title, language,
             keywords, genre, targetAudience);
        break;
      case "audiobook":
        ml = AudioBookStrategy.getInstance().findMaterial(title, language,
             keywords, genre, targetAudience);
        break;
      case "book":
        ml = BookStrategy.getInstance().findMaterial(title, language, keywords,
            genre, targetAudience);
        break;
      case "cd":
        ml = CDStrategy.getInstance().findMaterial(title, language, keywords,genre, targetAudience);
        break;
      case "dvd":
        ml = DVDStrategy.getInstance().findMaterial(title, language, keywords, genre, targetAudience);
        break;
      case "e_book":
        ml = EBookStrategy.getInstance().findMaterial(title, language, keywords, genre, targetAudience);
        break;
    }

    return materialFilterStrategyInterface.findMaterial(title,language,keywords,genre,targetAudience);
  }
}
