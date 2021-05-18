package client.view.registermaterial;

import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import shared.materials.Place;
import shared.person.MaterialCreator;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegisterMaterialController
{
  @FXML private ComboBox<String> materialTypeCompo;
  @FXML private ComboBox<String> audiance;
  @FXML private Label error;

  @FXML private TextField title;
  @FXML private TextField publisher;
  @FXML private TextArea description;
  @FXML private TextArea keywords;
  @FXML private TextField language;
  @FXML private TextField isbn;
  @FXML private DatePicker releaseDate;
  @FXML private TextField numberOfPages;
  @FXML private TextField licensNumber;
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

  @FXML private Label hallNoWarning;
  @FXML private Label departmentWaning;
  @FXML private Label creatorLNameWarning;
  @FXML private Label genreWarning;
  @FXML private Label firstNameWarning;
  @FXML private Label lastNameWarning;
  @FXML private Label dobWarning;
  @FXML private Label countryWarning;
  @FXML private Label typeWarning;
  @FXML private Label titleWarning;
  @FXML private Label publisherWarning;
  @FXML private Label releseDateWarning;
  @FXML private Label descriptionWarning;
  @FXML private Label keywordsWarning;
  @FXML private Label audianceWarning;
  @FXML private Label pageNoWarning;
  @FXML private Label languageWarning;
  @FXML private Label isbnWarning;
  @FXML private Label licensNoWarning;
  @FXML private Label subtitileWarning;
  @FXML private Label lengthWarning;

  private ObservableList<TextField> listT = FXCollections.observableArrayList();

  public void init()
  {
    materialTypeCompo.setItems(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .getMaterialType());
    audiance.setItems(ViewModelFactory.getInstance().getRegisterMaterialVM()
        .getTargetAudiance());
    listT.addAll(title, publisher, language, isbn, firstName, lastName, country,
        hallNumber, creatorLastName, department, subtitleLanguage, length,
        numberOfPages, genre);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    //convert String to LocalDate
    LocalDate localDate;

    title.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .titlePropertyProperty());
    publisher.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .publisherPropertyProperty());
    language.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .languagePropertyProperty());
    isbn.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .isbnPropertyProperty());
    firstName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .firstNamePropertyProperty());
    lastName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .lastNamePropertyProperty());
    country.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .countryPropertyProperty());
    hallNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .hallNumberPropertyProperty());
    creatorLastName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .creatorLastNamePropertyProperty());
    department.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .departmentPropertyProperty());
    subtitleLanguage.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .subtitleLanguagePropertyProperty());
    length.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .lengthPropertyProperty());
    numberOfPages.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .numberOfPagesPropertyProperty());
    genre.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .genrePropertyProperty());
    dateOfBirth.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .dateOfBirthPropertyProperty());
    releaseDate.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .releaseDatePropertyProperty());
    description.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .descriptionPropertyProperty());
    keywords.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .keywordsPropertyProperty());
    audiance.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .audianceProperty());
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  @FXML public void onButtonBack(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Administation");
  }

  @FXML public void onButtonConfirmWindow(ActionEvent actionEvent)
      throws IOException
  {
    String rd = String.valueOf(releaseDate.getValue());
    String dob = String.valueOf(dateOfBirth.getValue());

    String type = materialTypeCompo.getValue();
    String aud = audiance.getValue().toString();

    /*if (onMouseExitHallCheck() && onMouseExitDepartmentCheck()
        && onMouseExitCreatorCheck() && onMouseExitGenreCheck()
        && onMouseExitFNameCheck() && onMouseExitLNameCheck()
        && onMouseExitDobCheck() && onMouseExitCountryCheck()
        && onMouseExitTypeCheck() && onMouseExitTitleCheck()
        && onMouseExitPublisherCheck() && onMouseExitReleseDateCheck()
        && onMouseExitDescriptionCheck() && onMouseExitKeywordCheck()
        && onMouseExitAudianceCheck() && onMouseExitPageNoCheck()
        && onMouseExitLanguageCheck() && onMouseExitIsbnCheck()
        && onMouseExitLicensNoCheck() && onMouseExitSubtitleCheck()
        && onMouseExitLengthCheck())*/

      if (type.equals("Book"))
      {
        ViewModelFactory.getInstance().getRegisterMaterialVM().addBook();
        setTestForLabelGreen("Bogen");
      }
      else if (type.equals("EBook"))
      {
        ViewModelFactory.getInstance().getRegisterMaterialVM().addEBook();
        setTestForLabelGreen("E-bogen");
      }
      else if (type.equals("AudioBook"))
      {
        ViewModelFactory.getInstance().getRegisterMaterialVM().addAudioBook();
        setTestForLabelGreen("Lydbogen");
      }
      else if (type.equals("CD"))
      {
        ViewModelFactory.getInstance().getRegisterMaterialVM().addCD();
        setTestForLabelGreen("CD'en");
      }
      else if (type.equals("DVD"))
      {
        ViewModelFactory.getInstance().getRegisterMaterialVM().addDVD();
        setTestForLabelGreen("DVD'en");
      }
      else if (type.equals(null))
      {
        error.setVisible(true);
      }
  }

  private void clearFields()
  {
    for (int i = 0; i < listT.size(); i++)
    {
      listT.get(i).clear();
    }
    dateOfBirth.getEditor().clear();
    releaseDate.getEditor().clear();
    description.clear();
    keywords.clear();
  }

  private void setTestForLabelGreen(String type)
  {
    error.setText(type + " er tilføjet");
    error.setTextFill(Paint.valueOf("green"));
    clearFields();
  }

  private void setErrorMaterialInSystem()
  {
    error.setText("Materialet findes allerede i systemet!!!");
    error.setTextFill(Paint.valueOf("red"));
  }

  private void setErrorOnEmptyType()
  {
    error.setText("Vælg materialetype");
    error.setTextFill(Paint.valueOf("red"));
  }

  @FXML public void onButtonConfirm(ActionEvent actionEvent)
  {

  }

  @FXML public boolean onMouseExitHallCheck()
  {
    if (!hallNumber.getText().matches(".*\\d.*") || hallNumber.getText()
        .isEmpty())
    {
      hallNoWarning.setVisible(true);
      return true;
    }
    else
    {
      hallNoWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitDepartmentCheck()
  {
    if (department.getText().matches(".*\\d.*") || department.getText()
        .isEmpty())
    {
      departmentWaning.setVisible(true);
      return true;
    }
    else
    {
      departmentWaning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitCreatorCheck()
  {
    if (creatorLastName.getText().isEmpty() || creatorLastName.getText()
        .matches(".*\\d.*"))
    {
      creatorLNameWarning.setVisible(true);
      return true;
    }
    else
    {
      creatorLNameWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitGenreCheck()
  {
    if (genre.getText().isEmpty() || genre.getText().matches(".*\\d.*"))
    {
      genreWarning.setVisible(true);
      return true;
    }
    genreWarning.setVisible(false);
    return false;
  }

  @FXML public boolean onMouseExitFNameCheck()
  {
    if (firstName.getText().isEmpty() || firstName.getText().matches(".*\\d.*"))
    {
      firstNameWarning.setVisible(true);
      return true;
    }
    else
    {
      firstNameWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitLNameCheck()
  {
    if (lastName.getText().isEmpty() || lastName.getText().matches(".*\\d.*"))
    {
      lastNameWarning.setVisible(true);
      return true;
    }
    else
    {
      return false;
    }
  }

  @FXML public boolean onMouseExitDobCheck()
  {
    if (dateOfBirth.getValue().equals(null))
    {
      dobWarning.setVisible(true);
      return true;
    }
    else
    {
      dobWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitCountryCheck()
  {
    if (country.getText().isEmpty() || country.getText().matches(".*\\d.*"))
    {
      countryWarning.setVisible(true);
      return true;
    }
    else
    {
      countryWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitTypeCheck()
  {
    if (materialTypeCompo.getValue().isEmpty())
    {
      setErrorOnEmptyType();
      typeWarning.setVisible(true);
      return true;
    }
    else
    {
      error.setVisible(false);
      typeWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitTitleCheck()
  {
    if (title.getText().isEmpty())
    {
      titleWarning.setVisible(true);
      return true;
    }
    else
    {
      return false;
    }
  }

  @FXML public boolean onMouseExitPublisherCheck()
  {
    if (publisher.getText().isEmpty())
    {
      publisherWarning.setVisible(true);
      return true;
    }
    else
    {
      publisherWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitReleseDateCheck()
  {
    if (releaseDate.getValue().equals(null))
    {
      releseDateWarning.setVisible(true);
      return true;
    }
    else
    {
      releseDateWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitDescriptionCheck()
  {
    if (description.getText().isEmpty())
    {
      descriptionWarning.setVisible(true);
      return true;
    }
    else
    {
      descriptionWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitKeywordCheck()
  {
    if (keywords.getText().isEmpty() || keywords.getText().matches(".*\\d.*"))
    {
      keywordsWarning.setVisible(true);
      return true;
    }
    else
    {
      keywordsWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitAudianceCheck()
  {
    if (audiance.getValue().isEmpty())
    {
      audianceWarning.setVisible(true);
      return true;
    }
    else
    {
      audianceWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitPageNoCheck()
  {
    if (numberOfPages.getText().isEmpty() || !numberOfPages.getText()
        .matches(".*\\d.*"))
    {
      pageNoWarning.setVisible(true);
      return true;
    }
    else
    {
      pageNoWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitLanguageCheck()
  {
    if (language.getText().isEmpty() || !(
        language.getText().equalsIgnoreCase("Dansk") || language.getText()
            .equalsIgnoreCase("Engelsk") || language.getText()
            .equalsIgnoreCase("Arabisk")) || language.getText()
        .matches(".*\\d.*"))
    {
      languageWarning.setVisible(true);
      return true;
    }
    else
    {
      languageWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitIsbnCheck()
  {
    if (isbn.getText().isEmpty() || !isbn.getText().matches(".*\\d.*"))
    {
      isbnWarning.setVisible(true);
      return true;
    }
    else
    {
      isbnWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitLicensNoCheck()
  {
    if (licensNumber.getText().isEmpty() || licensNumber.getText()
        .matches(".*\\d.*"))
    {
      licensNoWarning.setVisible(true);
      return true;
    }
    else
    {
      licensNoWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitSubtitleCheck()
  {
    if (subtitleLanguage.getText().isEmpty() || subtitleLanguage.getText()
        .matches(".*\\d.*"))
    {
      subtitileWarning.setVisible(true);
      return true;
    }
    else
    {
      subtitileWarning.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitLengthCheck()
  {
    if (length.getText().isEmpty() || !length.getText().matches(".*\\d.*"))
    {
      lengthWarning.setVisible(true);
      return true;
    }
    else
    {
      lengthWarning.setVisible(false);
      return false;
    }
  }
}
