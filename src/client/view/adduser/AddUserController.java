package client.view.adduser;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
  @FXML private Label errorLable;

  @FXML private Button signupButton;

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
    errorLable.setVisible(false);
    errorLable.setTextFill(Paint.valueOf("red"));

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
    errorLable.textProperty().bind(
        ViewModelFactory.getInstance().getAddUserVM().errorLabelProperty());
  }

  @FXML public void onButtonSignup(ActionEvent actionEvent) throws IOException
  {
    boolean bol = ViewModelFactory.getInstance().getAddUserVM()
        .cprAlreadyExists();
    boolean bol1 = ViewModelFactory.getInstance().getAddUserVM()
        .emailAlreadyExists();
    boolean bol2 = ViewModelFactory.getInstance().getAddUserVM()
        .phoneNumberAlreadyExists();
    boolean bol3 = ViewModelFactory.getInstance().getAddUserVM()
        .borrowerAlreadyExists();

    if (bol)
    {
      errorLable.setVisible(true);
    }
    else if (bol1)
    {
      errorLable.setVisible(true);
    }
    else if (bol2)
    {
      errorLable.setVisible(true);
    }
    else if (bol3)
    {
      errorLable.setVisible(true);
    }
    else
    {
      errorLable.setVisible(false);
      ViewModelFactory.getInstance().getAddUserVM().addUser();
      ViewHandler.getInstance().openView("UserWindow");
      clearFields();
    }
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
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      lastNameError.setVisible(true);
    }
    else
      lastNameError.setVisible(false);
  }

  @FXML public void onTypedFirstNameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM()
        .firstNameProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      firstNameError.setVisible(true);
    }
    else
    {
      firstNameError.setVisible(false);
    }
  }

  @FXML public void onTypedCprCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM().cprProperty()
        .get();
    boolean bol = ViewModelFactory.getInstance().getAddUserVM()
        .cprAlreadyExists();
    if (arg.isEmpty() || !arg.matches(".*\\d.*") || !arg.contains("-")
        || arg.length() != 11)
    {
      cprError.setVisible(true);
    }
    else if (bol)
    {
      errorLable.setVisible(true);
    }
    else
    {
      cprError.setVisible(false);
      errorLable.setVisible(false);
    }
  }

  @FXML public void onTypedStreetnameCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM()
        .streetNameProperty().get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      streetNameError.setVisible(true);
    }
    else
    {
      streetNameError.setVisible(false);
    }
  }

  @FXML public void onTypedCityCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM().cityProperty()
        .get();
    if (arg.isEmpty() || arg.matches(".*\\d.*"))
    {
      cityError.setVisible(true);
    }
    else
    {
      cityError.setVisible(false);
    }
  }

  @FXML public void onTypedZipCodeCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM().zipCodeProperty()
        .get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*") || arg.length() != 4)
    {
      zipCodeError.setVisible(true);
    }
    else
    {
      zipCodeError.setVisible(false);
    }
  }

  @FXML public void onTypedStreetNoCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM()
        .streetNoProperty().get();
    if (arg.isEmpty())
    {
      streetNoError.setVisible(true);
    }
    else
    {
      streetNoError.setVisible(false);
    }
  }

  @FXML public void onTypedPhoneNoCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM().phoneNoProperty()
        .get();
    boolean bol = ViewModelFactory.getInstance().getAddUserVM()
        .phoneNumberAlreadyExists();
    if (arg.isEmpty() || !arg.contains("+45") || arg.length() != 11 || !arg
        .matches(".*\\d.*"))
    {
      phoneError.setVisible(true);
    }
    else if (bol)
    {
      errorLable.setVisible(true);
    }
    else
    {
      phoneError.setVisible(false);
      errorLable.setVisible(false);
    }
  }

  @FXML public void onTypedEmailCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM().emailProperty()
        .get();
    boolean bol = ViewModelFactory.getInstance().getAddUserVM()
        .emailAlreadyExists();
    if (arg.isEmpty() || !arg.contains("@"))
    {
      emailError.setVisible(true);
    }
    else if (bol)
    {
      errorLable.setVisible(true);
    }
    else
    {
      emailError.setVisible(false);
      errorLable.setVisible(false);
    }
  }

  @FXML public void onTypedPasswordCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getAddUserVM()
        .passwordProperty().get();
    if (arg.isEmpty())
    {
      passwordError.setVisible(true);
    }
    else
    {
      passwordError.setVisible(false);
    }
  }

  private void clearFields()
  {
    for (int i = 0; i < fields.size(); i++)
    {
      fields.get(i).clear();
    }
  }
}
