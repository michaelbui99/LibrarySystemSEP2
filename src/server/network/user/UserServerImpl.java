package server.network.user;

import client.model.loan.Address;
import client.model.user.borrower.Borrower;
import client.model.user.librarian.Librarian;
import server.core.ModelFactoryServer;
import server.model.user.UserModelServer;
import shared.UserServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class UserServerImpl implements UserServer
{


  public UserServerImpl()
  {

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
    return ModelFactoryServer.getInstance().getUserModel()
        .create(cpr, firstName, lastName, email, tlfNumber, address, password);
  }

  @Override public boolean Login(String cprNo, String password)
  {
    return ModelFactoryServer.getInstance().getUserModel().logInBorrower(cprNo, password);
  }

  @Override public Borrower getLoginBorrower()
  {
    return ModelFactoryServer.getInstance().getUserModel().getLoginBorrower();
  }

  @Override public Librarian registerLibrarian(int employee_no,
      String firstName, String lastName, String cpr, String tlfNumber,
      String email, Address address, String password)
  {
    return ModelFactoryServer.getInstance().getUserModel().registerLibrarian(employee_no, firstName, lastName, cpr, tlfNumber, email, address, password);
  }

  @Override public boolean librarianLogin(int employee_no, String password)
  {
    return ModelFactoryServer.getInstance().getUserModel().librarianLogin(employee_no, password);
  }

  @Override public Librarian getLoginLibrarian()
  {
    return ModelFactoryServer.getInstance().getUserModel().getLoginLibrarian();
  }
}
