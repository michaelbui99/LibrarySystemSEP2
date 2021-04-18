package server.network;


import client.model.material.Material;
import server.model.LibraryModel;
import shared.RMIServer;
import shared.util.constants;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerImpl implements RMIServer
{
  private LibraryModel model;

  public RMIServerImpl(LibraryModel model) throws RemoteException
  {
    this.model = model;
    UnicastRemoteObject.exportObject(this, 0);
  }

  @Override public void startServer()
      throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind(constants.RMI_SERVER, this);
  }

  @Override public void registerLoan(Material material, String loanerCPR,
      String deadline) throws RemoteException
  {

  }
}
