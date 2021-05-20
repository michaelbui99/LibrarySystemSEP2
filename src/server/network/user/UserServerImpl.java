package server.network.user;

import client.core.ModelFactoryClient;
import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;
import server.core.ModelFactoryServer;
import shared.servers.UserServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
    return ModelFactoryServer.getInstance().getUserModel()
        .logInBorrower(cprNo, password);
  }

  @Override public Borrower getLoginBorrower()
  {
    return ModelFactoryServer.getInstance().getUserModel().getLoginBorrower();
  }

  @Override public Librarian registerLibrarian(int employee_no,
      String firstName, String lastName, String cpr, String tlfNumber,
      String email, Address address, String password)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .registerLibrarian(employee_no, firstName, lastName, cpr, tlfNumber,
            email, address, password);
  }

  @Override public boolean librarianLogin(int employee_no, String password)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .librarianLogin(employee_no, password);
  }

  @Override public Librarian getLoginLibrarian()
  {
    return ModelFactoryServer.getInstance().getUserModel().getLoginLibrarian();
  }

  @Override public void setBorrowerCpr(String borrowerCpr)
  {
    ModelFactoryServer.getInstance().getUserModel().setBorrowerCpr(borrowerCpr);
  }

  @Override public boolean borrowerCprNumberAlreadyExists(String cpr)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .borrowerCprNumberAlreadyExists(cpr);
  }

  @Override public boolean borrowerEmailAlreadyExists(String email)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .borrowerEmailAlreadyExists(email);
  }

  @Override public boolean borrowerPhoneNumberAlreadyExists(String phone)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .borrowerPhoneNumberAlreadyExists(phone);
  }

  @Override public boolean borrowerAlreadyExists(String cpr, String email,
      String phone)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .borrowerAlreadyExists(cpr, email, phone);
  }

  @Override public boolean employeeNumberAlreadyExists(int employeeNo)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .employeeNumberAlreadyExists(employeeNo);
  }

  @Override public boolean librarianCprNumberAlreadyExists(String cpr)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .librarianCprNumberAlreadyExists(cpr);
  }

  @Override public boolean librarianEmailAlreadyExists(String email)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .librarianEmailAlreadyExists(email);
  }

  @Override public boolean librarianPhoneNumberAlreadyExists(String phone)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .librarianPhoneNumberAlreadyExists(phone);
  }

  @Override public boolean librarianAlreadyExists(int employeeNo, String cpr,
      String email, String phone)
  {
    return ModelFactoryServer.getInstance().getUserModel()
        .librarianAlreadyExists(employeeNo, cpr, email, phone);
  }
}
