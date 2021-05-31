package client.model.material;

import shared.materials.strategy.SearchStrategy;
import shared.materials.Material;
import shared.person.MaterialCreator;
import client.network.Client;
import shared.materials.Place;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class MaterialModelManagerClient implements MaterialModelClient
{

  private Client client;
  private PropertyChangeSupport support;
  private Material selectMaterial;

  public MaterialModelManagerClient(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);

  }

  public Material getSelectMaterial()
  {
    return client.getSelectMaterial();
  }

  public void setSelectMaterial(Material selectMaterial)
  {
      client.setSelectMaterial(selectMaterial);
      support.firePropertyChange("materialSelected", null ,selectMaterial);
  }

  @Override public void deleteMaterial(int materialID)
  {
    client.deleteMaterial(materialID);
  }

  @Override public int numberOfAvailableCopies()
  {
    return client.numberOfAvailableCopies();
  }

  @Override public int totalNumberOfCopies(int materialID)
  {
    return client.totalNumberOfCopies(materialID);
  }

  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place place, MaterialCreator author, String genre, String url,
      String keywords)
  {
    client.registerBook(title, publisher, releaseDate, description, tags,
        targetAudience, language, isbn, pageCount, place, author, genre, url, keywords);
  }

  @Override public void createBookCopy(int materialID)
  {
    client.createBookCopy(materialID);
  }

  @Override public void deleteBookCopy(int materialID, int copyNo)
  {
    client.deleteBookCopy(materialID, copyNo);
  }

  @Override public boolean bookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String isbn, int pageCount, MaterialCreator author,
      String genre)
  {
    return client.bookAlreadyExists(title, publisher, releaseDate, description,
        targetAudience, language, isbn, pageCount, author, genre);
  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      int playDuration, Place place, String genre, String url)
  {
    client.registerDVD(title, publisher, releaseDate, description, tags,
        targetAudience, language, subtitlesLanguage, playDuration, place, genre,
        url);
  }

  @Override public void createDVDCopy(int materialID)
  {
    client.createDVDCopy(materialID);
  }

  @Override public void deleteDVDCopy(int materialID, int copyNo)
  {
    client.deleteDVDCopy(materialID, copyNo);
  }

  @Override public boolean dvdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String playDuration, String genre)
  {
    return client.dvdAlreadyExists(title, publisher, releaseDate, description,
        targetAudience, language, playDuration, genre);
  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, Place place,
      String genre, String url)
  {
    client.registerCD(title, publisher, releaseDate, description, tags,
        targetAudience, language, playDuration, place, genre, url);
  }

  @Override public void createCDCopy(int materialID)
  {
    client.createCDCopy(materialID);
  }

  @Override public void deleteCDCopy(int materialID, int copyNo)
  {
    client.deleteCDCopy(materialID, copyNo);
  }

  @Override public boolean cdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, String genre)
  {
    return client.cdAlreadyExists(title, publisher, releaseDate, description,
        targetAudience, language, playDuration, genre);
  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount, int licenseNr,
      MaterialCreator author, String genre, String url)
  {
    client.registerEBook(title, publisher, releaseDate, description, tags,
        targetAudience, language, pageCount, licenseNr, author, genre, url);
  }

  @Override public void createEBookCopy(int materialID)
  {
    client.createEBookCopy(materialID);
  }

  @Override public void deleteEBookCopy(int materialID, int copyNo)
  {
    client.deleteEBookCopy(materialID, copyNo);
  }

  @Override public boolean eBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int pageCount, int licenseNr, String genre,
      MaterialCreator author)
  {
    return client.eBookAlreadyExists(title, publisher, releaseDate, description,
        targetAudience, language, pageCount, licenseNr, genre, author);
  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String genre,
      MaterialCreator author, String url)
  {
    client.registerAudioBook(title, publisher, releaseDate, description, tags,
        targetAudience, language, playDuration, genre, author, url);
  }

  @Override public void createAudioBookCopy(int materialID)
  {
    client.createAudioBookCopy(materialID);
  }

  @Override public void deleteAudioBookCopy(int materialID, int copyNo)
  {
    client.deleteAudioBookCopy(materialID, copyNo);
  }

  @Override public boolean audioBookAlreadyExists(String title,
      String publisher, String releaseDate, String description,
      String targetAudience, String language, int playDuration,
      MaterialCreator author, String genre)
  {
    return client
        .audioBookAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, playDuration, author, genre);
  }

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience,
      SearchStrategy searchStrategy)
  {
    return client.findMaterial(title, language, keywords, genre, targetAudience,
        searchStrategy);

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
