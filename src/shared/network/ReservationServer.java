package shared.network;

import shared.reservation.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ReservationServer extends Remote
{
  /**
   * Registers a new Reservation in the system for a given Material and Borrower.
   * The Reservation is made for a non-specific copy of the Material.
   * A reservation can only be made, if the Material has none available copies.
   *
   * @param material material is The Material which the Borrower intends to make a Reservation for.
   * @param borrower borrower is the owner of the Reservation.
   * @throws IllegalStateException if the Material has more than 1 available copies
   */
  void registerReservation(Material material, Borrower borrower)
      throws RemoteException;
  void endReservation(Reservation reservation) throws RemoteException;
  /**
   * Registers a ClientCallback to the server to be used to notify the Client when a event regarding Reservation has occurred.
   * works the same way as the method in LoanServer
   * @param client the Client to be registered as listener/ClientCallback
   * @see LoanServer
   */
  public void registerClientCallBack(ClientCallback client) throws RemoteException;
  List<Reservation> getAllReservationsByCPR(String cpr) throws RemoteException;
}
