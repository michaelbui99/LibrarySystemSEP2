package client.view.registermaterial;

import client.core.ModelFactoryClient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import shared.materials.Place;
import shared.person.MaterialCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class RegisterMaterialVM
{
  private ObservableList<String> materialType;
  private ObservableList<String> targetAudiance;

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
  private StringProperty audiance;

  public RegisterMaterialVM()
  {
    materialType = FXCollections.observableArrayList();
    materialType.addAll("Book", "EBook", "AudioBook", "CD", "DVD");

    targetAudiance = FXCollections.observableArrayList();
    targetAudiance
        .addAll("Voksen", "Barn", "Teenager", "Familie", "Ældre", "Studerende");

    releaseDateProperty = new SimpleObjectProperty<>();
    dateOfBirthProperty = new SimpleObjectProperty<>();
    titleProperty = new SimpleStringProperty();
    descriptionProperty = new SimpleStringProperty();
    publisherProperty = new SimpleStringProperty();
    keywordsProperty = new SimpleStringProperty();
    languageProperty = new SimpleStringProperty();
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
    audiance = new SimpleStringProperty();
  }

  public StringProperty titlePropertyProperty()
  {
    return titleProperty;
  }

  public StringProperty publisherPropertyProperty()
  {
    return publisherProperty;
  }

  public StringProperty descriptionPropertyProperty()
  {
    return descriptionProperty;
  }

  public StringProperty keywordsPropertyProperty()
  {
    return keywordsProperty;
  }

  public StringProperty languagePropertyProperty()
  {
    return languageProperty;
  }

  public StringProperty isbnPropertyProperty()
  {
    return isbnProperty;
  }

  public ObjectProperty<LocalDate> releaseDatePropertyProperty()
  {
    return releaseDateProperty;
  }

  public StringProperty numberOfPagesPropertyProperty()
  {
    return numberOfPagesProperty;
  }

  public StringProperty licensNumberPropertyProperty()
  {
    return licensNumberProperty;
  }

  public StringProperty firstNamePropertyProperty()
  {
    return firstNameProperty;
  }

  public StringProperty lastNamePropertyProperty()
  {
    return lastNameProperty;
  }

  public StringProperty countryPropertyProperty()
  {
    return countryProperty;
  }

  public ObjectProperty<LocalDate> dateOfBirthPropertyProperty()
  {
    return dateOfBirthProperty;
  }

  public StringProperty hallNumberPropertyProperty()
  {
    return hallNumberProperty;
  }

  public StringProperty genrePropertyProperty()
  {
    return genreProperty;
  }

  public StringProperty creatorLastNamePropertyProperty()
  {
    return creatorLastNameProperty;
  }

  public StringProperty departmentPropertyProperty()
  {
    return departmentProperty;
  }

  public StringProperty subtitleLanguagePropertyProperty()
  {
    return subtitleLanguageProperty;
  }

  public StringProperty lengthPropertyProperty()
  {
    return lengthProperty;
  }

  public StringProperty audianceProperty()
  {
    return audiance;
  }

  public ObservableList<String> getMaterialType()
  {
    return materialType;
  }

  public ObservableList<String> getTargetAudiance()
  {
    return targetAudiance;
  }

  public void addBook()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerBook(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audiance.get(), languageProperty.get(),
            isbnProperty.get(), Integer.parseInt(numberOfPagesProperty.get()),
            new Place(Integer.parseInt(hallNumberProperty.get()),
                departmentProperty.get(), creatorLastNameProperty.getName(),
                genreProperty.get()),
            new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
                dateOfBirthProperty.get().toString(), countryProperty.get()),
            genreProperty.get(), null);
  }

  public void addEBook()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerEBook(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audiance.get(), languageProperty.get(),
            Integer.parseInt(numberOfPagesProperty.get()),
            Integer.parseInt(licensNumberProperty.get()),
            new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
                dateOfBirthProperty.get().toString(), countryProperty.get()),
            genreProperty.get(), null);
  }

  public void addAudioBook()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerAudioBook(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audiance.get(), languageProperty.get(),
            Integer.parseInt(lengthProperty.get()), genreProperty.get(),
            new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
                dateOfBirthProperty.get().toString(), countryProperty.get()),
            null);
  }

  public void addDVD()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerDVD(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audiance.get(), languageProperty.get(),
            subtitleLanguageProperty.get(),
            Integer.parseInt(lengthProperty.get()),
            new Place(Integer.parseInt(hallNumberProperty.get()),
                departmentProperty.get(), creatorLastNameProperty.getName(),
                genreProperty.get()), genreProperty.get(), null);
  }

  public void addCD()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .registerCD(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audiance.get(), languageProperty.get(),
            Integer.parseInt(lengthProperty.get()),
            new Place(Integer.parseInt(hallNumberProperty.get()),
                departmentProperty.get(), creatorLastNameProperty.getName(),
                countryProperty.get()), genreProperty.get(), null);
  }
}
