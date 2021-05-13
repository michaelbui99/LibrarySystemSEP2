package server.network;

import shared.Server;
import shared.util.Constants;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    Server server = new ServerImpl();
    Registry registry = LocateRegistry.createRegistry(1090);
    registry.bind(Constants.RMISERVER, server);
    System.out.println("Server started...");
  }
}
