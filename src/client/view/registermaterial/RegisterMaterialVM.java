package client.view.registermaterial;

import client.core.ModelFactoryClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.materials.Place;
import shared.person.MaterialCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RegisterMaterialVM
{
  private ObservableList<String> materialType;
  private ObservableList<String> targetAudiance;

  private StringProperty title, publisher, description, keywords, language, isbn,
                         releaseDate, numberOfPages, licensNumber, firstName,lastName,
                         country, hallNumber, genre, creatorLastName, department,
                         subtitleLanguage, length,dateOfBirth, warning;

  public RegisterMaterialVM()
  {
    materialType = FXCollections.observableArrayList();
    materialType.addAll("Book", "EBook", "AudioBook", "CD", "DVD");

    targetAudiance = FXCollections.observableArrayList();
    targetAudiance.addAll("Voksen", "Barn", "Teenager", "Familie", "Ældre", "Studerende");

  title = new SimpleStringProperty();
  publisher = new SimpleStringProperty();
  description = new SimpleStringProperty();
  keywords = new SimpleStringProperty();
  language = new SimpleStringProperty();
  isbn = new SimpleStringProperty();
  releaseDate = new SimpleStringProperty();
  numberOfPages = new SimpleStringProperty();
  licensNumber = new SimpleStringProperty();
  firstName = new SimpleStringProperty();
  lastName = new SimpleStringProperty();
  country = new SimpleStringProperty();
  hallNumber = new SimpleStringProperty();
  genre = new SimpleStringProperty();
  creatorLastName = new SimpleStringProperty();
  department = new SimpleStringProperty();
  subtitleLanguage = new SimpleStringProperty();
  length = new SimpleStringProperty();
  dateOfBirth = new SimpleStringProperty();
  warning = new SimpleStringProperty("");
  }

  public ObservableList<String> getMaterialType()
  {
    return materialType;
  }
  public ObservableList<String> getTargetAudiance()
  {
    return targetAudiance;
  }

  public StringProperty titleProperty()
  {
    return title;
  }

  public StringProperty publisherProperty()
  {
    return publisher;
  }

  public StringProperty descriptionProperty()
  {
    return description;
  }

  public StringProperty keywordsProperty()
  {
    return keywords;
  }

  public StringProperty languageProperty()
  {
    return language;
  }

  public StringProperty isbnProperty()
  {
    return isbn;
  }

  public StringProperty releaseDateProperty()
  {
    return releaseDate;
  }

  public StringProperty numberOfPagesProperty()
  {
    return numberOfPages;
  }

  public StringProperty licensNumberProperty()
  {
    return licensNumber;
  }

  public StringProperty firstNameProperty()
  {
    return firstName;
  }

  public StringProperty lastNameProperty()
  {
    return lastName;
  }

  public StringProperty countryProperty()
  {
    return country;
  }

  public StringProperty hallNumberProperty()
  {
    return hallNumber;
  }

  public StringProperty genreProperty()
  {
    return genre;
  }

  public StringProperty creatorLastNameProperty()
  {
    return creatorLastName;
  }

  public StringProperty departmentProperty()
  {
    return department;
  }

  public StringProperty subtitleLanguageProperty()
  {
    return subtitleLanguage;
  }

  public StringProperty lengthProperty()
  {
    return length;
  }

  public StringProperty dateOfBirthProperty()
  {
    return dateOfBirth;
  }

  public StringProperty warningProperty()
  {
    return warning;
  }

  public void addBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, Place place, MaterialCreator author, String genre,
      String url)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerBook(String.valueOf(titleProperty()),
            String.valueOf(publisherProperty()),
            String.valueOf(releaseDateProperty()),
            String.valueOf(descriptionProperty()),
            String.valueOf(keywordsProperty()),
            targetAudience, String.valueOf(languageProperty()),
            String.valueOf(isbnProperty()),
            Integer.parseInt(String.valueOf(numberOfPagesProperty())), new Place(Integer.parseInt(String.valueOf(hallNumberProperty())), String.valueOf(departmentProperty()),
                String.valueOf(creatorLastNameProperty()), String.valueOf(genreProperty())), new MaterialCreator(String.valueOf(firstNameProperty()),
                String.valueOf(lastNameProperty()), String.valueOf(dateOfBirthProperty()), String.valueOf(countryProperty())),
            String.valueOf(genreProperty()),
            url);
  }

  public void addEBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String isbn, int pageCount, String licenseNr, MaterialCreator author, String genre,
      String url)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerEBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, isbn, pageCount, licenseNr, author,
            genre, url);
  }

  public void addAudioBook(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, String genre, MaterialCreator author, String url)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerAudioBook(title, publisher, releaseDate, description, tags,
            targetAudience, language, playDuration, genre, author, url);
  }

  public void addDVD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String subtitlesLanguage, int playDuration, Place placeID, String genre,
      String url)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerDVD(title, publisher, releaseDate, description, tags,
            targetAudience, language, subtitlesLanguage, playDuration, placeID,
            genre, url);
  }

  public void addCD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      int playDuration, Place place, String genre, String url)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerCD(title, publisher, releaseDate, description, tags,
            targetAudience, language, playDuration, place, genre, url);
  }
}
