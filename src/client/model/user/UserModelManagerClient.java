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
    support = new PropertyChangeSupport(this);
  }

 /**
  * Method add a Borrower to the system that
  * @return a Borrower object
  * */
  @Override public Borrower registerBorrower(String cpr_no, String f_name,
      String l_name, String email, String tel_no, Address address_id,
      String password)
  {
    //TODO call the method registerBorrower(args) from the Client and then fire an event//
    return null;
  }

  /**
   * Boolean method to administrate borrower login that
   * @return true if the
   * @param cprNo and
   * @param password
   * matches an existed Borrower's cprNo and password
   * */
  @Override public boolean borrowerLogin(String cprNo, String password)
  {
    //TODO call the method Login from the Client and then fire an event//
    return false;
  }

  /**
   * Method
   * @return a Borrower object of a logged borrower
   * */
  @Override public Borrower getLoginUser()
  {
    //TODO call the method getLogInUser from the Client and then fire an event//
    return null;
  }

  /**
   * Method add a Librarian to the system that
   * @return a Librarian object
   * */
  @Override public Librarian registerLibrarian(int employee_no,
      String firstName, String lastName, String cpr, String tlfNumber,
      String email, Address address, String password)
  {
    return null;
  }

  /**
   * Boolean method to administrate librarian login that
   * @return true if the
   * @param employee_no and
   * @param password
   * matches an existed Librarian's employee_no and password
   * */

  @Override public boolean librarianLogin(int employee_no, String password)
  {
    return false;
  }

  /**
   * Method
   * @return a Borrower object of a logged borrower
   * */

  @Override public Librarian getLoginLibrarian()
  {
    return null;
  }

  /**
   * PropertyChangeSubject implementation
   * */

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
