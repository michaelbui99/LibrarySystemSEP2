package server.network.material;

import shared.materials.Place;
import shared.person.MaterialCreator;
import client.model.material.strategy.SearchStrategy;
import server.core.ModelFactoryServer;
import shared.servers.MaterialServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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

  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place place, MaterialCreator author, String genre, String url)
  {
    ModelFactoryServer.getInstance().getMaterialModel()
        .registerBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, isbn, pageCount, place, author, genre,
            url);
  }

  @Override public void createBookCopy(int materialID)
  {

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

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount, int licenseNr,
      MaterialCreator author, String genre, String url) throws RemoteException
  {
    ModelFactoryServer.getInstance().getMaterialModel()
        .registerEBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, pageCount, licenseNr, author, genre, url);
  }

  @Override public void createEBookCopy(int materialID)
  {

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

  @Override public void findMaterial(String arg, SearchStrategy searchStrategy)
  {

  }
}
