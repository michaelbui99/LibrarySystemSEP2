package shared.servers;

import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReservationServer extends Remote
{
  void registerClientCallBack(ClientCallback client) throws RemoteException;
  void registerReservation(Material material, Borrower borrower) throws RemoteException;
}
