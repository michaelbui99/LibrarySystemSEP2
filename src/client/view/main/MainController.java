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

  private MainVM mainVM;

  public void init(MainVM mainVM)
  {
    this.mainVM = mainVM;

    password.textProperty().bindBidirectional(
        mainVM.passwordProperty());
    cprNo.textProperty().bindBidirectional(
        mainVM.cprProperty());
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
    if (mainVM.login())
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
    String arg = mainVM.cprProperty().get();
    if (arg.isEmpty() || !arg.matches(".*\\d.*") || !arg.contains("-")
        || arg.length() != 11)
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
      String arg = mainVM.passwordProperty()
          .get();
      if (arg.isEmpty())
      {
        passwordError.setVisible(true);
      }
      else
      {
        passwordError.setVisible(false);
      }

    }
    catch (Exception e)
    {

    }

  }
}
