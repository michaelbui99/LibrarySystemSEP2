package server.network;

import shared.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    Server server = new ServerImpl();
//    Registry registry = LocateRegistry.createRegistry(1099);
//    registry.bind(Constants.RMISERVER, server);
    server.start();
  }
}
