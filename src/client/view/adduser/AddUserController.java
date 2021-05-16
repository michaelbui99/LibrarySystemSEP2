package client.view.adduser;

import client.core.ViewModelFactory;
import shared.places.Address;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

  /**
   * This pattern return true if String contains any thing other than 0-9 digit,
   * which can be used to know if an String is number or not using regular expression.
   * email.getText().matches(".*\\d.*)"
   */
  public void init()
  {
  }

  @FXML public boolean onMouseExitCheckEmail()
  {
    if (email.getText().isEmpty() || !email.getText().contains("@"))
    {
      emailError.setVisible(true);
      return true;
    }
    else
    {
      emailError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckLastName()
  {
    if (lastName.getText().isEmpty() || lastName.getText().matches(".*\\d.*"))
    {
      lastNameError.setVisible(true);
      return true;
    }
    else
    {
      lastNameError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckFirstName()
  {
    if (firstName.getText().isEmpty() || firstName.getText().matches(".*\\d.*"))
    {
      firstNameError.setVisible(true);
      return true;
    }
    else
    {
      firstNameError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckCPR()
  {
    if ((cprNumber.getText().isEmpty()) || !(cprNumber.getText().contains("-"))
        || (cprNumber.getText().length() != 11))
    {
      cprError.setVisible(true);
      return true;
    }
    else
    {
      cprError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckStreetName()
  {
    if (streetName.getText().isEmpty() || streetName.getText()
        .matches(".*\\d.*"))
    {
      streetNameError.setVisible(true);
      return true;
    }
    else
    {
      streetNameError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckCity()
  {
    if (city.getText().isEmpty() || city.getText().matches(".*\\d.*"))
    {
      cityError.setVisible(true);
      return true;
    }
    else
    {
      cityError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckZipCode()
  {
    if (zipCode.getText().isEmpty() || !zipCode.getText().matches(".*\\d.*")
        || (zipCode.getText().length() != 4))
    {
      zipCodeError.setVisible(true);
      return true;
    }
    else
    {
      zipCodeError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckStreetNumber()
  {
    if (streetNumber.getText().isEmpty() || !streetNumber.getText()
        .matches(".*\\d.*"))
    {
      streetNoError.setVisible(true);
      return true;
    }
    else
    {
      streetNoError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckPhoneNumber()
  {
    if ((phoneNumber.getText().isEmpty()) || !(phoneNumber.getText()
        .matches(".*\\d.*")) || !(phoneNumber.getText().contains("+45")) || (
        phoneNumber.getText().length() != 11))
    {
      phoneError.setVisible(true);
      return true;
    }
    else
    {
      phoneError.setVisible(false);
      return false;
    }
  }

  public boolean onMouseExitCheckPassword()
  {
    if (password.getText().isEmpty())
    {
      passwordError.setVisible(true);
      return true;
    }
    else
    {
      passwordError.setVisible(false);
      return false;
    }
  }

  @FXML public void onButtonSignup(ActionEvent actionEvent) throws IOException
  {
    int zip_code = Integer.parseInt(zipCode.getText());
    int street_no = Integer.parseInt(streetNumber.getText());

    if ((onMouseExitCheckEmail()) && (onMouseExitCheckFirstName())
        && (onMouseExitCheckLastName()) && (onMouseExitCheckCPR())
        && (onMouseExitCheckPassword()) && (onMouseExitCheckPhoneNumber())
        && (onMouseExitCheckStreetName()) && (onMouseExitCheckStreetNumber())
        && (onMouseExitCheckZipCode()) && (onMouseExitCheckCity()))
    {
      signupButton.setDisable(true);
    }
    else
    {
            ViewModelFactory.getInstance().getAddUserVM()
          .addUser(cprNumber.getText(), firstName.getText(), lastName.getText(),
              email.getText(), phoneNumber.getText(),
              new Address(city.getText(), streetName.getText(), zip_code,
                  street_no), password.getText());
      ViewHandler.getInstance().openView("UserWindow");
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
}
