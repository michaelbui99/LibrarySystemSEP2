package client.model.user;

import client.network.Client;
import shared.util.EventTypes;

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

  @Override public Borrower registerBorrower()
  {
    return null;
  }

  @Override public boolean Login()
  {
    return false;
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
