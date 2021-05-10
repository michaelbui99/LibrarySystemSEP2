package client.view.adduser;

import client.core.ViewModelFactory;
import client.model.loan.Address;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddUserController
{
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

  @FXML public void onButtonSignup(ActionEvent actionEvent) throws IOException
  {
    int zip_code = Integer.parseInt(zipCode.getText());

    int street_no = Integer.parseInt(streetNumber.getText());

    ViewModelFactory.getInstance().getAddUserVM()
        .addUser(cprNumber.getText(), firstName.getText(), lastName.getText(),
            email.getText(), phoneNumber.getText(),
            new Address(city.getText(), streetName.getText(), zip_code, street_no), password.getText());
ViewHandler.getInstance().openView("User");
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
