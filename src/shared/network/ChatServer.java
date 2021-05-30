package shared.network;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatServer
{
  void startServer() throws RemoteException, AlreadyBoundException;
  void  sendMessage(String msg, String userName) throws RemoteException;
  List<String> getConnectedUsers()throws RemoteException;
  void registerCallback(ClientCallback ccb)throws RemoteException;
}
