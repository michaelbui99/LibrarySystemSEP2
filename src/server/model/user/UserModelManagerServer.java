package server.model.user;

import client.model.loan.Address;
import client.model.user.Borrower;
import client.model.user.BorrowerList;
import database.user.BorrowerImpl;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

public class UserModelManagerServer implements UserModelServer
{
  private PropertyChangeSupport support;
  private BorrowerList borrowerList;

  public UserModelManagerServer()
  {
    support = new PropertyChangeSupport(this);
    borrowerList = new BorrowerList();
  }

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

  @Override public boolean logInBorrower(String email, String password)
  {
    boolean login = false;
    try
    {
     login = BorrowerImpl.getInstance().logInBorrower(email, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    support.firePropertyChange(EventTypes.LOGINREQUESTED, null, login);
    return login;
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
