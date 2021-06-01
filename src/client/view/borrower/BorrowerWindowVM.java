package client.view.borrower;

import client.model.user.UserModelClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * View mode for the borrower navigation window
 *
 * @author Kutaiba
 * @version 1.0
 */
public class BorrowerWindowVM
{

  private StringProperty cprProperty;
  private UserModelClient userModel;

  public BorrowerWindowVM(UserModelClient userModel)
  {
    //The StringProperty stores the users cpr such that we can differentiate in the model which user is logged in from that specific client window.
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
