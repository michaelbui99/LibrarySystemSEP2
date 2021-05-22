package server.network.material;

import shared.materials.Material;
import shared.materials.Place;
import shared.person.MaterialCreator;
import client.model.material.strategy.SearchStrategy;
import server.core.ModelFactoryServer;
import shared.servers.MaterialServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MaterialServerImpl implements MaterialServer
{
  public MaterialServerImpl()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
  }

  @Override public void setSelectedMaterial(Material material)
  {
    ModelFactoryServer.getInstance().getMaterialModel()
        .setSelectedMaterial(material);
  }

  @Override public Material getSelectedMaterial()
  {
    return ModelFactoryServer.getInstance().getMaterialModel()
        .getSelectMaterial();
  }

  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place place, MaterialCreator author, String genre, String url,
      String keywards)
  {
    ModelFactoryServer.getInstance().getMaterialModel()
        .registerBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, isbn, pageCount, place, author, genre,
            url, keywards);
  }

  @Override public void createBookCopy(int materialID)
  {

  }

  @Override public boolean bookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String isbn, int pageCount, MaterialCreator author,
      String genre)
  {
    return ModelFactoryServer.getInstance().getMaterialModel()
        .bookAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, isbn, pageCount, author, genre);
  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      int playDuration, Place placeID, String genre, String url)
  {
    ModelFactoryServer.getInstance().getMaterialModel()
        .registerDVD(title, publisher, releaseDate, description, tags,
            targetAudience, language, subtitlesLanguage, playDuration, placeID,
            genre, url);
  }

  @Override public void createDVDCopy(int materialID)
  {

  }

  @Override public boolean dvdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String playDuration, String genre)
  {
    return ModelFactoryServer.getInstance().getMaterialModel()
        .dvdAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, playDuration, genre);
  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, Place place,
      String genre, String url)
  {
    ModelFactoryServer.getInstance().getMaterialModel()
        .registerCD(title, publisher, releaseDate, description, tags,
            targetAudience, language, playDuration, place, genre, url);
  }

  @Override public void createCDCopy(int materialID)
  {

  }

  @Override public boolean cdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, String genre)
  {
    return ModelFactoryServer.getInstance().getMaterialModel()
        .cdAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, playDuration, genre);
  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount, int licenseNr,
      MaterialCreator author, String genre, String url)
  {
    ModelFactoryServer.getInstance().getMaterialModel()
        .registerEBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, pageCount, licenseNr, author, genre, url);
  }

  @Override public void createEBookCopy(int materialID)
  {

  }

  @Override public boolean eBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int pageCount, String licenseNr, String genre,
      MaterialCreator author)
  {
    return ModelFactoryServer.getInstance().getMaterialModel()
        .eBookAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, pageCount, licenseNr, genre, author);
  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String genre,
      MaterialCreator author, String url)
  {
    ModelFactoryServer.getInstance().getMaterialModel()
        .registerAudioBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, playDuration, genre, author, url);
  }

  @Override public void createAudioBookCopy(int materialID)
  {

  }

  @Override public boolean audioBookAlreadyExists(String title,
      String publisher, String releaseDate, String description,
      String targetAudience, String language, int playDuration,
      MaterialCreator author, String genre)
  {
    return ModelFactoryServer.getInstance().getMaterialModel()
        .audioBookAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, playDuration, author, genre);
  }

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience,
      SearchStrategy searchStrategy)
  {
    return ModelFactoryServer.getInstance().getMaterialModel()
        .findMaterial(title, language, keywords, genre, targetAudience,
            searchStrategy);
  }

  @Override public int numberOfAvailableCopies()
  {
    return ModelFactoryServer.getInstance().getMaterialModel()
        .numberOfAvailableCopies();
  }

}
