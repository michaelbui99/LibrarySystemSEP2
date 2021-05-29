package client.view.addlibrarian;

import client.core.ModelFactoryClient;
import client.model.user.UserModelClient;
import client.model.user.UserModelManagerClient;
import client.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.person.Address;

import java.io.IOException;

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
  private StringProperty errorLableProperty;
  private UserModelClient userModelClient;

  public AddLibrarianVM(UserModelClient userModelClient)
  {
    this.userModelClient = userModelClient;
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
    errorLableProperty = new SimpleStringProperty();
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

  public StringProperty streetNameProperty()
  {
    return streetnameProperty;
  }

  public StringProperty cityProperty()
  {
    return cityProperty;
  }

  public StringProperty zipCodeProperty()
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

  public StringProperty errorLabelProperty()
  {
    return errorLableProperty;
  }

  public void addLibrarian() throws IOException
  {
    if (userFieldsAreEmpty())
    {
      errorLableProperty.setValue("nødvendige felter er tomme");
    }
    else
    {
      if (employeeNoAlreadyExists())
      {
        errorLableProperty.setValue(
            "medarbejdernummer, Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else if (cprAlreadyExists())
      {
        errorLableProperty.setValue(
            "medarbejdernummer, Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else if (emailAlreadyExists())
      {
        errorLableProperty.setValue(
            "medarbejdernummer, Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else if (phoneNoAlreadyExists())
      {
        errorLableProperty.setValue(
            "medarbejdernummer, Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else
      {
        errorLableProperty.setValue("");
        addNewUser();
        ViewHandler.getInstance().openView("Administration");
      }
    }
  }

  public void addNewUser()
  {
    ModelFactoryClient.getInstance().getUserModelClient()
        .registerLibrarian(Integer.parseInt(employeeNoProperty.get()),
            firstNameProperty.get(), lastNameProperty.get(), cprProperty.get(),
            phoneProperty.get(), emailProperty.get(),
            new Address(cityProperty.get(), streetnameProperty.get(),
                Integer.parseInt(zipCodeproperty.get()),
                streetNoProperty.get()), passwordProperty.get());
  }

  public boolean employeeNoAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getUserModelClient()
        .employeeNumberAlreadyExists(
            Integer.parseInt(employeeNoProperty.get()));
  }

  public boolean cprAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getUserModelClient()
        .librarianCprNumberAlreadyExists(cprProperty.get());
  }

  public boolean emailAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getUserModelClient()
        .librarianEmailAlreadyExists(emailProperty.get());
  }

  public boolean phoneNoAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getUserModelClient()
        .librarianPhoneNumberAlreadyExists(phoneProperty.get());
  }

  public boolean librarianAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getUserModelClient()
        .librarianAlreadyExists(Integer.parseInt(employeeNoProperty.get()),
            cprProperty.get(), emailProperty.get(), phoneProperty.get());
  }

  public boolean userFieldsAreEmpty()
  {
    return emailProperty.get() == null || emailProperty.get() == null
        || passwordProperty.get() == null || firstNameProperty.get() == null
        || lastNameProperty.get() == null || cprProperty.get() == null
        || streetNoProperty.get() == null || streetNoProperty.get() == null
        || zipCodeproperty.get() == null || cityProperty.get() == null
        || passwordProperty.get() == null;
  }
}
