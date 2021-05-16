package shared;

import shared.servers.ChatServer;
import shared.servers.LoanServer;
import shared.servers.MaterialServer;
import shared.servers.UserServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote
{
  UserServer getUserServer() throws RemoteException;
  MaterialServer getMaterialServer() throws RemoteException;
  LoanServer getLoanServer() throws RemoteException;
  ChatServer getChatServer() throws RemoteException;
}
