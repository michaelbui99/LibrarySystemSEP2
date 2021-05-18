package client.view.user;

import client.core.ModelFactoryClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserVM
{

  private StringProperty cprProperty;

  public UserVM()
  {
    //The StringProperty stores the users cpr such that we can differenatiate in the model which user is logged in from that specific client window.
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
