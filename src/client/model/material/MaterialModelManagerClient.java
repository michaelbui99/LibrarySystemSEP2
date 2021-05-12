package client.model.material;

import client.model.material.strategy.SearchStrategy;
import client.model.material.strategy.SearchStrategyManager;
import client.network.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class MaterialModelManagerClient implements MaterialModelClient
{


  private Client client;
  private PropertyChangeSupport support;
  private SearchStrategyManager searchStrategyManager;
  private Material selectMaterial;

  public MaterialModelManagerClient(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    searchStrategyManager = new SearchStrategyManager();

  }

  public Material getSelectMaterial()
  {
    return selectMaterial;
  }

  public void setSelectMaterial(Material selectMaterial)
  {
    this.selectMaterial = selectMaterial;

  }

  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      int placeID, int authorId, String genre, String url)
  {

  }
  // TODO: 5/12/2021 REGISTER MATERIAL GUI NEED TO BE MATCHED WITH THE METHOD!! AUTHORID, ISBN, PLACEID
//  public void registerMaterial(String type, String title, String publisher,
//      String length, int page, String description, String tags,
//      String releasedDate, String audience, String language, String isbn){
//    switch (type){
//      case "book":
//        this.registerBook(title,publisher,releasedDate,description,tags,audience,language,isbn,page,placeID, author,genre,url);
//    }
  //}


  @Override public void createBookCopy(int materialID)
  {

  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      String playDuration, int placeID, String genre, String url)
  {

  }

  @Override public void createDVDCopy(int materialID)
  {

  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID,
      String genre, String url)
  {

  }

  @Override public void createCDCopy(int materialID)
  {

  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      String licenseNr, int authorId, String genre, String url)
  {

  }


  @Override public void createEBookCopy(int materialID)
  {

  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, String genre,
      int authorId, String url)
  {

  }

  @Override public void createAudioBookCopy(int materialID)
  {

  }

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience, String type)
  {
     searchStrategyManager.selectStrategy(type);
    return searchStrategyManager.findMaterial(title,language,keywords,genre,targetAudience);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
