package server.network.material;

import client.model.material.Place;
import client.model.material.strategy.MaterialCreator;
import client.model.material.strategy.SearchStrategy;
import shared.MaterialServer;

import java.rmi.RemoteException;

public class MaterialServerImpl implements MaterialServer
{



  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place place, MaterialCreator author, String genre, String url)
      throws RemoteException
  {

  }

  @Override public void createBookCopy(int materialID) throws RemoteException
  {

  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      int playDuration, Place placeID, String genre, String url)
      throws RemoteException
  {

  }

  @Override public void createDVDCopy(int materialID) throws RemoteException
  {

  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, Place place,
      String genre, String url) throws RemoteException
  {

  }

  @Override public void createCDCopy(int materialID) throws RemoteException
  {

  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      String licenseNr, MaterialCreator author, String genre, String url)
      throws RemoteException
  {

  }

  @Override public void createEBookCopy(int materialID) throws RemoteException
  {

  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, String genre,
      MaterialCreator author, String url) throws RemoteException
  {

  }

  @Override public void createAudioBookCopy(int materialID)
      throws RemoteException
  {

  }

  @Override public void findMaterial(String arg, SearchStrategy searchStrategy)
      throws RemoteException
  {

  }
}
