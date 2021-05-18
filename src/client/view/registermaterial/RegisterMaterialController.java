package client.view.registermaterial;

import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class RegisterMaterialController
{
  @FXML private ComboBox<String> materialTypeCompo;
  @FXML private ComboBox<String> audiance;
  @FXML private ComboBox<String> language;
  @FXML private Label error;

  @FXML private TextField title;
  @FXML private TextField publisher;
  @FXML private TextArea description;
  @FXML private TextArea keywords;
  //@FXML private TextField language;
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

  @FXML private Label titWar;

  private ObservableList<TextField> listT = FXCollections.observableArrayList();

  public void init()
  {
    materialTypeCompo.setItems(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .getMaterialType());
    audiance.setItems(ViewModelFactory.getInstance().getRegisterMaterialVM()
        .getTargetAudiance());
    language.setItems(
        ViewModelFactory.getInstance().getRegisterMaterialVM().getLang());

    listT.addAll(title, publisher, isbn, firstName, lastName, country,
        hallNumber, creatorLastName, department, subtitleLanguage, length,
        numberOfPages, genre);

    title.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .titleProperty());
    publisher.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .publisherProperty());
    language.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .languageProperty());
    isbn.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .isbnProperty());
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
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .genreProperty());
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
    ViewHandler.getInstance().openView("Administration");
  }

  @FXML public void onButtonConfirmWindow(ActionEvent actionEvent)
      throws IOException
  {
    String type = materialTypeCompo.getValue();

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

  private void setErrorOnEmptyFields()
  {
      error.setText("Title is Empty");
      error.setTextFill(Paint.valueOf("red"));
  }

  private boolean titleIsEmp()
  {
    return ViewModelFactory.getInstance().getRegisterMaterialVM().titleProperty().get().isEmpty();
  }

  @FXML public void onButtonConfirm(ActionEvent actionEvent)
  {

  }

  public void onError(MouseEvent mouseEvent)
  {
    if (titleIsEmp())
    {
      titWar.setVisible(true);
    }
    else
    {
      titWar.setVisible(false);
    }
  }
}
