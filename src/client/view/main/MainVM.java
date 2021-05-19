package client.view.main;

import client.core.ModelFactoryClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainVM
{

  private StringProperty passwordProperty;
  private StringProperty cprProperty;

  public MainVM()
  {
    passwordProperty = new SimpleStringProperty();
    cprProperty = new SimpleStringProperty();
  }

  public StringProperty passwordProperty()
  {
    return passwordProperty;
  }

  public StringProperty cprProperty()
  {
    return cprProperty;
  }

  public boolean login()
  {
   return ModelFactoryClient.getInstance().getUserModelClient()
        .borrowerLogin(cprProperty.get(), passwordProperty.get());
  }
}
