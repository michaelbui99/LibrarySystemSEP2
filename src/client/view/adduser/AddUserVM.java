package client.view.adduser;

import client.core.ModelFactoryClient;
import client.model.user.UserModelClient;
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

  public void addUser()
  {

    if (cprAlreadyExists())
    {
      errorProperty
          .setValue("Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
    }
    else if (emailAlreadyExists())
    {
      errorProperty
          .setValue("Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
    }
    else if (phoneNumberAlreadyExists())
    {
      errorProperty
          .setValue("Cpr-nummer, e-mail eller telefonnummer er allerede brugt");
    }
    else if (userFieldsAreEmpty())
    {
      errorProperty.setValue("nødvendige felter er tomme");
    }
    else
    {
      addNewUser();
    }
  }

  private void addNewUser()
  {
    errorProperty.setValue("");
    ModelFactoryClient.getInstance().getUserModelClient()
        .registerBorrower(cprProperty.get(), firstNameProperty.get(),
            lastNameProperty.get(), emailProperty.get(), phoneNoProperty.get(),
            new Address(cityProperty.get(), streetNameProperty.get(),
                Integer.parseInt(zipCodeProperty.get()),
                streetNoProperty.get()), passwordProperty.get());
  }

  public boolean cprAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getUserModelClient()
        .borrowerCprNumberAlreadyExists(cprProperty.get());
  }

  public boolean emailAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getUserModelClient()
        .borrowerEmailAlreadyExists(emailProperty.get());
  }

  public boolean phoneNumberAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getUserModelClient()
        .borrowerPhoneNumberAlreadyExists(phoneNoProperty.get());
  }

  public boolean borrowerAlreadyExists()
  {
    return ModelFactoryClient.getInstance().getUserModelClient()
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
