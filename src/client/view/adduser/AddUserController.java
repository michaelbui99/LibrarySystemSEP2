package client.view.adduser;

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

public class AddUserController
{
  @FXML private Label firstNameError;
  @FXML private Label lastNameError;
  @FXML private Label streetNameError;
  @FXML private Label streetNoError;
  @FXML private Label cityError;
  @FXML private Label zipCodeError;
  @FXML private Label emailError;
  @FXML private Label passwordError;
  @FXML private Label cprError;
  @FXML private Label phoneError;
  @FXML private Label errorLabel;

  @FXML private TextField email;
  @FXML private PasswordField password;
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField cprNumber;
  @FXML private TextField streetName;
  @FXML private TextField streetNumber;
  @FXML private TextField zipCode;
  @FXML private TextField city;
  @FXML private TextField phoneNumber;

  private ObservableList<TextField> fields = FXCollections
      .observableArrayList();

  /**
   * This pattern return true if String contains any thing other than 0-9 digit,
   * which can be used to know if an String is number or not using regular expression.
   * email.getText().matches(".*\\d.*)"
   */
  public void init()
  {
    errorLabel.setVisible(false);
    errorLabel.setTextFill(Paint.valueOf("red"));

    fields
        .addAll(email, firstName, lastName, cprNumber, streetName, streetNumber,
            zipCode, city, phoneNumber, password);

    email.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().emailProperty());
    password.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().passwordProperty());
    firstName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().firstNameProperty());
    lastName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().lastNameProperty());
    cprNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().cprProperty());
    streetName.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().streetNameProperty());
    streetNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().streetNoProperty());
    zipCode.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().zipCodeProperty());
    city.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().cityProperty());
    phoneNumber.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().phoneNoProperty());
    errorLabel.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getAddUserVM().errorProperty());
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

  @FXML public void onButtonSignup(ActionEvent actionEvent) throws IOException
  {
    errorLabel.setVisible(true);
    ViewModelFactory.getInstance().getAddUserVM().addUser();
    ViewHandler.getInstance().openView("BorrowerWindow");
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

  @FXML public void onTypedLastNameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM()
        .lastNameProperty().get();
    lastNameError.setVisible(arg.isEmpty() || arg.matches(".*\\d.*"));
  }

  @FXML public void onTypedFirstNameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM()
        .firstNameProperty().get();
    firstNameError.setVisible(arg.isEmpty() || arg.matches(".*\\d.*"));
  }

  @FXML public void onTypedCprCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM().cprProperty()
        .get();
    String[] arr = arg.split("-");
    cprError.setVisible(arg.length() != 11 || !(containsOnlyDigits(arr[0])
        && containsOnlyDigits(arr[1])) || !arg.contains("-"));

  }

  @FXML public void onTypedStreetNameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM()
        .streetNameProperty().get();
    streetNameError.setVisible(arg.isEmpty() || arg.matches(".*\\d.*"));
  }

  @FXML public void onTypedCityCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM().cityProperty()
        .get();
    cityError.setVisible(arg.isEmpty() || arg.matches(".*\\d.*"));
  }

  @FXML public void onTypedZipCodeCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM().zipCodeProperty()
        .get();
    zipCodeError.setVisible(
        arg.isEmpty() || !containsOnlyDigits(arg) || arg.length() != 4);
  }

  @FXML public void onTypedStreetNoCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM()
        .streetNoProperty().get();
    streetNoError.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedPhoneNoCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM().phoneNoProperty()
        .get();
    //"^(\+\d{10}( )?)$" to check if the streng contains "+" and an 11 digit  number
    phoneError.setVisible(
        arg.isEmpty() || !arg.matches("^(\\+\\d{10}( )?)$") || !arg
            .contains("+45"));
  }

  @FXML public void onTypedEmailCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM().emailProperty()
        .get();
    emailError.setVisible(arg.isEmpty() || !arg.contains("@"));
  }

  @FXML public void onTypedPasswordCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM()
        .passwordProperty().get();
    passwordError.setVisible(arg.isEmpty());
  }

  private void clearFields()
  {
    for (TextField field : fields)
    {
      field.clear();
    }
  }
}
