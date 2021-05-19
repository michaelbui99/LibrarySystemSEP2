package client.model.material.strategy;

import database.material.BookDAOImpl;
import shared.materials.Material;
import database.material.MaterialDAOImpl;

import java.sql.SQLException;
import java.util.List;

//Concrete strategy
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
    return BookDAOImpl.getInstance().findMaterial(title, language, keywords, genre, targetAudience);
  }
}



