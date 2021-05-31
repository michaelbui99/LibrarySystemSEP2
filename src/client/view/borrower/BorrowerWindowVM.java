package client.view.borrower;

import client.model.user.UserModelClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BorrowerWindowVM
{

  private StringProperty cprProperty;
  private UserModelClient userModel;

  public BorrowerWindowVM(UserModelClient userModel)
  {
    //The StringProperty stores the users cpr such that we can differenatiate in the model which user is logged in from that specific client window.
    this.userModel = userModel;
    cprProperty = new SimpleStringProperty(userModel.getLoginUser().getCpr());
  }

  public void setBorrowerCPR()
  {
    userModel.setBorrowerCpr(cprProperty.get());
  }

  public StringProperty cprPropertyProperty()
  {
    return cprProperty;
  }
}
