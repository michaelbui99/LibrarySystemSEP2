package client.view.stafflogin;

import client.model.user.UserModelClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * View model for librarian log in
 *
 * @author Kutaiba
 * @version 1.0
 */
public class StaffLogInVM
{
  private StringProperty employeeNoProperty;
  private StringProperty passwordProperty;
  private UserModelClient userModel;

  public StaffLogInVM(UserModelClient userModel)
  {
    this.userModel = userModel;
    employeeNoProperty = new SimpleStringProperty();
    passwordProperty = new SimpleStringProperty();
  }

  public StringProperty employeeNoProperty()
  {
    return employeeNoProperty;
  }

  public StringProperty passwordProperty()
  {
    return passwordProperty;
  }

  public boolean login()
  {
    return userModel.librarianLogin(Integer.parseInt(employeeNoProperty.get()),
        passwordProperty.get());
  }
}
