package client.view.adduser;

import client.core.ModelFactoryClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.person.Address;

public class AddUserVM
{
  private StringProperty emailProperty;
  private StringProperty passwordProperty;
  private StringProperty firstNameProperty;
  private StringProperty lastNameProperty;
  private StringProperty cprProperty;
  private StringProperty streetNameProperty;
  private StringProperty streetNoProperty;
  private StringProperty zipCodeProperty;
  private StringProperty cityproperty;
  private StringProperty phoneNoProperty;

  public AddUserVM()
  {
    emailProperty = new SimpleStringProperty();
    passwordProperty = new SimpleStringProperty();
    firstNameProperty = new SimpleStringProperty();
    lastNameProperty = new SimpleStringProperty();
    cprProperty = new SimpleStringProperty();
    streetNameProperty = new SimpleStringProperty();
    streetNoProperty = new SimpleStringProperty();
    zipCodeProperty = new SimpleStringProperty();
    cityproperty = new SimpleStringProperty();
    phoneNoProperty = new SimpleStringProperty();
  }

  public StringProperty emailProperty()
  {
    return emailProperty;
  }

  public StringProperty passwordProperty()
  {
    return passwordProperty;
  }

  public StringProperty firstNameProperty()
  {
    return firstNameProperty;
  }

  public StringProperty lastNameProperty()
  {
    return lastNameProperty;
  }

  public StringProperty cprProperty()
  {
    return cprProperty;
  }

  public StringProperty streetNameProperty()
  {
    return streetNameProperty;
  }

  public StringProperty streetNoProperty()
  {
    return streetNoProperty;
  }

  public StringProperty zipCodeProperty()
  {
    return zipCodeProperty;
  }

  public StringProperty cityProperty()
  {
    return cityproperty;
  }

  public StringProperty phoneNoProperty()
  {
    return phoneNoProperty;
  }

  public void addUser()
  {
    ModelFactoryClient.getInstance().getUserModelClient()
        .registerBorrower(cprProperty.get(), firstNameProperty.get(),
            lastNameProperty.get(), emailProperty.get(), phoneNoProperty.get(),
            new Address(cityproperty.get(), streetNameProperty.get(),
                Integer.parseInt(zipCodeProperty.get()),
                streetNoProperty.get()), passwordProperty.get());
  }
}
