package client.view.addlibrarian;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class AddLibrarianController
{
  @FXML private TextField lastName;
  @FXML private TextField firstName;
  @FXML private TextField cprNumber;
  @FXML private TextField streetName;
  @FXML private TextField city;
  @FXML private TextField zipCode;
  @FXML private TextField streetNumber;
  @FXML private TextField phoneNumber;
  @FXML private TextField employeeNo;
  @FXML private PasswordField password;
  @FXML private TextField email;

  @FXML private Label employeeNoError;
  @FXML private Label cprError;
  @FXML private Label fNameError;
  @FXML private Label lNameError;
  @FXML private Label streetNameError;
  @FXML private Label streetNoError;
  @FXML private Label zipCodeError;
  @FXML private Label cityError;
  @FXML private Label phoneError;
  @FXML private Label emailError;
  @FXML private Label passwordError;
  @FXML private Label errorLable;

  private ObservableList<TextField> fields = FXCollections
      .observableArrayList();

  public void init()
  {
    errorLable.setVisible(false);
    errorLable.setTextFill(Paint.valueOf("red"));

    fields.addAll(lastName, firstName, cprNumber, streetName, city, zipCode,
        streetNumber, phoneNumber, employeeNo, password, email);

    lastName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().lastNameProperty());
    firstName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().firstNameProperty());
    cprNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().cprProperty());
    streetName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM()
            .streetNameProperty());
    city.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().cityProperty());
    zipCode.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().zipCodeProperty());
    streetNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().streetNoProperty());
    phoneNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().phoneProperty());
    email.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().emailProperty());
    password.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM().passwordProperty());
    employeeNo.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddLibrarianVM()
            .employeeNoProperty());
    errorLable.textProperty().bind(
        ViewModelFactory.getInstance().getAddLibrarianVM()
            .errorLabelProperty());
  }

  private boolean containsOnlyDigits(String str)
  {
    for (int i = 0; i < str.length(); i++)
    {
      try
      {
        Integer.parseInt(str);
        return true;
      }
      catch (NumberFormatException e)
      {
        return false;
      }
    }
    return false;
  }

  @FXML public void onButtonSignUp(ActionEvent actionEvent) throws IOException
  {
    errorLable.setVisible(true);
    ViewModelFactory.getInstance().getAddLibrarianVM().addLibrarian();
    clearFields();
  }

  @FXML public void onButtonBack(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Main");
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  @FXML public void onTypedEmailCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .emailProperty().get();
    emailError.setVisible(arg.isEmpty() || !arg.contains("@"));
  }

  @FXML public void onTypedPasswordCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .passwordProperty().get();
    passwordError.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedEmployeeNOCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .employeeNoProperty().get();
    employeeNoError.setVisible(arg.isEmpty() || !arg.matches("\\d+"));
  }

  @FXML public void onTypedPhoneNo(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .phoneProperty().get();
    //"^(\+\d{10}( )?)$" to check if the streng contains "+" and an 11 digit  number
    phoneError.setVisible(
        arg.isEmpty() || !arg.matches("^(\\+\\d{10}( )?)$") || !arg
            .contains("+45"));
  }

  @FXML public void onTypeStreetNoCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .streetNoProperty().get();
    streetNoError.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedZipCode(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .zipCodeProperty().get();
    zipCodeError
        .setVisible(arg.isEmpty() || !arg.matches("\\d+") || arg.length() != 4);
  }

  @FXML public void onTypedCity(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .cityProperty().get();
    cityError.setVisible(arg.isEmpty() || !arg.matches("[a-zA-Z]+"));
  }

  @FXML public void onTypeStreetNameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .streetNameProperty().get();
    streetNameError.setVisible(arg.isEmpty() || !arg.matches("[a-zA-Z]+"));
  }

  @FXML public void onTypedCprCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .cprProperty().get();
    String[] arr = arg.split("-");
    cprError.setVisible(arg.length() != 11 || !(containsOnlyDigits(arr[0])
        && containsOnlyDigits(arr[1])) || !arg.contains("-"));
  }

  @FXML public void onTypedFirstNameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .firstNameProperty().get();
    fNameError.setVisible(arg.isEmpty() || !arg.matches("[a-zA-Z]+"));
  }

  @FXML public void onTypedLastNameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddLibrarianVM()
        .lastNameProperty().get();
    lNameError.setVisible(arg.isEmpty() || !arg.matches("[a-zA-Z]+"));
  }

  private void clearFields()
  {
    for (TextField field : fields)
    {
      field.clear();
    }
  }
}
