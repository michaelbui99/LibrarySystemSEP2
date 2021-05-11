package client.view.addlibrarian;

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
  @FXML private PasswordField email;

  public void init(ViewHandler viewHandler, AddLibrarianVM addLibrarianVM)
  {
    viewHandler = ViewHandler.getInstance();
    addLibrarianVM = ViewModelFactory.getInstance().getAddLibrarianVM();
  }

  @FXML public boolean onMouseExitedCheckLastName()
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

  @FXML public boolean onMouseExitedCheckFirstName()
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

  @FXML public boolean onMouseExitedCheckCPR()
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

  @FXML public boolean onMouseExitedCheckStreetName()
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

  @FXML public boolean onMouseExitedCheckCity()
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

  @FXML public boolean onMouseExitedCheckZipCode()
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

  @FXML public boolean onMouseExitedCheckStreetNumber()
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

  @FXML public boolean onMouseExitedCheckPhoneNumber()
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

  @FXML public boolean onMouseExitedCheckEmployeeNumber()
  {
    if ((employeeNo.getText().isEmpty()) || (!(phoneNumber.getText()
        .matches(".*\\d.*"))))
    {
      employeeNo.setBorder(
          new Border((BorderStroke) BorderFactory.createLineBorder(Color.red)));
      return true;
    }
    else
    {
      employeeNo.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      return false;
    }
  }

  @FXML public boolean onMouseExitedCheckPassword()
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

  @FXML public boolean onMouseExitedCheckEmail()
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

  @FXML public void onButtonSignUp(ActionEvent actionEvent) throws IOException
  {
    int employee_no = Integer.parseInt(employeeNo.getText());
    int zip_code = Integer.parseInt(zipCode.getText());
    int street_no = Integer.parseInt(streetNumber.getText());

    if ((onMouseExitedCheckEmployeeNumber()) || (onMouseExitedCheckEmail())
        || (onMouseExitedCheckFirstName()) || (onMouseExitedCheckLastName())
        || (onMouseExitedCheckPassword()) || (onMouseExitedCheckCity())
        || (onMouseExitedCheckStreetName()) || (onMouseExitedCheckZipCode())
        || (onMouseExitedCheckStreetNumber())
        || (onMouseExitedCheckPhoneNumber()) || (onMouseExitedCheckCPR()))
    {
      signUpButton.setBorder(new Border(
          (BorderStroke) BorderFactory.createLineBorder(Color.green)));
      signUpButton.setDisable(true);
    }
    else
    {
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
