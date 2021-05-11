package client.view.adduser;

import client.core.ViewModelFactory;
import client.model.loan.Address;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AddUserController
{
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

  public void init(ViewHandler viewHandler, AddUserVM addUserVM)
  {
    viewHandler = ViewHandler.getInstance();
    addUserVM = ViewModelFactory.getInstance().getAddUserVM();
  }

  /**
   * This pattern return true if String contains any thing other than 0-9 digit,
   * which can be used to know if an String is number or not using regular expression.
   * email.getText().matches(".*\\d.*)"
   */

  @FXML public boolean onMouseExitCheckEmail()
  {
    if (email.getText().isEmpty() || !email.getText().contains("@"))
    {
      email.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      email.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckLastName()
  {
    if (lastName.getText().isEmpty() || lastName.getText().matches(".*\\d.*"))
    {
      lastName.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      lastName.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckFirstName()
  {
    if (firstName.getText().isEmpty() || firstName.getText().matches(".*\\d.*"))
    {
      firstName.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      firstName.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckCPR()
  {
    if ((cprNumber.getText().isEmpty()) || !(cprNumber.getText().contains("-"))
        || (cprNumber.getText().length() != 11))
    {
      cprNumber.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      cprNumber.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckStreetName()
  {
    if (streetName.getText().isEmpty() || streetName.getText()
        .matches(".*\\d.*"))
    {
      streetName.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      streetName.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckCity()
  {
    if (city.getText().isEmpty() || city.getText().matches(".*\\d.*"))
    {
      city.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      city.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckZipCode()
  {
    if (zipCode.getText().isEmpty() || !zipCode.getText().matches(".*\\d.*")
        || (zipCode.getText().length() != 4))
    {
      zipCode.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      zipCode.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckStreetNumber()
  {
    if (streetNumber.getText().isEmpty() || !streetNumber.getText()
        .matches(".*\\d.*"))
    {
      streetNumber.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      streetNumber.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  @FXML public boolean onMouseExitCheckPhoneNumber()
  {
    if ((phoneNumber.getText().isEmpty()) || !(phoneNumber.getText()
        .matches(".*\\d.*")) || !(phoneNumber.getText().contains("+45")) || (
        phoneNumber.getText().length() != 11))
    {
      phoneNumber.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      phoneNumber.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  public boolean onMouseExitCheckPassword()
  {
    if (password.getText().isEmpty())
    {
      password.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      password.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  @FXML public void onButtonSignup(ActionEvent actionEvent) throws IOException
  {
    int zip_code = Integer.parseInt(zipCode.getText());

    int street_no = Integer.parseInt(streetNumber.getText());
    if ((onMouseExitCheckEmail()) || (onMouseExitCheckFirstName())
        || (onMouseExitCheckLastName()) || (onMouseExitCheckCPR())
        || (onMouseExitCheckPassword()) || (onMouseExitCheckPhoneNumber())
        || (onMouseExitCheckStreetName()) || (onMouseExitCheckStreetNumber())
        || (onMouseExitCheckZipCode()) || (onMouseExitCheckCity()))
    {
      signupButton.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      signupButton.setDisable(true);
    }
    else
    {
      ViewModelFactory.getInstance().getAddUserVM()
          .addUser(cprNumber.getText(), firstName.getText(), lastName.getText(),
              email.getText(), phoneNumber.getText(),
              new Address(city.getText(), streetName.getText(), zip_code,
                  street_no), password.getText());
      ViewHandler.getInstance().openView("User");
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
