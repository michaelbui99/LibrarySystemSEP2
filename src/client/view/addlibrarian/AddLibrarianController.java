package client.view.addlibrarian;

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

/**
 * Controller for registering librarian
 *
 * @author Kutaiba
 * @version 1.0
 */
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
  @FXML private Label errorLabel;

  private ObservableList<TextField> fields = FXCollections
      .observableArrayList();

  private AddLibrarianVM librarianVM;

  public void init(AddLibrarianVM librarianVM)
  {
    this.librarianVM = librarianVM;

    errorLabel.setVisible(false);
    errorLabel.setTextFill(Paint.valueOf("red"));

    fields.addAll(lastName, firstName, cprNumber, streetName, city, zipCode,
        streetNumber, phoneNumber, employeeNo, password, email);

    lastName.textProperty().bindBidirectional(librarianVM.lastNameProperty());
    firstName.textProperty().bindBidirectional(librarianVM.firstNameProperty());
    cprNumber.textProperty().bindBidirectional(librarianVM.cprProperty());
    streetName.textProperty()
        .bindBidirectional(librarianVM.streetNameProperty());
    city.textProperty().bindBidirectional(librarianVM.cityProperty());
    zipCode.textProperty().bindBidirectional(librarianVM.zipCodeProperty());
    streetNumber.textProperty()
        .bindBidirectional(librarianVM.streetNoProperty());
    phoneNumber.textProperty().bindBidirectional(librarianVM.phoneProperty());
    email.textProperty().bindBidirectional(librarianVM.emailProperty());
    password.textProperty().bindBidirectional(librarianVM.passwordProperty());
    employeeNo.textProperty()
        .bindBidirectional(librarianVM.employeeNoProperty());
    errorLabel.textProperty().bind(librarianVM.errorLabelProperty());
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
    errorLabel.setVisible(true);
    librarianVM.addLibrarian();
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
    String arg = librarianVM.emailProperty().get();
    emailError.setVisible(arg.isEmpty() || !arg.contains("@"));
  }

  @FXML public void onTypedPasswordCheck(KeyEvent keyEvent)
  {
    String arg = librarianVM.passwordProperty().get();
    passwordError.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedEmployeeNOCheck(KeyEvent keyEvent)
  {
    // \\d+ check if the string consists of only numbers

    String arg = librarianVM.employeeNoProperty().get();
    employeeNoError.setVisible(arg.isEmpty() || !arg.matches("\\d+"));
  }

  @FXML public void onTypedPhoneNo(KeyEvent keyEvent)
  {
    String arg = librarianVM.phoneProperty().get();
    //"^(\+\d{10}( )?)$" to check if the string contains "+" and an 11 digit  number
    phoneError.setVisible(
        arg.isEmpty() || !arg.matches("^(\\+\\d{10}( )?)$") || !arg
            .contains("+45"));
  }

  @FXML public void onTypeStreetNoCheck(KeyEvent keyEvent)
  {
    String arg = librarianVM.streetNoProperty().get();
    streetNoError.setVisible(arg.isEmpty());
  }

  @FXML public void onTypedZipCode(KeyEvent keyEvent)
  {
    // \\d+ check if the string consists of only numbers

    String arg = librarianVM.zipCodeProperty().get();
    zipCodeError
        .setVisible(arg.isEmpty() || !arg.matches("\\d+") || arg.length() != 4);
  }

  @FXML public void onTypedCity(KeyEvent keyEvent)
  {
    // [a-zA-Z]+ check if the string consists of only numbers

    String arg = librarianVM.cityProperty().get();
    cityError.setVisible(arg.isEmpty() || !arg.matches("[a-zA-Z]+"));
  }

  @FXML public void onTypeStreetNameCheck(KeyEvent keyEvent)
  {
    // [a-zA-Z]+ check if the string consists of only numbers

    String arg = librarianVM.streetNameProperty().get();
    streetNameError.setVisible(arg.isEmpty() || !arg.matches("[a-zA-Z]+"));
  }

  @FXML public void onTypedCprCheck(KeyEvent keyEvent)
  {
    String arg = librarianVM.cprProperty().get();
    String[] arr = arg.split("-");
    cprError.setVisible(arg.length() != 11 || !(containsOnlyDigits(arr[0])
        && containsOnlyDigits(arr[1])) || !arg.contains("-"));
  }

  @FXML public void onTypedFirstNameCheck(KeyEvent keyEvent)
  {
    // [a-zA-Z]+ check if the string consists of only numbers

    String arg = librarianVM.firstNameProperty().get();
    fNameError.setVisible(arg.isEmpty() || !arg.matches("[a-zA-Z]+"));
  }

  @FXML public void onTypedLastNameCheck(KeyEvent keyEvent)
  {
    // [a-zA-Z]+ check if the string consists of only numbers

    String arg = librarianVM.lastNameProperty().get();
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
