package client.view.adduser;

import client.core.ModelFactoryClient;
import client.model.user.UserModelClient;
import client.model.user.UserModelManagerClient;
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
  private StringProperty errorLabelProperty;
  private UserModelClient userModelClient;


  public AddUserVM(UserModelClient userModelClient)
  {
    this.userModelClient = userModelClient;
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
    errorLabelProperty = new SimpleStringProperty();
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

  public StringProperty errorLabelProperty()
  {
    return errorLabelProperty;
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

  public boolean cprAlreadyExists()
  {
    errorLabelProperty.setValue("Cpr number Already exists!!!");
    return ModelFactoryClient.getInstance().getUserModelClient()
        .borrowerCprNumberAlreadyExists(cprProperty.get());
  }

  public boolean emailAlreadyExists()
  {
    errorLabelProperty.setValue("Email already exists!!!");
    return ModelFactoryClient.getInstance().getUserModelClient()
        .borrowerEmailAlreadyExists(emailProperty.get());
  }

  public boolean phoneNumberAlreadyExists()
  {
    errorLabelProperty.setValue("Phone number already exists!!!");
    return ModelFactoryClient.getInstance().getUserModelClient()
        .borrowerPhoneNumberAlreadyExists(phoneNoProperty.get());
  }

  public boolean borrowerAlreadyExists()
  {
    errorLabelProperty.setValue("User already exists!!!");
    return ModelFactoryClient.getInstance().getUserModelClient()
        .borrowerAlreadyExists(cprProperty.get(), emailProperty.get(),
            phoneNoProperty.get());
  }
}
