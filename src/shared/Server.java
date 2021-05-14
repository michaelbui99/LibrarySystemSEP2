package shared;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote
{
  UserServer getUserServer() throws RemoteException;
  MaterialServer getMaterialServer() throws RemoteException;
  LoanServer getLoanServer() throws RemoteException;
  ChatServer getChatServer() throws RemoteException;
}
