package client.view.user;

import client.core.ModelFactoryClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserVM
{
  private StringProperty cprProperty;

  public UserVM()
  {
    cprProperty = new SimpleStringProperty(ModelFactoryClient.getInstance().getUserModelClient().getLoginUser().getCpr());
  }

  public void setBorrowerCPR()
  {
    ModelFactoryClient.getInstance().getUserModelClient().setBorrowerCpr(cprProperty.get());
  }


  public StringProperty cprPropertyProperty()
  {
    return cprProperty;
  }
}
