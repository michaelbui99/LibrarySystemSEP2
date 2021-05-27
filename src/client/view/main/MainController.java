package client.view.main;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class MainController
{
  @FXML private Label errorMessage;
  @FXML private TextField cprNo;
  @FXML private PasswordField password;
  @FXML private Label cprError;
  @FXML private Label passwordError;

  public void init()
  {
    password.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getMainVM().passwordProperty());
    cprNo.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getMainVM().cprProperty());
  }

  @FXML public void onButtonStaffLogin(ActionEvent actionEvent)
      throws IOException
  {
    ViewHandler.getInstance().openView("StaffLogin");
  }

  @FXML public void onButtonAddUser(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("AddUser");
  }

  @FXML public void OnButtonLogin(ActionEvent actionEvent) throws IOException
  {
    if (ViewModelFactory.getInstance().getMainVM().login())
    {
      ViewHandler.getInstance().openView("BorrowerWindow");
    }
    else
    {
      errorMessage.setVisible(true);
    }
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  @FXML public void onTypedCprCheck(KeyEvent keyEvent)
  {
    String arg = ViewModelFactory.getInstance().getMainVM().cprProperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*") || !arg.contains("-") || arg.length() != 11)
    {
      cprError.setVisible(true);
    }
    else
    {
      cprError.setVisible(false);
    }
  }

  @FXML public void onTypedPasswordCheck(KeyEvent keyEvent)
  {
    try
    {
      String arg = ViewModelFactory.getInstance().getMainVM().passwordProperty().get();
      if (arg.isEmpty())
      {
        passwordError.setVisible(true);
      }
      else
      {
        passwordError.setVisible(false);
      }

    }catch (Exception e){

    }

  }
}
