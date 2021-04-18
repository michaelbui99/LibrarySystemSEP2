package client.network;

import client.model.material.Material;
import shared.PropertyChangeSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote, PropertyChangeSubject
{
  void startClient() throws RemoteException;
  void registerLoan(Material material, String loanerCPR, String deadline) throws RemoteException;
}
