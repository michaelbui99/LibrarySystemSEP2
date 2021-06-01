package client.view.addlibrarian;

import client.model.user.UserModelClient;
import client.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.person.Address;

import java.io.IOException;

/**View model for registering librarian
 * @author Kutaiba
 * @version 1.0
 * */
public class AddLibrarianVM
{
  private StringProperty lastNameProperty;
  private StringProperty firstNameProperty;
  private StringProperty cprProperty;
  private StringProperty streetNameProperty;
  private StringProperty cityProperty;
  private StringProperty zipCodeProperty;
  private StringProperty streetNoProperty;
  private StringProperty phoneProperty;
  private StringProperty employeeNoProperty;
  private StringProperty passwordProperty;
  private StringProperty emailProperty;
  private StringProperty errorLabelProperty;
  private UserModelClient userModelClient;

  public AddLibrarianVM(UserModelClient userModelClient)
  {
    this.userModelClient = userModelClient;
    lastNameProperty = new SimpleStringProperty();
    firstNameProperty = new SimpleStringProperty();
    cprProperty = new SimpleStringProperty();
    streetNameProperty = new SimpleStringProperty();
    streetNoProperty = new SimpleStringProperty();
    cityProperty = new SimpleStringProperty();
    zipCodeProperty = new SimpleStringProperty();
    phoneProperty = new SimpleStringProperty();
    employeeNoProperty = new SimpleStringProperty();
    passwordProperty = new SimpleStringProperty();
    emailProperty = new SimpleStringProperty();
    errorLabelProperty = new SimpleStringProperty();
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
    return streetNameProperty;
  }

  public StringProperty cityProperty()
  {
    return cityProperty;
  }

  public StringProperty zipCodeProperty()
  {
    return zipCodeProperty;
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
    return errorLabelProperty;
  }

  public void addLibrarian() throws IOException
  {
    if (userFieldsAreEmpty())
    {
      errorLabelProperty.setValue("nødvendige felter er tomme");
    }
    else
    {
      if (employeeNoAlreadyExists())
      {
        errorLabelProperty.setValue(
            "medarbejdernummer, Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else if (cprAlreadyExists())
      {
        errorLabelProperty.setValue(
            "medarbejdernummer, Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else if (emailAlreadyExists())
      {
        errorLabelProperty.setValue(
            "medarbejdernummer, Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else if (phoneNoAlreadyExists())
      {
        errorLabelProperty.setValue(
            "medarbejdernummer, Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
      }
      else
      {
        errorLabelProperty.setValue("");
        addNewUser();
        ViewHandler.getInstance().openView("Administration");
      }
    }
  }

  public void addNewUser()
  {
    userModelClient
        .registerLibrarian(Integer.parseInt(employeeNoProperty.get()),
            firstNameProperty.get(), lastNameProperty.get(), cprProperty.get(),
            phoneProperty.get(), emailProperty.get(),
            new Address(cityProperty.get(), streetNameProperty.get(),
                Integer.parseInt(zipCodeProperty.get()),
                streetNoProperty.get()), passwordProperty.get());
  }

  public boolean employeeNoAlreadyExists()
  {
    return userModelClient.employeeNumberAlreadyExists(
        Integer.parseInt(employeeNoProperty.get()));
  }

  public boolean cprAlreadyExists()
  {
    return userModelClient.librarianCprNumberAlreadyExists(cprProperty.get());
  }

  public boolean emailAlreadyExists()
  {
    return userModelClient.librarianEmailAlreadyExists(emailProperty.get());
  }

  public boolean phoneNoAlreadyExists()
  {
    return userModelClient
        .librarianPhoneNumberAlreadyExists(phoneProperty.get());
  }

  public boolean librarianAlreadyExists()
  {
    return userModelClient
        .librarianAlreadyExists(Integer.parseInt(employeeNoProperty.get()),
            cprProperty.get(), emailProperty.get(), phoneProperty.get());
  }

  public boolean userFieldsAreEmpty()
  {
    return emailProperty.get() == null || emailProperty.get() == null
        || passwordProperty.get() == null || firstNameProperty.get() == null
        || lastNameProperty.get() == null || cprProperty.get() == null
        || streetNoProperty.get() == null || streetNoProperty.get() == null
        || zipCodeProperty.get() == null || cityProperty.get() == null
        || passwordProperty.get() == null;
  }
}
