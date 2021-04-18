package shared;

import client.model.material.Material;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote
{
  void startServer() throws RemoteException, AlreadyBoundException;
  void registerLoan(Material material, String loanerCPR, String deadline) throws RemoteException;
  void registerClientCallback(ClientCallback ccb) throws RemoteException;
}
