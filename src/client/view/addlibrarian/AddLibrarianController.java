package client.view.addlibrarian;

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

public class AddLibrarianController
{
  @FXML private Button signUpButton;
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

  /**
   * This pattern return true if String contains any thing other than 0-9 digit,
   * which can be used to know if an String is number or not using regular expression.
   * email.getText().matches(".*\\d.*)"
   */
  public void init()
  {

  }

  @FXML public boolean onMouseExitedCheckLastName()
  {
    if (lastName.getText().isEmpty() || lastName.getText().matches(".*\\d.*"))
    {
      lNameError.setVisible(true);
      return true;
    }
    else
    {
      lNameError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitedCheckFirstName()
  {
    if (firstName.getText().isEmpty() || firstName.getText().matches(".*\\d.*"))
    {
      fNameError.setVisible(true);
      return true;
    }
    else
    {
      fNameError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitedCheckCPR()
  {
    if ((cprNumber.getText().isEmpty()) || !(cprNumber.getText().contains("-"))
        || (cprNumber.getText().length() != 11) || !(cprNumber.getText()
        .matches(".*\\d.*")))
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

  @FXML public boolean onMouseExitedCheckStreetName()
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

  @FXML public boolean onMouseExitedCheckCity()
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

  @FXML public boolean onMouseExitedCheckZipCode()
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

  @FXML public boolean onMouseExitedCheckStreetNumber()
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

  @FXML public boolean onMouseExitedCheckPhoneNumber()
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

  @FXML public boolean onMouseExitedCheckEmployeeNumber()
  {
    if ((employeeNo.getText().isEmpty()) || (!(phoneNumber.getText()
        .matches(".*\\d.*"))))
    {
      employeeNoError.setVisible(true);
      return true;
    }
    else
    {
      employeeNoError.setVisible(false);
      return false;
    }
  }

  @FXML public boolean onMouseExitedCheckPassword()
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

  @FXML public boolean onMouseExitedCheckEmail()
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

  @FXML public void onButtonSignUp(ActionEvent actionEvent) throws IOException
  {
    int employee_no = Integer.parseInt(employeeNo.getText());
    int zip_code = Integer.parseInt(zipCode.getText());
    int street_no = Integer.parseInt(streetNumber.getText());

    if ((onMouseExitedCheckEmployeeNumber()) && (onMouseExitedCheckEmail())
        && (onMouseExitedCheckFirstName()) && (onMouseExitedCheckLastName())
        && (onMouseExitedCheckPassword()) && (onMouseExitedCheckCity())
        && (onMouseExitedCheckStreetName()) && (onMouseExitedCheckZipCode())
        && (onMouseExitedCheckStreetNumber())
        && (onMouseExitedCheckPhoneNumber()) && (onMouseExitedCheckCPR()))
    {
      signUpButton.setDisable(true);
    }
    else
    {
      signUpButton.setDisable(false);
      ViewModelFactory.getInstance().getAddLibrarianVM()
          .addLibrarian(employee_no, firstName.getText(), lastName.getText(),
              cprNumber.getText(), phoneNumber.getText(), email.getText(),
              new Address(city.getText(), streetName.getText(), zip_code,
                  street_no), password.getText());
      ViewHandler.getInstance().openView("Administration");
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
