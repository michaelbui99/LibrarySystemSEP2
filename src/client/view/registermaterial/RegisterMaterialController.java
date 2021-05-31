package client.view.registermaterial;

import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.*;
import javafx.scene.paint.Paint;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;

public class RegisterMaterialController
{
  @FXML private ComboBox<String> materialTypeCompo;
  @FXML private ComboBox<String> audience;
  @FXML private ComboBox<String> language;

  @FXML private TextField title;
  @FXML private TextField publisher;
  @FXML private TextArea description;
  @FXML private TextArea keywords;
  @FXML private TextField isbn;
  @FXML private DatePicker releaseDate;
  @FXML private TextField numberOfPages;
  @FXML private TextField licenseNumber;
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField country;
  @FXML private DatePicker dateOfBirth;
  @FXML private TextField hallNumber;
  @FXML private TextField genre;
  @FXML private TextField creatorLastName;
  @FXML private TextField department;
  @FXML private TextField subtitleLanguage;
  @FXML private TextField length;
  @FXML private TextField url;

  @FXML private Label error;
  @FXML private Label hallNoWarning;
  @FXML private Label departmentWarning;
  @FXML private Label creatorLNameWarning;
  @FXML private Label genreWarning;
  @FXML private Label firstNameWarning;
  @FXML private Label lastNameWarning;
  @FXML private Label dobWarning;
  @FXML private Label countryWarning;
  @FXML private Label titleWarning;
  @FXML private Label publisherWarning;
  @FXML private Label releaseDateWarning;
  @FXML private Label descriptionWarning;
  @FXML private Label keywordsWarning;
  @FXML private Label pageNoWarning;
  @FXML private Label isbnWarning;
  @FXML private Label licenseNoWarning;
  @FXML private Label subtitleWarning;
  @FXML private Label lengthWarning;

  private ObservableList<TextField> listT = FXCollections.observableArrayList();

  private RegisterMaterialVM registerMaterialVM;

  public void init(RegisterMaterialVM registerMaterialVM)
  {
    this.registerMaterialVM = registerMaterialVM;

    materialTypeCompo.setItems(registerMaterialVM.getMaterialType());
    audience.setItems(registerMaterialVM.getTargetAudience());
    language.setItems(registerMaterialVM.getLang());

    listT.addAll(title, publisher, isbn, firstName, lastName, country,
        hallNumber, creatorLastName, department, subtitleLanguage, length,
        numberOfPages, genre, url);

    title.textProperty().bindBidirectional(registerMaterialVM.titleProperty());
    publisher.textProperty()
        .bindBidirectional(registerMaterialVM.publisherProperty());
    language.valueProperty()
        .bindBidirectional(registerMaterialVM.languageProperty());
    isbn.textProperty().bindBidirectional(registerMaterialVM.isbnProperty());
    firstName.textProperty()
        .bindBidirectional(registerMaterialVM.firstNameProperty());
    lastName.textProperty()
        .bindBidirectional(registerMaterialVM.lastNameProperty());
    country.textProperty()
        .bindBidirectional(registerMaterialVM.countryProperty());
    hallNumber.textProperty()
        .bindBidirectional(registerMaterialVM.hallNumberProperty());
    creatorLastName.textProperty()
        .bindBidirectional(registerMaterialVM.creatorLastNameProperty());
    department.textProperty()
        .bindBidirectional(registerMaterialVM.departmentProperty());
    subtitleLanguage.textProperty()
        .bindBidirectional(registerMaterialVM.subtitleLanguageProperty());
    length.textProperty()
        .bindBidirectional(registerMaterialVM.lengthProperty());
    numberOfPages.textProperty()
        .bindBidirectional(registerMaterialVM.numberOfPagesProperty());
    genre.textProperty().bindBidirectional(registerMaterialVM.genreProperty());
    dateOfBirth.valueProperty()
        .bindBidirectional(registerMaterialVM.dateOfBirthPropertyProperty());
    releaseDate.valueProperty()
        .bindBidirectional(registerMaterialVM.releaseDateProperty());
    description.textProperty()
        .bindBidirectional(registerMaterialVM.descriptionProperty());
    keywords.textProperty()
        .bindBidirectional(registerMaterialVM.keywordsProperty());
    audience.valueProperty()
        .bindBidirectional(registerMaterialVM.audiencePropertyProperty());
    materialTypeCompo.valueProperty()
        .bindBidirectional(registerMaterialVM.typePropertyProperty());
    url.textProperty().bindBidirectional(registerMaterialVM.urlProperty());
    licenseNumber.textProperty()
        .bindBidirectional(registerMaterialVM.licenseNumberProperty());
  }

  private boolean containsOnlyLitters(String str)
  {
    for (int i = 0; i < str.length(); i++)
    {
      if (Character.isDigit(str.charAt(i)))
      {
        return false;
      }
    }
    return true;
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  @FXML public void onButtonBack(ActionEvent actionEvent) throws IOException
  {
    clearFields();
    ViewHandler.getInstance().openView("Administration");
  }

  @FXML public void onButtonConfirm(ActionEvent actionEvent) throws IOException
  {
    String type = materialTypeCompo.getValue();

    switch (type)
    {
      case "Vælg en materiale":
        error.setVisible(true);
        error.setTextFill(Paint.valueOf("red"));
        error.setText("Vælg en type");
        break;
      case "Bog":
        if (registerMaterialVM.bookAlreadyExists())
        {
          error.setText("Bogen findes allerede i systemet");
          error.setTextFill(Paint.valueOf("red"));
          error.setVisible(true);
        }
        else if (!registerMaterialVM.bookFieldsAreEmpty())
        {
          registerMaterialVM.addBook();
          error.setVisible(true);
          error.setTextFill(Paint.valueOf("green"));
          error.setText("Bog er tilføjet");
          clearFields();
        }
        else if (registerMaterialVM.bookFieldsAreEmpty())
        {
          error.setVisible(true);
          error.setText("Nødvendige bog felter er tomme !!");
        }
        break;
      case "Ebog":
        if (registerMaterialVM.eBookAlreadyExists())
        {
          error.setText("EBog findes allerede i systemet");
          error.setTextFill(Paint.valueOf("red"));
          error.setVisible(true);
        }
        else if (!registerMaterialVM.eBookFieldsAreEmpty())
        {
          registerMaterialVM.addEBook();
          error.setVisible(true);
          error.setTextFill(Paint.valueOf("green"));
          error.setText("EBog er tilføjet");
          clearFields();
        }
        else if (registerMaterialVM.eBookFieldsAreEmpty())
        {
          error.setVisible(true);
          error.setText("Nødvendige e-bog felter er tomme !!");
        }
        break;
      case "Lydbog":
        if (registerMaterialVM.audioBookAlreadyExists())
        {
          error.setText("Lydbog findes allerede i systemet");
          error.setTextFill(Paint.valueOf("red"));
          error.setVisible(true);
        }
        else if (!registerMaterialVM.audioBookFieldsAreEmpty())
        {
          registerMaterialVM.addAudioBook();
          error.setVisible(true);
          error.setTextFill(Paint.valueOf("green"));
          error.setText("Lydbog er tilføjet");
          clearFields();
        }
        else if (registerMaterialVM.audioBookFieldsAreEmpty())
        {
          error.setVisible(true);
          error.setText("Nødvendige lydbog felter er tomme !!");
        }
        break;
      case "CD":
        if (registerMaterialVM.cdAlreadyExists())
        {
          error.setText("CD findes allerede i systemet");
          error.setTextFill(Paint.valueOf("red"));
          error.setVisible(true);
        }
        else if (!registerMaterialVM.cdFieldsAreEmpty())
        {
          cdSelectedFields();
          registerMaterialVM.addCD();
          error.setVisible(true);
          error.setTextFill(Paint.valueOf("green"));
          error.setText("CD er tilføjet");
          clearFields();
        }
        else if (registerMaterialVM.cdFieldsAreEmpty())
        {
          error.setVisible(true);
          error.setText("Nødvendige CD felter er tomme !!");
        }
        break;
      case "DVD":
        if (registerMaterialVM.dvdAlreadyExists())
        {
          error.setText("DVD findes allerede i systemet");
          error.setTextFill(Paint.valueOf("red"));
          error.setVisible(true);
        }
        else if (!registerMaterialVM.dvdFieldsAreEmpty())
        {
          registerMaterialVM.addDVD();
          error.setVisible(true);
          error.setTextFill(Paint.valueOf("green"));
          error.setText("DVD er tilføjet");
          clearFields();
        }
        else if (registerMaterialVM.dvdFieldsAreEmpty())
        {
          error.setVisible(true);
          error.setText("Nødvendige DVD felter er tomme !!");
        }
        break;
    }
  }

  private void clearFields()
  {
    for (TextField textField : listT)
    {
      textField.clear();
    }
    dateOfBirth.getEditor().clear();
    releaseDate.getEditor().clear();
    description.clear();
    keywords.clear();
  }

  private void bookSelectedFields()
  {
    numberOfPages.setDisable(false);
    isbn.setDisable(false);
    licenseNumber.setDisable(true);
    firstName.setDisable(false);
    lastName.setDisable(false);
    dateOfBirth.setDisable(false);
    country.setDisable(false);
    hallNumber.setDisable(false);
    department.setDisable(false);
    creatorLastName.setDisable(false);
    subtitleLanguage.setDisable(true);
    length.setDisable(true);
  }

  private void cdSelectedFields()
  {
    numberOfPages.setDisable(true);
    isbn.setDisable(true);
    licenseNumber.setDisable(true);
    firstName.setDisable(true);
    lastName.setDisable(true);
    dateOfBirth.setDisable(true);
    country.setDisable(true);
    hallNumber.setDisable(false);
    department.setDisable(false);
    creatorLastName.setDisable(false);
    subtitleLanguage.setDisable(true);
  }

  private void eBookSelectedFields()
  {
    numberOfPages.setDisable(false);
    isbn.setDisable(true);
    licenseNumber.setDisable(false);
    firstName.setDisable(false);
    lastName.setDisable(false);
    dateOfBirth.setDisable(false);
    country.setDisable(false);
    hallNumber.setDisable(true);
    department.setDisable(true);
    creatorLastName.setDisable(true);
    subtitleLanguage.setDisable(true);
    length.setDisable(true);
  }

  private void audioBookSelectedFields()
  {
    numberOfPages.setDisable(true);
    isbn.setDisable(true);
    licenseNumber.setDisable(true);
    firstName.setDisable(false);
    lastName.setDisable(false);
    dateOfBirth.setDisable(false);
    country.setDisable(false);
    hallNumber.setDisable(true);
    department.setDisable(true);
    creatorLastName.setDisable(true);
    subtitleLanguage.setDisable(true);
    length.setDisable(false);
  }

  public void dvdSelectedFields()
  {
    numberOfPages.setDisable(true);
    isbn.setDisable(true);
    licenseNumber.setDisable(true);
    firstName.setDisable(true);
    lastName.setDisable(true);
    dateOfBirth.setDisable(true);
    country.setDisable(true);
    hallNumber.setDisable(false);
    department.setDisable(false);
    creatorLastName.setDisable(false);
    subtitleLanguage.setDisable(false);
    length.setDisable(false);
  }

  public void disableAll()
  {
    for (TextField textField : listT)
    {
      textField.setDisable(true);
    }
    dateOfBirth.getEditor().setDisable(true);
    releaseDate.getEditor().setDisable(true);
    description.setDisable(true);
    keywords.setDisable(true);
  }

  private void relevantFields()
  {
    String type = materialTypeCompo.getValue();
    switch (type)
    {
      case "Bog":
        bookSelectedFields();
        break;
      case "Ebog":
        eBookSelectedFields();
        break;
      case "Lydbog":
        audioBookSelectedFields();
        break;
      case "CD":
        cdSelectedFields();
        break;
      case "DVD":
        dvdSelectedFields();
        break;
      default:
        disableAll();
        break;
    }
  }

  @FXML public void onTypedTitle(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.titleProperty().get();
    titleWarning.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedPublisher(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.publisherProperty().get();
    publisherWarning.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedDescription(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.descriptionProperty().get();
    descriptionWarning.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedKeywords(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.keywordsProperty().get();
    keywordsWarning.setVisible(arg.isEmpty() || arg.matches("[0-9]"));
  }

  @FXML public void onTypedIsbn(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.isbnProperty().get();
    isbnWarning.setVisible(arg.isEmpty() || !arg.matches("[0-9]+"));
  }

  @FXML public void onTypedPageNumber(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.numberOfPagesProperty().get();
    pageNoWarning.setVisible(arg.isEmpty() || !arg.matches("[0-9]+"));
  }

  @FXML public void onTypeLicensNumber(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.licenseNumberProperty().get();
    licenseNoWarning.setVisible(arg.isEmpty() || !arg.matches("[0-9]+"));
  }

  @FXML public void onTypedHallNumber(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.hallNumberProperty().get();
    hallNoWarning.setVisible(arg.isEmpty() || !arg.matches("[0-9]+"));
  }

  @FXML public void onTypeGenre(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.genreProperty().get();
    genreWarning.setVisible(arg.isEmpty() && !containsOnlyLitters(arg));
  }

  @FXML public void onTypeCreatorLastName(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.creatorLastNameProperty().get();
    creatorLNameWarning.setVisible(arg.isEmpty() || !containsOnlyLitters(arg));
  }

  @FXML public void onTypeDepartment(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.departmentProperty().get();
    departmentWarning.setVisible(
        arg.isEmpty() || !containsOnlyLitters(arg) || arg.length() != 1);
  }

  @FXML public void onTypeSubtitleLanguage(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.subtitleLanguageProperty().get();
    subtitleWarning.setVisible(arg.isEmpty() || !containsOnlyLitters(arg));
  }

  @FXML public void onTypedLength(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.lengthProperty().get();
    lengthWarning.setVisible(arg.isEmpty() || !arg.matches("[0-9]+"));
  }

  @FXML public void onTypedLastName(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.lastNameProperty().get();
    lastNameWarning.setVisible(arg.isEmpty() || !containsOnlyLitters(arg));
  }

  @FXML public void onTypeCountry(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.countryProperty().get();
    countryWarning.setVisible(arg.isEmpty() || !containsOnlyLitters(arg));
  }

  @FXML public void onTypeFirstName(KeyEvent keyEvent)
  {
    String arg = registerMaterialVM.firstNameProperty().get();
    firstNameWarning.setVisible(arg.isEmpty() || !containsOnlyLitters(arg));
  }

  public void onChooseTypeCheck(ActionEvent actionEvent)
  {
    relevantFields();
  }

  @FXML public void omMouseExitedReleaseDateCheck(MouseEvent mouseEvent)
  {
    LocalDate arg = registerMaterialVM.releaseDateProperty().get();
    try
    {
      releaseDateWarning.setVisible(arg == null);
    }
    catch (NullPointerException e)
    {
      System.out.println("");
    }
  }

  @FXML public void onMouseExitedDateOfBirthCheck(MouseEvent mouseEvent)
  {
    LocalDate arg = registerMaterialVM.dateOfBirthPropertyProperty().get();
    try
    {
      dobWarning.setVisible(arg == null);
    }
    catch (NullPointerException e)
    {
      System.out.println("");
    }
  }
}
