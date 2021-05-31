package client.view.adduser;

import client.model.user.UserModelClient;
import client.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.person.Address;

import java.io.IOException;

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
  private StringProperty cityProperty;
  private StringProperty phoneNoProperty;
  private UserModelClient userModelClient;
  private StringProperty errorProperty;

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
    cityProperty = new SimpleStringProperty();
    phoneNoProperty = new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
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
    return cityProperty;
  }

  public StringProperty phoneNoProperty()
  {
    return phoneNoProperty;
  }

  public StringProperty errorProperty()
  {
    return errorProperty;
  }

  public void addUser() throws IOException
  {

    if (userFieldsAreEmpty())
    {
      errorProperty.setValue("nødvendige felter er tomme");
    }
    else
    {
      if (!cprAlreadyExists())
      {
        System.out.println("here");
        errorProperty.setValue(
            "Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else if (emailAlreadyExists())
      {
        errorProperty.setValue(
            "Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else if (phoneNumberAlreadyExists())
      {
        errorProperty.setValue(
            "Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else
      {
        errorProperty.setValue("");
        addNewUser();
        ViewHandler.getInstance().openView("Main");

      }
    }
  }

  private void addNewUser()
  {
    errorProperty.setValue("");
    userModelClient.registerBorrower(cprProperty.get(), firstNameProperty.get(),
        lastNameProperty.get(), emailProperty.get(), phoneNoProperty.get(),
        new Address(cityProperty.get(), streetNameProperty.get(),
            Integer.parseInt(zipCodeProperty.get()), streetNoProperty.get()),
        passwordProperty.get());
  }

  public boolean cprAlreadyExists()
  {
    return userModelClient.borrowerCprNumberAlreadyExists(cprProperty.get());
  }

  public boolean emailAlreadyExists()
  {
    return userModelClient.borrowerEmailAlreadyExists(emailProperty.get());
  }

  public boolean phoneNumberAlreadyExists()
  {
    return userModelClient
        .borrowerPhoneNumberAlreadyExists(phoneNoProperty.get());
  }

  public boolean borrowerAlreadyExists()
  {
    return userModelClient
        .borrowerAlreadyExists(cprProperty.get(), emailProperty.get(),
            phoneNoProperty.get());
  }

  public boolean userFieldsAreEmpty()
  {
    return emailProperty.get() == null || passwordProperty.get() == null
        || firstNameProperty.get() == null || lastNameProperty.get() == null
        || cprProperty.get() == null || streetNameProperty.get() == null
        || streetNoProperty.get() == null || zipCodeProperty.get() == null
        || cityProperty.get() == null || phoneNoProperty.get() == null;
  }
}
