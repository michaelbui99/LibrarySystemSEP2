package server.network.user;

import client.model.loan.Address;
import client.model.user.Borrower;
import client.model.user.UserModelClient;
import server.model.user.UserModelServer;
import shared.UserServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class UserServerImpl implements UserServer
{
  private UserModelServer model;

  public UserServerImpl(UserModelServer model)
  {
    this.model = model;
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
  }

  @Override public Borrower registerBorrower(String cpr, String firstName,
      String lastName, String email, String tlfNumber, Address address,
      String password)
  {
    try
    {
      return model.create(cpr, firstName, lastName, email, tlfNumber, address, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public boolean Login(String email, String password)
  {
    try
    {
      return model.logInBorrower(email, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return false;
  }
}
