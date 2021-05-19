package server.model.material;

import server.core.ModelFactoryServer;
import shared.materials.Material;
import shared.materials.MaterialList;
import shared.materials.Place;
import shared.person.MaterialCreator;
import client.model.material.strategy.SearchStrategy;
import database.material.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;

public class MaterialModelManagerServer implements MaterialModelServer
{
  private PropertyChangeSupport support;
  private MaterialList materialList;
  private SearchStrategy searchStrategy;
  private Material selectedMaterial;

  public MaterialModelManagerServer()
  {
    selectedMaterial = null;
    support = new PropertyChangeSupport(this);
    materialList = new MaterialList();
  }

  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place place, MaterialCreator author, String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url);
      BookDAOImpl.getInstance()
          .create(materialID, isbn, pageCount, author, place);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createBookCopy(int materialID)
  {

  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      int playDuration, Place place, String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url);
      DVDDAOImpl.getInstance()
          .create(materialID, subtitlesLanguage, playDuration, place);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createDVDCopy(int materialID)
  {

  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, Place place,
      String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url);
      CDDAOImpl.getInstance().create(materialID, playDuration, place);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createCDCopy(int materialID)
  {

  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount, int licenseNr,
      MaterialCreator author, String genre, String url)
  {
    int materialID = 0;
    try
    {
      materialID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, targetAudience,
              language, genre, url);
      EbookDAOImpl.getInstance().create(materialID, pageCount, author, licenseNr);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createEBookCopy(int materialID)
  {

  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String genre,
      MaterialCreator author, String url)
  {
    int materialID = 0;
    try
    {
      materialID = MaterialDAOImpl.getInstance().create(title, publisher, releaseDate, description,
          targetAudience, language, genre, url);
    AudioBookDAOImpl.getInstance().create(materialID, playDuration, author);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createAudioBookCopy(int materialID)
  {

  }

  @Override public List<Material> findMaterial(String title, String language, String keywords,
      String genre, String targetAudience, SearchStrategy searchStrategy)
  {
    this.searchStrategy = searchStrategy;
    return searchStrategy.findMaterial(title, language, keywords, genre, targetAudience);

  }

  @Override public int numberOfAvailableCopies()
  {
    return MaterialDAOImpl.getInstance().getNumberOfAvailableCopies(selectedMaterial.getMaterialID());
  }

  @Override public Material getSelectMaterial()
  {
    return selectedMaterial;
  }

  @Override public void setSelectedMaterial(Material material)
  {
    this.selectedMaterial = material;
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
