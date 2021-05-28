package client.view.borrowercontactinfo;

import client.model.user.UserModelClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.person.Address;
import shared.person.borrower.Borrower;

import java.util.NoSuchElementException;

public class BorrowerContactInfoVM
{
  private UserModelClient userModel;
  private StringProperty firstNameProperty, lastNameProperty, phoneNumberProperty, cprProperty, addressProperty, emailProperty, warningProperty;

  public BorrowerContactInfoVM(UserModelClient userModel)
  {
    this.userModel = userModel;
    firstNameProperty = new SimpleStringProperty();
    lastNameProperty = new SimpleStringProperty();
    phoneNumberProperty = new SimpleStringProperty();
    cprProperty = new SimpleStringProperty();
    addressProperty = new SimpleStringProperty();
    emailProperty = new SimpleStringProperty();
    warningProperty = new SimpleStringProperty();
  }

  public void getBorrowerInfo()
  {
    if (!cprProperty.get().contains("-") || cprProperty.get().length() != 11)
    {
      warningProperty.set("Ugyldigt CPR");
    }
    else if (cprProperty.get() == null || cprProperty.get().isEmpty())
    {
      warningProperty.set("Indtast et CPR nummer.");
    }
    else
    {
      try
      {
        Borrower borrower = userModel.getBorrowerByCPR(cprProperty.get());
        Address address = borrower.getAddress();
        String addressString =
            address.getStreetName() + " " + address.getStreetNr() + " " + address.getZipCode() + " " + address.getCity();
        firstNameProperty.set(borrower.getFirstName());
        lastNameProperty.set(borrower.getLastName());
        emailProperty.set(borrower.getEmail());
        addressProperty.set(addressString);
        phoneNumberProperty.set(borrower.getTlfNumber());
        warningProperty.set("");
      }
      catch (NoSuchElementException e)
      {
        warningProperty.set(e.getMessage());
      }
    }
  }

  public void clearAllProperties()
  {
    firstNameProperty.set("");
    lastNameProperty.set("");
    phoneNumberProperty.set("");
    cprProperty.set("");
    addressProperty.set("");
    emailProperty.set("");
  }

  public StringProperty firstNameProperty()
  {
    return firstNameProperty;
  }

  public StringProperty lastNameProperty()
  {
    return lastNameProperty;
  }

  public StringProperty phoneNumberProperty()
  {
    return phoneNumberProperty;
  }

  public StringProperty cprProperty()
  {
    return cprProperty;
  }

  public StringProperty addressProperty()
  {
    return addressProperty;
  }

  public StringProperty emailProperty()
  {
    return emailProperty;
  }

  public StringProperty warningProperty()
  {
    return warningProperty;
  }
}
