package client.view.main;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainController
{
  @FXML private Label errorMessage;
  @FXML private TextField cprNo;
  @FXML private PasswordField password;

  public void init(ViewHandler viewHandler, MainVM mainVM)
  {
    viewHandler = ViewHandler.getInstance();
    ViewModelFactory.getInstance().getMainVM();
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
    if (ViewModelFactory.getInstance().getMainVM()
        .login(cprNo.getText(), password.getText()))
    {
      ViewHandler.getInstance().openView("User");
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
}
