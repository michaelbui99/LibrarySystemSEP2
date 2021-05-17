package client.model.user;

import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;
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
    Borrower borrower = client.registerBorrower(cpr_no, f_name, l_name, email, tel_no, address, password);
    //support.firePropertyChange(EventTypes.BORROWERREGISTERED, null, borrower);
    return borrower;
  }

  @Override public boolean borrowerLogin(String cprNo, String password)
  {
    return client.borrowerLogin(cprNo, password);
  }

  @Override public Borrower getLoginUser()
  {
   return client.getLoginUser();
  }

  @Override public Librarian registerLibrarian(int employee_no,
      String firstName, String lastName, String cpr, String tlfNumber,
      String email, Address address, String password)
  {
    Librarian librarian = client.registerLibrarian(employee_no, firstName, lastName, cpr, tlfNumber, email, address, password);
    return librarian;
  }

  @Override public boolean librarianLogin(int employee_no, String password)
  {
    return client.librarianLogin(employee_no, password);
  }

  @Override public Librarian getLoginLibrarian()
  {
    return client.getLoginLibrarian();
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
