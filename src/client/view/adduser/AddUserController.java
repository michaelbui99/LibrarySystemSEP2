package client.view.adduser;

import client.model.loan.Address;
import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddUserController
{
  private ViewHandler viewHandler;
  /**
   * the AddUserVM should be called using the singleton class viewModelFactory.
   * the field variable addUserVM is set here temporarily until the
   * viewModelFactory is complete
   */
  private AddUserVM addUserVM;

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

  public AddUserController(ViewHandler viewHandler)
  {
    //TODO use the ViewModelFactory.getInstance.getAddUserVM to instantiate the addUSerVM//
    this.viewHandler = viewHandler;
  }

  @FXML public void onButtonLogin(ActionEvent actionEvent)
  {
    String zip = zipCode.getText();
    int zipInt = Integer.parseInt(zip);
    String cityString = city.getText();
    int cityInt = Integer.parseInt(cityString);
    addUserVM
        .addUser(cprNumber.getText(), firstName.getText(), lastName.getText(),
            email.getText(), phoneNumber.getText(),
            new Address(streetName.getText(), streetNumber.getText(), zipInt,
                cityInt), password.getText());

  }

  @FXML public void onButtonBack(ActionEvent actionEvent)
  {
    //TODO call the openView(arg) method from the viewHandler to open the mainView window//
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }
}
