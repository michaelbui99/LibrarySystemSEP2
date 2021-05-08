package client.view.main;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController
{
  private ViewHandler viewHandler;
  /**
   * the MainVM should be called using the singleton class viewModelFactory.
   * the field variable mainVM is set here temporarily until the
   * viewModelFactory is complete
   */
  private MainVM mainVM;

  @FXML private TextField email;
  @FXML private PasswordField password;

  public MainController(ViewHandler viewHandler)
  {
    //TODO use the ViewModelFactory.getInstance.getMainVM to instantiate the mainVM//
    this.viewHandler = viewHandler;
  }

  @FXML public void onButtonStaffLogin(ActionEvent actionEvent)
  {
    //TODO call the openView(arg) method from the viewHandler to open the StaffLoginView window//
  }

  @FXML public void onButtonAddUser(ActionEvent actionEvent)
  {
    //TODO call the openView(arg) method from the viewHandler to open the AddUserView window//
  }

  @FXML public void OnButtonLogin(ActionEvent actionEvent)
  {
    mainVM.login(email.getText(), password.getText());
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }
}
