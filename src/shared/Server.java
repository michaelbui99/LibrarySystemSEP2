package shared;

import shared.network.*;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

//Michael
public interface Server extends Remote
{
  void start() throws RemoteException, AlreadyBoundException;
  UserServer getUserServer() throws RemoteException;
  MaterialServer getMaterialServer() throws RemoteException;
  LoanServer getLoanServer() throws RemoteException;
  ReservationServer getReservationServer() throws RemoteException;
}
