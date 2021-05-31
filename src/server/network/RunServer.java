package server.network;

import shared.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

//Michael
public class RunServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    Server server = new ServerImpl();
    server.start();
  }
}
