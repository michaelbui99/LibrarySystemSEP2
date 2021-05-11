package client.view.stafflogin;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class StaffLogInController
{
  @FXML private GridPane errorMessage;
  @FXML private TextField employeeNo;
  @FXML private PasswordField password;


  public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    viewHandler = ViewHandler.getInstance();
    viewModelFactory = ViewModelFactory.getInstance();
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
    int employee_no = Integer.parseInt(employeeNo.getText());

    if (ViewModelFactory.getInstance().getStaffLogInVM().login(employee_no, password.getText()))
    {
      ViewHandler.getInstance().openView("AddLibrarian");
    }
    else
    {
      errorMessage.setVisible(true);
    }
  }
}
