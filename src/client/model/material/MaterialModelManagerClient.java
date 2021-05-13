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
    client.registerBook(title, publisher, releaseDate, description, tags,
        targetAudience, language, isbn, pageCount, placeID, authorId, genre,
        url);
  }

  @Override public void createBookCopy(int materialID)
  {
    client.createBookCopy(materialID);
  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      int playDuration, int placeID, String genre, String url)
  {
    client.registerDVD(title, publisher, releaseDate, description, tags,
        targetAudience, language, subtitlesLanguage, playDuration, placeID,
        genre, url);
  }

  @Override public void createDVDCopy(int materialID)
  {
    client.createDVDCopy(materialID);
  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, int placeID,
      String genre, String url)
  {
    client.registerCD(title, publisher, releaseDate, description, tags,
        targetAudience, language, playDuration, placeID, genre, url);
  }

  @Override public void createCDCopy(int materialID)
  {
    client.createCDCopy(materialID);
  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      String licenseNr, int authorId, String genre, String url)
  {
    client.registerEBook(title, publisher, releaseDate, description, tags,
        targetAudience, language, isbn, pageCount, licenseNr, authorId, genre,
        url);
  }

  @Override public void createEBookCopy(int materialID)
  {
    client.createEBookCopy(materialID);
  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String genre,
      int authorId, String url)
  {
    client.registerAudioBook(title, publisher, releaseDate, description, tags,
        targetAudience, language, playDuration, genre, authorId, url);
  }

  @Override public void createAudioBookCopy(int materialID)
  {
    client.createAudioBookCopy(materialID);
  }

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience, String type)
  {
    searchStrategyManager.selectStrategy(type);
    return searchStrategyManager
        .findMaterial(title, language, keywords, genre, targetAudience);
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
