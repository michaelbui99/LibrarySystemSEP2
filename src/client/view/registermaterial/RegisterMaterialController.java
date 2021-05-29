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

  public void init()
  {
    materialTypeCompo.setItems(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .getMaterialType());
    audience.setItems(ViewModelFactory.getInstance().getRegisterMaterialVM()
        .getTargetAudience());
    language.setItems(
        ViewModelFactory.getInstance().getRegisterMaterialVM().getLang());

    listT.addAll(title, publisher, isbn, firstName, lastName, country,
        hallNumber, creatorLastName, department, subtitleLanguage, length,
        numberOfPages, genre, url);

    title.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM().titleProperty());
    publisher.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .publisherProperty());
    language.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .languageProperty());
    isbn.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM().isbnProperty());
    firstName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .firstNameProperty());
    lastName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .lastNameProperty());
    country.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .countryProperty());
    hallNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .hallNumberProperty());
    creatorLastName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .creatorLastNameProperty());
    department.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .departmentProperty());
    subtitleLanguage.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .subtitleLanguageProperty());
    length.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .lengthProperty());
    numberOfPages.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .numberOfPagesProperty());
    genre.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM().genreProperty());
    dateOfBirth.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .dateOfBirthPropertyProperty());
    releaseDate.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .releaseDateProperty());
    description.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .descriptionProperty());
    keywords.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .keywordsProperty());
    audience.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .audiencePropertyProperty());
    materialTypeCompo.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .typePropertyProperty());
    url.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM().urlProperty());
    licenseNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .licenseNumberProperty());
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
        if (ViewModelFactory.getInstance().getRegisterMaterialVM()
            .bookAlreadyExists())
        {
          error.setText("Bogen findes allerede i systemet");
          error.setTextFill(Paint.valueOf("red"));
          error.setVisible(true);
        }
        else if (!ViewModelFactory.getInstance().getRegisterMaterialVM()
            .bookFieldsAreEmpty())
        {
          ViewModelFactory.getInstance().getRegisterMaterialVM().addBook();
          error.setVisible(true);
          error.setTextFill(Paint.valueOf("green"));
          error.setText("Bog er tilføjet");
          clearFields();
        }
        else if (ViewModelFactory.getInstance().getRegisterMaterialVM()
            .bookFieldsAreEmpty())
        {
          error.setVisible(true);
          error.setText("Nødvendige bog felter er tomme !!");
        }
        break;
      case "Ebog":
        if (ViewModelFactory.getInstance().getRegisterMaterialVM()
            .eBookAlreadyExists())
        {
          error.setText("EBog findes allerede i systemet");
          error.setTextFill(Paint.valueOf("red"));
          error.setVisible(true);
        }
        else if (!ViewModelFactory.getInstance().getRegisterMaterialVM()
            .eBookFieldsAreEmpty())
        {
          ViewModelFactory.getInstance().getRegisterMaterialVM().addEBook();
          error.setVisible(true);
          error.setTextFill(Paint.valueOf("green"));
          error.setText("EBog er tilføjet");
          clearFields();
        }
        else if (ViewModelFactory.getInstance().getRegisterMaterialVM()
            .eBookFieldsAreEmpty())
        {
          error.setVisible(true);
          error.setText("Nødvendige e-bog felter er tomme !!");
        }
        break;
      case "Lydbog":
        if (ViewModelFactory.getInstance().getRegisterMaterialVM()
            .audioBookAlreadyExists())
        {
          error.setText("Lydbog findes allerede i systemet");
          error.setTextFill(Paint.valueOf("red"));
          error.setVisible(true);
        }
        else if (!ViewModelFactory.getInstance().getRegisterMaterialVM()
            .audioBookFieldsAreEmpty())
        {
          ViewModelFactory.getInstance().getRegisterMaterialVM().addAudioBook();
          error.setVisible(true);
          error.setTextFill(Paint.valueOf("green"));
          error.setText("Lydbog er tilføjet");
          clearFields();
        }
        else if (ViewModelFactory.getInstance().getRegisterMaterialVM()
            .audioBookFieldsAreEmpty())
        {
          error.setVisible(true);
          error.setText("Nødvendige lydbog felter er tomme !!");
        }
        break;
      case "CD":
        if (ViewModelFactory.getInstance().getRegisterMaterialVM()
            .cdAlreadyExists())
        {
          error.setText("CD findes allerede i systemet");
          error.setTextFill(Paint.valueOf("red"));
          error.setVisible(true);
        }
        else if (!ViewModelFactory.getInstance().getRegisterMaterialVM()
            .cdFieldsAreEmpty())
        {
          cdSelectedFields();
          ViewModelFactory.getInstance().getRegisterMaterialVM().addCD();
          error.setVisible(true);
          error.setTextFill(Paint.valueOf("green"));
          error.setText("CD er tilføjet");
          clearFields();
        }
        else if (ViewModelFactory.getInstance().getRegisterMaterialVM()
            .cdFieldsAreEmpty())
        {
          error.setVisible(true);
          error.setText("Nødvendige CD felter er tomme !!");
        }
        break;
      case "DVD":
        if (ViewModelFactory.getInstance().getRegisterMaterialVM()
            .dvdAlreadyExists())
        {
          error.setText("DVD findes allerede i systemet");
          error.setTextFill(Paint.valueOf("red"));
          error.setVisible(true);
        }
        else if (!ViewModelFactory.getInstance().getRegisterMaterialVM()
            .dvdFieldsAreEmpty())
        {
          ViewModelFactory.getInstance().getRegisterMaterialVM().addDVD();
          error.setVisible(true);
          error.setTextFill(Paint.valueOf("green"));
          error.setText("DVD er tilføjet");
          clearFields();
        }
        else if (ViewModelFactory.getInstance().getRegisterMaterialVM()
            .dvdFieldsAreEmpty())
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
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .titleProperty().get();
    titleWarning.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedPublisher(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .publisherProperty().get();
    publisherWarning.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedDescription(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .descriptionProperty().get();
    descriptionWarning.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedKeywords(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .keywordsProperty().get();
    keywordsWarning.setVisible(arg.isEmpty() || arg.matches("[0-9]"));
  }

  @FXML public void onTypedIsbn(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .isbnProperty().get();
    isbnWarning.setVisible(arg.isEmpty() || !arg.matches("[0-9]+"));
  }

  @FXML public void onTypedPageNumber(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .numberOfPagesProperty().get();
    pageNoWarning.setVisible(arg.isEmpty() || !arg.matches("[0-9]+"));
  }

  @FXML public void onTypeLicensNumber(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .licenseNumberProperty().get();
    licenseNoWarning.setVisible(arg.isEmpty() || !arg.matches("[0-9]+"));
  }

  @FXML public void onTypedHallNumber(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .hallNumberProperty().get();
    hallNoWarning.setVisible(arg.isEmpty() || !arg.matches("[0-9]+"));
  }

  @FXML public void onTypeGenre(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .genreProperty().get();
    genreWarning.setVisible(arg.isEmpty() && !containsOnlyLitters(arg));
  }

  @FXML public void onTypeCreatorLastName(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .creatorLastNameProperty().get();
    creatorLNameWarning.setVisible(
        arg.isEmpty() || !containsOnlyLitters(arg));
  }

  @FXML public void onTypeDepartment(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .departmentProperty().get();
    departmentWarning.setVisible(
        arg.isEmpty() || !containsOnlyLitters(arg) || arg.length() != 1);
  }

  @FXML public void onTypeSubtitleLanguage(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .subtitleLanguageProperty().get();
    subtitleWarning.setVisible(
        arg.isEmpty() || !containsOnlyLitters(arg));
  }

  @FXML public void onTypedLength(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .lengthProperty().get();
    lengthWarning.setVisible(arg.isEmpty() || !arg.matches("[0-9]+"));
  }

  @FXML public void onTypedLastName(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .lastNameProperty().get();
    lastNameWarning.setVisible(arg.isEmpty() || !containsOnlyLitters(arg));
  }

  @FXML public void onTypeCountry(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .countryProperty().get();
    countryWarning.setVisible(arg.isEmpty() || !containsOnlyLitters(arg));
  }

  @FXML public void onTypeFirstName(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .firstNameProperty().get();
    firstNameWarning.setVisible(arg.isEmpty() || !containsOnlyLitters(arg));
  }

  public void onChooseTypeCheck(ActionEvent actionEvent)
  {
    relevantFields();
  }

  @FXML public void omMouseExitedReleaseDateCheck(MouseEvent mouseEvent)
  {
    LocalDate arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .releaseDateProperty().get();
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
    LocalDate arg = ViewModelFactory.getInstance().getRegisterMaterialVM()
        .dateOfBirthPropertyProperty().get();
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
