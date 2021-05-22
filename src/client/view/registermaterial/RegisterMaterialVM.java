package client.view.registermaterial;

import client.core.ModelFactoryClient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.materials.Place;
import shared.person.MaterialCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class RegisterMaterialVM
{
  private ObservableList<String> materialType;
  private ObservableList<String> targetAudiance;
  private ObservableList<String> lang;

  private StringProperty titleProperty;
  private StringProperty publisherProperty;
  private StringProperty descriptionProperty;
  private StringProperty keywordsProperty;
  private StringProperty languageProperty;
  private StringProperty isbnProperty;
  private ObjectProperty<LocalDate> releaseDateProperty;
  private StringProperty numberOfPagesProperty;
  private StringProperty licensNumberProperty;
  private StringProperty firstNameProperty;
  private StringProperty lastNameProperty;
  private StringProperty countryProperty;
  private ObjectProperty<LocalDate> dateOfBirthProperty;
  private StringProperty hallNumberProperty;
  private StringProperty genreProperty;
  private StringProperty creatorLastNameProperty;
  private StringProperty departmentProperty;
  private StringProperty subtitleLanguageProperty;
  private StringProperty lengthProperty;
  private StringProperty audianceProperty;
  private StringProperty typeProperty;
  private StringProperty urlProperty;

  public RegisterMaterialVM()
  {
    materialType = FXCollections.observableArrayList();
    materialType.addAll("Book", "EBook", "AudioBook", "CD", "DVD");

    targetAudiance = FXCollections.observableArrayList();
    targetAudiance
        .addAll("Voksen", "Barn", "Teenager", "Familie", "Ældre", "Studerende");

    lang = FXCollections.observableArrayList();
    lang.addAll("Dansk", "Engelsk", "Arabisk");

    releaseDateProperty = new SimpleObjectProperty<>();
    dateOfBirthProperty = new SimpleObjectProperty<>();
    titleProperty = new SimpleStringProperty();
    descriptionProperty = new SimpleStringProperty();
    publisherProperty = new SimpleStringProperty();
    keywordsProperty = new SimpleStringProperty();
    languageProperty = new SimpleStringProperty("Dansk");
    isbnProperty = new SimpleStringProperty();
    numberOfPagesProperty = new SimpleStringProperty();
    titleProperty = new SimpleStringProperty();
    licensNumberProperty = new SimpleStringProperty();
    firstNameProperty = new SimpleStringProperty();
    lastNameProperty = new SimpleStringProperty();
    countryProperty = new SimpleStringProperty();
    hallNumberProperty = new SimpleStringProperty();
    genreProperty = new SimpleStringProperty();
    creatorLastNameProperty = new SimpleStringProperty();
    departmentProperty = new SimpleStringProperty();
    subtitleLanguageProperty = new SimpleStringProperty();
    lengthProperty = new SimpleStringProperty();
    audianceProperty = new SimpleStringProperty("Voksen");
    typeProperty = new SimpleStringProperty("Book");
    urlProperty = new SimpleStringProperty();
  }

  public ObjectProperty<LocalDate> releaseDateProperty()
  {
    return releaseDateProperty;
  }

  public ObservableList<String> getMaterialType()
  {
    return materialType;
  }

  public ObservableList<String> getTargetAudiance()
  {
    return targetAudiance;
  }

  public ObservableList<String> getLang()
  {
    return lang;
  }

  public StringProperty titleProperty()
  {
    return titleProperty;
  }

  public StringProperty publisherProperty()
  {
    return publisherProperty;
  }

  public StringProperty descriptionProperty()
  {
    return descriptionProperty;
  }

  public StringProperty keywordsProperty()
  {
    return keywordsProperty;
  }

  public StringProperty languageProperty()
  {
    return languageProperty;
  }

  public StringProperty isbnProperty()
  {
    return isbnProperty;
  }

  public StringProperty numberOfPagesProperty()
  {
    return numberOfPagesProperty;
  }

  public StringProperty licensNumberProperty()
  {
    return licensNumberProperty;
  }

  public StringProperty firstNameProperty()
  {
    return firstNameProperty;
  }

  public StringProperty lastNameProperty()
  {
    return lastNameProperty;
  }

  public StringProperty countryProperty()
  {
    return countryProperty;
  }

  public ObjectProperty<LocalDate> dateOfBirthPropertyProperty()
  {
    return dateOfBirthProperty;
  }

  public StringProperty hallNumberProperty()
  {
    return hallNumberProperty;
  }

  public StringProperty genreProperty()
  {
    return genreProperty;
  }

  public StringProperty creatorLastNameProperty()
  {
    return creatorLastNameProperty;
  }

  public StringProperty departmentProperty()
  {
    return departmentProperty;
  }

  public StringProperty subtitleLanguageProperty()
  {
    return subtitleLanguageProperty;
  }

  public StringProperty lengthProperty()
  {
    return lengthProperty;
  }

  public StringProperty audiancePropertyProperty()
  {
    return audianceProperty;
  }

  public StringProperty typePropertyProperty()
  {
    return typeProperty;
  }

  public StringProperty urlProperty()
  {
    return urlProperty;
  }

  public void addBook()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerBook(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audianceProperty.get(),
            languageProperty.get(), isbnProperty.get(),
            Integer.parseInt(numberOfPagesProperty.get()),
            new Place(Integer.parseInt(hallNumberProperty.get()),
                departmentProperty.get(), creatorLastNameProperty.getName(),
                genreProperty.get()),
            new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
                dateOfBirthProperty.get().toString(), countryProperty.get()),
            genreProperty.get(), urlProperty.get(), keywordsProperty.get());
  }

  public boolean bookAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getMaterialModelClient()
        .bookAlreadyExists(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            audianceProperty.get(), languageProperty.get(), isbnProperty.get(),
            Integer.parseInt(numberOfPagesProperty.get()),
            new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
                dateOfBirthProperty.get().toString(), countryProperty.get()),
            genreProperty.get());
  }

  public void addEBook()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerEBook(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audianceProperty.get(),
            languageProperty.get(),
            Integer.parseInt(numberOfPagesProperty.get()),
            Integer.parseInt(licensNumberProperty.get()),
            new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
                dateOfBirthProperty.get().toString(), countryProperty.get()),
            genreProperty.get(), urlProperty.get());
  }

  public boolean eBookAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getMaterialModelClient()
        .eBookAlreadyExists(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            audianceProperty.get(), languageProperty.get(),
            Integer.parseInt(numberOfPagesProperty.get()),
            licensNumberProperty.get(), genreProperty.get(),
            new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
                dateOfBirthProperty.get().toString(), countryProperty.get()));
  }

  public void addAudioBook()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerAudioBook(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audianceProperty.get(),
            languageProperty.get(), Integer.parseInt(lengthProperty.get()),
            genreProperty.get(),
            new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
                dateOfBirthProperty.get().toString(), countryProperty.get()),
            urlProperty.get());
  }

  public boolean audioBookAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getMaterialModelClient()
        .audioBookAlreadyExists(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            audianceProperty.get(), languageProperty.get(),
            Integer.parseInt(lengthProperty.get()),
            new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
                dateOfBirthProperty.get().toString(), countryProperty.get()),
            genreProperty.get());
  }

  public void addDVD()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerDVD(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audianceProperty.get(),
            languageProperty.get(), subtitleLanguageProperty.get(),
            Integer.parseInt(lengthProperty.get()),
            new Place(Integer.parseInt(hallNumberProperty.get()),
                departmentProperty.get(), creatorLastNameProperty.getName(),
                genreProperty.get()), genreProperty.get(), urlProperty.get());
  }

  public boolean dvdAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getMaterialModelClient()
        .dvdAlreadyExists(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            audianceProperty.get(), languageProperty.get(),
            lengthProperty.get(), genreProperty.get());
  }

  public void addCD()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerCD(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audianceProperty.get(),
            languageProperty.get(), Integer.parseInt(lengthProperty.get()),
            new Place(Integer.parseInt(hallNumberProperty.get()),
                departmentProperty.get(), creatorLastNameProperty.getName(),
                countryProperty.get()), genreProperty.get(), urlProperty.get());
  }

  public boolean cdAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getMaterialModelClient()
        .cdAlreadyExists(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            audianceProperty.get(), languageProperty.get(),
            Integer.parseInt(lengthProperty.get()), genreProperty.get());
  }
}
