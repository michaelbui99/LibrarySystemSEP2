package server.network.user;

import client.core.ModelFactoryClient;
import server.model.user.UserModelServer;
import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;
import server.core.ModelFactoryServer;
import shared.servers.UserServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;

public class UserServerImpl implements UserServer
{
  private UserModelServer userModel;
  public UserServerImpl(UserModelServer userModel)
  {

    try
    {
      this.userModel = userModel;
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
    return userModel
        .create(cpr, firstName, lastName, email, tlfNumber, address, password);
  }

  @Override public boolean Login(String cprNo, String password)
  {
    return userModel
        .logInBorrower(cprNo, password);
  }

  @Override public Borrower getLoginBorrower()
  {
    return userModel.getLoginBorrower();
  }

  @Override public Librarian registerLibrarian(int employee_no,
      String firstName, String lastName, String cpr, String tlfNumber,
      String email, Address address, String password)
  {
    return userModel
        .registerLibrarian(employee_no, firstName, lastName, cpr, tlfNumber,
            email, address, password);
  }

  @Override public boolean librarianLogin(int employee_no, String password)
  {
    return userModel
        .librarianLogin(employee_no, password);
  }

  @Override public Librarian getLoginLibrarian()
  {
    return userModel.getLoginLibrarian();
  }

  @Override public void setBorrowerCpr(String borrowerCpr)
  {
    userModel.setBorrowerCpr(borrowerCpr);
  }

  @Override public boolean borrowerCprNumberAlreadyExists(String cpr)
  {
    return userModel
        .borrowerCprNumberAlreadyExists(cpr);
  }

  @Override public boolean borrowerEmailAlreadyExists(String email)
  {
    return userModel
        .borrowerEmailAlreadyExists(email);
  }

  @Override public boolean borrowerPhoneNumberAlreadyExists(String phone)
  {
    return userModel
        .borrowerPhoneNumberAlreadyExists(phone);
  }

  @Override public boolean borrowerAlreadyExists(String cpr, String email,
      String phone)
  {
    return userModel
        .borrowerAlreadyExists(cpr, email, phone);
  }

  @Override public boolean employeeNumberAlreadyExists(int employeeNo)
  {
    return userModel
        .employeeNumberAlreadyExists(employeeNo);
  }

  @Override public boolean librarianCprNumberAlreadyExists(String cpr)
  {
    return userModel
        .librarianCprNumberAlreadyExists(cpr);
  }

  @Override public boolean librarianEmailAlreadyExists(String email)
  {
    return userModel
        .librarianEmailAlreadyExists(email);
  }

  @Override public boolean librarianPhoneNumberAlreadyExists(String phone)
  {
    return userModel
        .librarianPhoneNumberAlreadyExists(phone);
  }

  @Override public boolean librarianAlreadyExists(int employeeNo, String cpr,
      String email, String phone)
  {
    return userModel
        .librarianAlreadyExists(employeeNo, cpr, email, phone);
  }

  @Override public Borrower getBorrowerByCPR(String cpr)
  {
    try
    {
      return userModel.getBorrowerByCPR(cpr);
    }
    catch (NoSuchElementException e)
    {
      throw new NoSuchElementException(e.getMessage());
    }
  }
}
