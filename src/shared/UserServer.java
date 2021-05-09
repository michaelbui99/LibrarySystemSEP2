package shared;

import client.model.loan.Address;
import client.model.user.Borrower;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserServer extends Remote
{
  Borrower registerBorrower(String cpr, String firstName, String lastName, String email,
      String tlfNumber, Address address, String password) throws
      RemoteException;
  boolean Login(String cprNo, String password) throws RemoteException;
}
