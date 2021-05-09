package client.model.user;

import client.model.loan.Address;
import client.network.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserModelManagerClient implements UserModelClient
{
  private Client client;
  private PropertyChangeSupport support;

  public UserModelManagerClient(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
  }

  @Override public Borrower registerBorrower(String cpr_no, String f_name,
      String l_name, String email, String tel_no, Address address_id,
      String password)
  {
    //TODO call the method registerBorrower(args) from the Client and then fire an event//
    return null;
  }

  @Override public boolean Login(String cprNo, String password)
  {
    //TODO call the method Login from the Client and then fire an event//
    return false;
  }

  @Override public Borrower getLogInUser()
  {
    //TODO call the method getLogInUser from the Client and then fire an event//
    return null;
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
