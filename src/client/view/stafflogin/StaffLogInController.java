package client.view.stafflogin;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class StaffLogInController
{
  @FXML private Label errorMessage;
  @FXML private TextField employeeNo;
  @FXML private PasswordField password;
  @FXML private Label employeeNoError;
  @FXML private Label passwordError;

  public void init()
  {
    employeeNo.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getStaffLogInVM().employeeNoProperty());
    password.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getStaffLogInVM().passwordProperty());
    errorMessage.setVisible(false);
  }

  public void onButtonAddLibrarian(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("AddLibrarian");
  }

  public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  public void OnButtonLogin(ActionEvent actionEvent) throws IOException
  {
    if (ViewModelFactory.getInstance().getStaffLogInVM().login())
    {
      errorMessage.setVisible(false);
      ViewHandler.getInstance().openView("Administration");
    }
    else
    {
      errorMessage.setVisible(true);
    }
  }

  @FXML public void onKeyTypedEmployeeNoCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getStaffLogInVM()
        .employeeNoProperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*"))
    {
      employeeNoError.setVisible(true);
    }
    else
    {
      employeeNoError.setVisible(false);
    }
  }

  @FXML public void onKeyTypedPasswordCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getStaffLogInVM()
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
}
