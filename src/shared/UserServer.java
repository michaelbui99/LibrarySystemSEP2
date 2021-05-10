package shared;

import client.model.loan.Address;
import client.model.user.borrower.Borrower;
import client.model.user.librarian.Librarian;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserServer extends Remote
{
  Borrower registerBorrower(String cpr, String firstName, String lastName, String email,
      String tlfNumber, Address address, String password) throws
      RemoteException;
  boolean Login(String cprNo, String password) throws RemoteException;
  Borrower getLoginBorrower() throws RemoteException;

  Librarian registerLibrarian(int employee_no, String firstName, String lastName, String cpr,
      String tlfNumber, String email, Address address, String password) throws RemoteException;
  boolean librarianLogin(int employee_no, String password) throws RemoteException;
  Librarian getLoginLibrarian() throws RemoteException;
}
