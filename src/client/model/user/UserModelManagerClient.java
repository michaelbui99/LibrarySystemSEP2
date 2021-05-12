package client.model.user;

import client.model.loan.Address;
import client.model.user.borrower.Borrower;
import client.model.user.librarian.Librarian;
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
    client.startClient();
    support = new PropertyChangeSupport(this);
  }

  @Override public Borrower registerBorrower(String cpr_no, String f_name,
      String l_name, String email, String tel_no, Address address,
      String password)
  {
    //TODO call the method registerBorrower(args) from the Client and then fire an event//
    return null;
  }

  @Override public boolean borrowerLogin(String cprNo, String password)
  {
    //TODO call the method Login from the Client and then fire an event//
    return client.borrowerLogin(cprNo, password);
  }

  @Override public Borrower getLoginUser()
  {
    //TODO call the method getLogInUser from the Client and then fire an event//
    return null;
  }

  @Override public Librarian registerLibrarian(int employee_no,
      String firstName, String lastName, String cpr, String tlfNumber,
      String email, Address address, String password)
  {
    return null;
  }

  @Override public boolean librarianLogin(int employee_no, String password)
  {
    return false;
  }

  @Override public Librarian getLoginLibrarian()
  {
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
