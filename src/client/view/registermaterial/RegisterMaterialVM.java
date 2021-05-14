package client.view.registermaterial;

import client.core.ModelFactoryClient;
import client.core.ViewModelFactory;
import client.model.material.MaterialModelClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class RegisterMaterialVM
{
  private ObservableList<String> materialType;
  public RegisterMaterialVM()
  {
    materialType = FXCollections.observableArrayList();
    materialType.add("Book");
    materialType.add("EBook");
    materialType.add("AudioBook");
    materialType.add("CD");
    materialType.add("DVD");
  }

  public ObservableList<String> getMaterialType()
  {
    return materialType;
  }

  public void addBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, int placeID, int authorId, String genre,
      String url)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, isbn, pageCount, placeID, authorId, genre,
            url);
  }

  public void addEBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, String licenseNr, int authorId, String genre,
      String url)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerEBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, isbn, pageCount, licenseNr, authorId,
            genre, url);
  }

  public void addAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, String genre, int authorId, String url)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerAudioBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, playDuration, genre, authorId, url);
  }

  public void addDVD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String subtitlesLanguage, int playDuration, int placeID, String genre,
      String url)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerDVD(title, publisher, releaseDate, description, tags,
            targetAudience, language, subtitlesLanguage, playDuration, placeID,
            genre, url);
  }

  public void addCD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, int placeID, String genre, String url)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerCD(title, publisher, releaseDate, description, tags,
            targetAudience, language, playDuration, placeID, genre, url);
  }
}
