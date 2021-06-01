package client.view.registermaterial;

import client.model.material.MaterialModelClient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.materials.Place;
import shared.person.MaterialCreator;

import java.time.LocalDate;

/**
 * View model for registering materials
 *
 * @author Kutaiba
 * @version 1.0
 */
public class RegisterMaterialVM
{
  private ObservableList<String> materialType;
  private ObservableList<String> targetAudience;
  private ObservableList<String> lang;

  private StringProperty titleProperty;
  private StringProperty publisherProperty;
  private StringProperty descriptionProperty;
  private StringProperty keywordsProperty;
  private StringProperty languageProperty;
  private StringProperty isbnProperty;
  private ObjectProperty<LocalDate> releaseDateProperty;
  private StringProperty numberOfPagesProperty;
  private StringProperty licenseNumberProperty;
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
  private StringProperty audienceProperty;
  private StringProperty typeProperty;
  private StringProperty urlProperty;
  private MaterialModelClient materialModel;

  public RegisterMaterialVM(MaterialModelClient materialModel)
  {
    this.materialModel = materialModel;
    materialType = FXCollections.observableArrayList();
    materialType.addAll("Bog", "Ebog", "Lydbog", "CD", "DVD");

    targetAudience = FXCollections.observableArrayList();
    targetAudience
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
    licenseNumberProperty = new SimpleStringProperty();
    firstNameProperty = new SimpleStringProperty();
    lastNameProperty = new SimpleStringProperty();
    countryProperty = new SimpleStringProperty();
    hallNumberProperty = new SimpleStringProperty();
    genreProperty = new SimpleStringProperty();
    creatorLastNameProperty = new SimpleStringProperty();
    departmentProperty = new SimpleStringProperty();
    subtitleLanguageProperty = new SimpleStringProperty();
    lengthProperty = new SimpleStringProperty();
    audienceProperty = new SimpleStringProperty("Voksen");
    typeProperty = new SimpleStringProperty("Vælg en materiale");
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

  public ObservableList<String> getTargetAudience()
  {
    return targetAudience;
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

  public StringProperty licenseNumberProperty()
  {
    return licenseNumberProperty;
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

  public StringProperty audiencePropertyProperty()
  {
    return audienceProperty;
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
    materialModel.registerBook(titleProperty.get(), publisherProperty.get(),
        releaseDateProperty.get().toString(), descriptionProperty.get(),
        keywordsProperty.get(), audienceProperty.get(), languageProperty.get(),
        isbnProperty.get(), Integer.parseInt(numberOfPagesProperty.get()),
        new Place(Integer.parseInt(hallNumberProperty.get()),
            departmentProperty.get(), creatorLastNameProperty.get(),
            genreProperty.get()),
        new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
            dateOfBirthProperty.get().toString(), countryProperty.get()),
        genreProperty.get(), urlProperty.get(), keywordsProperty.get());
  }

  public boolean bookAlreadyExists()
  {
    if (titleProperty.get() == null || publisherProperty.get() == null
        || releaseDateProperty.get() == null
        || descriptionProperty.get() == null || audienceProperty.get() == null
        || languageProperty.get() == null || isbnProperty.get() == null
        || numberOfPagesProperty.get() == null
        || firstNameProperty.get() == null || lastNameProperty.get() == null
        || dateOfBirthProperty.get() == null || countryProperty.get() == null
        || genreProperty.get() == null)
    {
      return false;
    }
    else
    {
      return materialModel
          .bookAlreadyExists(titleProperty.get(), publisherProperty.get(),
              releaseDateProperty.get().toString(), descriptionProperty.get(),
              audienceProperty.get(), languageProperty.get(),
              isbnProperty.get(), Integer.parseInt(numberOfPagesProperty.get()),
              new MaterialCreator(firstNameProperty.get(),
                  lastNameProperty.get(), dateOfBirthProperty.get().toString(),
                  countryProperty.get()), genreProperty.get());
    }
  }

  public void addEBook()
  {
    materialModel.registerEBook(titleProperty.get(), publisherProperty.get(),
        releaseDateProperty.get().toString(), descriptionProperty.get(),
        keywordsProperty.get(), audienceProperty.get(), languageProperty.get(),
        Integer.parseInt(numberOfPagesProperty.get()),
        Integer.parseInt(licenseNumberProperty.get()),
        new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
            dateOfBirthProperty.get().toString(), countryProperty.get()),
        genreProperty.get(), urlProperty.get());
  }

  public boolean eBookAlreadyExists()
  {
    if (titleProperty.get() == null || publisherProperty.get() == null
        || releaseDateProperty.get() == null
        || descriptionProperty.get() == null || audienceProperty.get() == null
        || languageProperty.get() == null || numberOfPagesProperty.get() == null
        || licenseNumberProperty.get() == null || genreProperty.get() == null
        || firstNameProperty.get() == null || lastNameProperty.get() == null
        || dateOfBirthProperty.get() == null || countryProperty.get() == null)
    {
      return false;
    }
    else
    {
      return materialModel
          .eBookAlreadyExists(titleProperty.get(), publisherProperty.get(),
              releaseDateProperty.get().toString(), descriptionProperty.get(),
              audienceProperty.get(), languageProperty.get(),
              Integer.parseInt(numberOfPagesProperty.get()),
              Integer.parseInt(licenseNumberProperty.get()),
              genreProperty.get(), new MaterialCreator(firstNameProperty.get(),
                  lastNameProperty.get(), dateOfBirthProperty.get().toString(),
                  countryProperty.get()));
    }
  }

  public void addAudioBook()
  {
    materialModel
        .registerAudioBook(titleProperty.get(), publisherProperty.get(),
            releaseDateProperty.get().toString(), descriptionProperty.get(),
            keywordsProperty.get(), audienceProperty.get(),
            languageProperty.get(), Integer.parseInt(lengthProperty.get()),
            genreProperty.get(),
            new MaterialCreator(firstNameProperty.get(), lastNameProperty.get(),
                dateOfBirthProperty.get().toString(), countryProperty.get()),
            urlProperty.get());
  }

  public boolean audioBookAlreadyExists()
  {
    if (titleProperty.get() == null || publisherProperty.get() == null
        || releaseDateProperty.get() == null
        || descriptionProperty.get() == null || audienceProperty.get() == null
        || languageProperty.get() == null || lengthProperty.get() == null
        || firstNameProperty.get() == null || lastNameProperty.get() == null
        || dateOfBirthProperty.get() == null || countryProperty.get() == null
        || genreProperty.get() == null)
    {
      return false;
    }
    else
    {
      return materialModel
          .audioBookAlreadyExists(titleProperty.get(), publisherProperty.get(),
              releaseDateProperty.get().toString(), descriptionProperty.get(),
              audienceProperty.get(), languageProperty.get(),
              Integer.parseInt(lengthProperty.get()),
              new MaterialCreator(firstNameProperty.get(),
                  lastNameProperty.get(), dateOfBirthProperty.get().toString(),
                  countryProperty.get()), genreProperty.get());
    }
  }

  public void addDVD()
  {
    materialModel.registerDVD(titleProperty.get(), publisherProperty.get(),
        releaseDateProperty.get().toString(), descriptionProperty.get(),
        keywordsProperty.get(), audienceProperty.get(), languageProperty.get(),
        subtitleLanguageProperty.get(), Integer.parseInt(lengthProperty.get()),
        new Place(Integer.parseInt(hallNumberProperty.get()),
            departmentProperty.get(), creatorLastNameProperty.get(),
            genreProperty.get()), genreProperty.get(), urlProperty.get());
  }

  public boolean dvdAlreadyExists()
  {
    if (titleProperty.get() == null || publisherProperty.get() == null
        || releaseDateProperty.get() == null
        || descriptionProperty.get() == null || audienceProperty.get() == null
        || languageProperty.get() == null || lengthProperty.get() == null
        || subtitleLanguageProperty.get() == null
        || genreProperty.get() == null)
    {
      return false;
    }
    else
    {
      return materialModel
          .dvdAlreadyExists(titleProperty.get(), publisherProperty.get(),
              releaseDateProperty.get().toString(), descriptionProperty.get(),
              audienceProperty.get(), languageProperty.get(),
              lengthProperty.get(), genreProperty.get());
    }
  }

  public void addCD()
  {
    materialModel.registerCD(titleProperty.get(), publisherProperty.get(),
        releaseDateProperty.get().toString(), descriptionProperty.get(),
        keywordsProperty.get(), audienceProperty.get(), languageProperty.get(),
        Integer.parseInt(lengthProperty.get()),
        new Place(Integer.parseInt(hallNumberProperty.get()),
            departmentProperty.get(), creatorLastNameProperty.get(),
            countryProperty.get()), genreProperty.get(), urlProperty.get());
  }

  public boolean cdAlreadyExists()
  {
    if (titleProperty.get() == null || publisherProperty.get() == null
        || releaseDateProperty.get() == null
        || descriptionProperty.get() == null || audienceProperty.get() == null
        || languageProperty.get() == null || lengthProperty.get() == null
        || genreProperty.get() == null)
    {
      return false;
    }
    else
    {
      return materialModel
          .cdAlreadyExists(titleProperty.get(), publisherProperty.get(),
              releaseDateProperty.get().toString(), descriptionProperty.get(),
              audienceProperty.get(), languageProperty.get(),
              Integer.parseInt(lengthProperty.get()), genreProperty.get());
    }
  }

  public boolean bookFieldsAreEmpty()
  {
    return titleProperty.get() == null || publisherProperty.get() == null
        || releaseDateProperty.get() == null
        || descriptionProperty.get() == null || keywordsProperty.get() == null
        || publisherProperty.get() == null || isbnProperty.get() == null
        || firstNameProperty.get() == null || lastNameProperty.get() == null
        || dateOfBirthProperty.get() == null || countryProperty == null
        || hallNumberProperty == null || departmentProperty == null
        || creatorLastNameProperty.get() == null || genreProperty == null;
  }

  public boolean eBookFieldsAreEmpty()
  {
    return titleProperty.get() == null || publisherProperty.get() == null
        || releaseDateProperty.get() == null || descriptionProperty == null
        || keywordsProperty == null || publisherProperty.get() == null
        || licenseNumberProperty.get() == null
        || firstNameProperty.get() == null || lastNameProperty.get() == null
        || dateOfBirthProperty.get() == null || countryProperty.get() == null
        || genreProperty.get() == null;
  }

  public boolean audioBookFieldsAreEmpty()
  {
    return titleProperty.get() == null || publisherProperty.get() == null
        || releaseDateProperty.get() == null
        || descriptionProperty.get() == null || keywordsProperty.get() == null
        || publisherProperty.get() == null || lengthProperty.get() == null
        || firstNameProperty.get() == null || lastNameProperty.get() == null
        || dateOfBirthProperty.get() == null || countryProperty.get() == null
        || genreProperty == null;
  }

  public boolean cdFieldsAreEmpty()
  {
    return titleProperty.get() == null || publisherProperty.get() == null
        || releaseDateProperty.get() == null
        || descriptionProperty().get() == null || keywordsProperty == null
        || publisherProperty.get() == null || lengthProperty.get() == null
        || genreProperty == null;
  }

  public boolean dvdFieldsAreEmpty()
  {
    return titleProperty.get() == null || publisherProperty.get() == null
        || releaseDateProperty.get() == null
        || descriptionProperty.get() == null || keywordsProperty.get() == null
        || publisherProperty.get() == null || lengthProperty.get() == null
        || genreProperty.get() == null
        || subtitleLanguageProperty.get() == null;
  }
}
