package client.view.addlibrarian;

import client.core.ViewModelFactory;
import client.model.loan.Address;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddLibrarianController
{
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

  @FXML public void onButtonSignUp(ActionEvent actionEvent) throws IOException
  {
    int employee_no = Integer.parseInt(employeeNo.getText());
    int zip_code = Integer.parseInt(zipCode.getText());
    int street_no = Integer.parseInt(streetNumber.getText());

    ViewModelFactory.getInstance().getAddLibrarianVM()
        .addLibrarian(employee_no, firstName.getText(), lastName.getText(),
            cprNumber.getText(), phoneNumber.getText(), email.getText(),
            new Address(city.getText(), streetName.getText(), zip_code,
                street_no), password.getText());
    ViewHandler.getInstance().openView("Administration");
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
