package client.view.stafflogin;

import client.core.ModelFactoryClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StaffLogInVM
{
  private StringProperty employeeNoProperty;
  private StringProperty passwordProperty;

  public StaffLogInVM()
  {
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
    return ModelFactoryClient.getInstance().getUserModelClient()
        .librarianLogin(Integer.parseInt(employeeNoProperty.get()), passwordProperty
            .get());
  }
}
