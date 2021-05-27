package client.model.user;

import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;
import client.network.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.NoSuchElementException;

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
    Borrower borrower = client
        .registerBorrower(cpr_no, f_name, l_name, email, tel_no, address,
            password);
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
    Librarian librarian = client
        .registerLibrarian(employee_no, firstName, lastName, cpr, tlfNumber,
            email, address, password);
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

  @Override public void setBorrowerCpr(String borrowerCpr)
  {
    //client.setBorrowerCpr(borrowerCpr);
  }

  @Override public boolean borrowerCprNumberAlreadyExists(String cpr)
  {
    return client.borrowerCprNumberAlreadyExists(cpr);
  }

  @Override public boolean borrowerEmailAlreadyExists(String email)
  {
    return client.borrowerEmailAlreadyExists(email);
  }

  @Override public boolean borrowerPhoneNumberAlreadyExists(String phone)
  {
    return client.borrowerPhoneNumberAlreadyExists(phone);
  }

  @Override public boolean borrowerAlreadyExists(String cpr, String email,
      String phone)
  {
    return client.borrowerAlreadyExists(cpr, email, phone);
  }

  @Override public boolean employeeNumberAlreadyExists(int employeeNo)
  {
    return client.employeeNumberAlreadyExists(employeeNo);
  }

  @Override public boolean librarianCprNumberAlreadyExists(String cpr)
  {
    return client.librarianCprNumberAlreadyExists(cpr);
  }

  @Override public boolean librarianEmailAlreadyExists(String email)
  {
    return client.librarianEmailAlreadyExists(email);
  }

  @Override public boolean librarianPhoneNumberAlreadyExists(String phone)
  {
    return client.librarianPhoneNumberAlreadyExists(phone);
  }

  @Override public boolean librarianAlreadyExists(int employeeNo, String cpr,
      String email, String phone)
  {
    return client.librarianAlreadyExists(employeeNo, cpr, email, phone);
  }

  @Override public Borrower getBorrowerByCPR(String cpr)
      throws NoSuchElementException
  {
    try
    {
      return client.getBorrowerByCPR(cpr);
    }
    catch (NoSuchElementException e)
    {
      throw new NoSuchElementException(e.getMessage());
    }
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
