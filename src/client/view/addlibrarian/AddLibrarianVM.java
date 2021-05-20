package client.view.addlibrarian;

import client.core.ModelFactoryClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.person.Address;

public class AddLibrarianVM
{
  private StringProperty lastNameProperty;
  private StringProperty firstNameProperty;
  private StringProperty cprProperty;
  private StringProperty streetnameProperty;
  private StringProperty cityProperty;
  private StringProperty zipCodeproperty;
  private StringProperty streetNoProperty;
  private StringProperty phoneProperty;
  private StringProperty employeeNoProperty;
  private StringProperty passwordProperty;
  private StringProperty emailProperty;

  public AddLibrarianVM()
  {
    lastNameProperty = new SimpleStringProperty();
    firstNameProperty = new SimpleStringProperty();
    cprProperty = new SimpleStringProperty();
    streetnameProperty = new SimpleStringProperty();
    streetNoProperty = new SimpleStringProperty();
    cityProperty = new SimpleStringProperty();
    zipCodeproperty = new SimpleStringProperty();
    phoneProperty = new SimpleStringProperty();
    employeeNoProperty = new SimpleStringProperty();
    passwordProperty = new SimpleStringProperty();
    emailProperty = new SimpleStringProperty();
  }

  public StringProperty lastNameProperty()
  {
    return lastNameProperty;
  }

  public StringProperty firstNameProperty()
  {
    return firstNameProperty;
  }

  public StringProperty cprProperty()
  {
    return cprProperty;
  }

  public StringProperty streetnameProperty()
  {
    return streetnameProperty;
  }

  public StringProperty cityProperty()
  {
    return cityProperty;
  }

  public StringProperty zipCodeproperty()
  {
    return zipCodeproperty;
  }

  public StringProperty streetNoProperty()
  {
    return streetNoProperty;
  }

  public StringProperty phoneProperty()
  {
    return phoneProperty;
  }

  public StringProperty employeeNoProperty()
  {
    return employeeNoProperty;
  }

  public StringProperty passwordProperty()
  {
    return passwordProperty;
  }

  public StringProperty emailProperty()
  {
    return emailProperty;
  }

  public void addLibrarian()
  {
    ModelFactoryClient.getInstance().getUserModelClient()
        .registerLibrarian(Integer.parseInt(employeeNoProperty.get()),
            firstNameProperty.getName(), lastNameProperty.getName(),
            cprProperty.get(), phoneProperty.get(), emailProperty.get(),
            new Address(cityProperty.get(), streetnameProperty.get(),
                Integer.parseInt(zipCodeproperty.get()),
                streetNoProperty.get()), passwordProperty.get());
  }
}
