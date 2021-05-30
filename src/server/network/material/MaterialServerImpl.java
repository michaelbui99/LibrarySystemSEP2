package server.network.material;

import server.model.material.MaterialModelServer;
import shared.materials.Material;
import shared.materials.Place;
import shared.person.MaterialCreator;
import client.model.material.strategy.SearchStrategy;
import shared.network.MaterialServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MaterialServerImpl implements MaterialServer
{
  private MaterialModelServer materialModel;
  public MaterialServerImpl(MaterialModelServer materialModel)
  {
    try
    {
      this.materialModel = materialModel;
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
  }

  @Override public int createMaterial(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String genre, String url, String keywords)
  {
    return materialModel
        .registerMaterial(title, publisher, releaseDate, description,
            targetAudience, language, genre, url, keywords);
  }

  @Override public void setSelectedMaterial(Material material)
  {
    materialModel
        .setSelectedMaterial(material);
  }

  @Override public Material getSelectedMaterial()
  {
    return materialModel
        .getSelectMaterial();
  }

  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place place, MaterialCreator author, String genre, String url,
      String keywards)
  {
    materialModel
        .registerBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, isbn, pageCount, place, author, genre,
            url);
  }

  @Override public void createBookCopy(int materialID)
  {
    materialModel
        .createBookCopy(materialID);
  }

  @Override public void deleteBookCopy(int materialID, int copyNo)
  {
    materialModel
        .deleteBookCopy(materialID, copyNo);
  }

  @Override public boolean bookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String isbn, int pageCount, MaterialCreator author,
      String genre)
  {
    return materialModel
        .bookAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, isbn, pageCount, author, genre);
  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      int playDuration, Place placeID, String genre, String url)
  {
    materialModel
        .registerDVD(title, publisher, releaseDate, description, tags,
            targetAudience, language, subtitlesLanguage, playDuration, placeID,
            genre, url);
  }

  @Override public void createDVDCopy(int materialID)
  {
    materialModel
        .createDVDCopy(materialID);
  }

  @Override public void deleteDVDCopy(int materialID, int copyNo)
  {
    materialModel
        .deleteDVDCopy(materialID, copyNo);
  }

  @Override public boolean dvdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String playDuration, String genre)
  {
    return materialModel
        .dvdAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, playDuration, genre);
  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, Place place,
      String genre, String url)
  {
    materialModel
        .registerCD(title, publisher, releaseDate, description, tags,
            targetAudience, language, playDuration, place, genre, url);
  }

  @Override public void createCDCopy(int materialID)
  {
    materialModel
        .createCDCopy(materialID);
  }

  @Override public void deleteCDCopy(int materialID, int copyNo)
  {
    materialModel
        .deleteCDCopy(materialID, copyNo);
  }

  @Override public boolean cdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, String genre)
  {
    return materialModel
        .cdAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, playDuration, genre);
  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount, int licenseNr,
      MaterialCreator author, String genre, String url)
  {
    materialModel
        .registerEBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, pageCount, licenseNr, author, genre, url);
  }

  @Override public void createEBookCopy(int materialID)
  {
    materialModel
        .createEBookCopy(materialID);
  }

  @Override public void deleteEBookCopy(int materialID, int copyNo)
  {
    materialModel
        .deleteEBookCopy(materialID, copyNo);
  }

  @Override public boolean eBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int pageCount, int licenseNr, String genre,
      MaterialCreator author)
  {
    return materialModel
        .eBookAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, pageCount, licenseNr, genre, author);
  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String genre,
      MaterialCreator author, String url)
  {
    materialModel
        .registerAudioBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, playDuration, genre, author, url);
  }

  @Override public void createAudioBookCopy(int materialID)
  {
    materialModel
        .createAudioBookCopy(materialID);
  }

  @Override public void deleteAudioBookCopy(int materialID, int copyNo)
  {
    materialModel
        .deleteAudiotBookCopy(materialID, copyNo);
  }

  @Override public boolean audioBookAlreadyExists(String title,
      String publisher, String releaseDate, String description,
      String targetAudience, String language, int playDuration,
      MaterialCreator author, String genre)
  {
    return materialModel
        .audioBookAlreadyExists(title, publisher, releaseDate, description,
            targetAudience, language, playDuration, author, genre);
  }

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience,
      SearchStrategy searchStrategy)
  {
    return materialModel
        .findMaterial(title, language, keywords, genre, targetAudience,
            searchStrategy);
  }

  @Override public int numberOfAvailableCopies()
  {
    return materialModel
        .numberOfAvailableCopies();
  }

  @Override public int totalNumberOfCopies(int materialID)
  {
    return materialModel
        .totalNumberOfCopies(materialID);
  }

  @Override public void deleteMaterial(int materialID)
  {
    materialModel
        .deleteMaterial(materialID);
  }

}
