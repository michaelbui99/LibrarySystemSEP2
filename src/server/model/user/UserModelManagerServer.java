package server.model.user;

import client.model.loan.Address;
import client.model.user.borrower.Borrower;
import client.model.user.borrower.BorrowerList;
import client.model.user.librarian.Librarian;
import client.model.user.librarian.LibrarianList;
import database.user.borrower.BorrowerImpl;
import database.user.librarian.LibrarianImpl;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class UserModelManagerServer implements UserModelServer
{
  private PropertyChangeSupport support;
  private BorrowerList borrowerList;
  private LibrarianList librarianList;

  public UserModelManagerServer()
  {
    support = new PropertyChangeSupport(this);
    borrowerList = new BorrowerList();
    librarianList = new LibrarianList();
  }

  /**
   * Method add a Borrower to the system that
   * @return a Borrower object
   * */

  @Override public Borrower create(String cpr, String firstName,
      String lastName, String email, String tlfNumber, Address address,
      String password)
  {
    Borrower borrower = null;
    try
    {
      borrower = BorrowerImpl.getInstance().create(cpr, firstName, lastName, email, tlfNumber, address, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    borrowerList.addBorrower(borrower);
    support.firePropertyChange(EventTypes.BORROWERREGISTERED,null,borrower);
    return borrower;
  }

  /**
   * Boolean method to administrate borrower login that
   * @return true if the
   * @param cprNo and
   * @param password
   * matches an existed Borrower's cprNo and password
   * */

  @Override public boolean logInBorrower(String cprNo, String password)
  {
    boolean login = false;
    try
    {
     login = BorrowerImpl.getInstance().logInBorrower(cprNo, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    support.firePropertyChange(EventTypes.LOGINREQUESTED, null, login);
    return login;
  }

  /**
   * Method
   * @return a Borrower object of a logged borrower
   * */

  @Override public Borrower getLoginBorrower()
  {
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
    Librarian librarian = null;
    try
    {
      librarian = LibrarianImpl.getInstance().create(employee_no, firstName, lastName, cpr, tlfNumber, email, address, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    librarianList.addLibrarian(librarian);
    support.firePropertyChange(EventTypes.LIBRARIANREGISTERD, null, librarian);
    return librarian;
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
    boolean login = false;
    try
    {
      login = LibrarianImpl.getInstance().librarianLogin(employee_no, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    support.firePropertyChange(EventTypes.LOGINREQUESTED, null, login);
    return login;
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
