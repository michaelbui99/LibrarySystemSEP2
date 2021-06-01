package shared.network;

import shared.reservation.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Reservation server
 *
 * @author Michael
 * @author Lilian
 * @version 1.0
 */
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
   * @throws RemoteException       if the method is called with no connection between the RMI-server and the RMI-client
   */
  void registerReservation(Material material, Borrower borrower)
      throws RemoteException;

  /**
   * Ends a reservation based on the given argument
   *
   * @param reservation The reservation to be ended
   * @throws RemoteException if the method is called with no connection between the RMI-server and the RMI-client
   */
  void endReservation(Reservation reservation) throws RemoteException;
  /**
   * Registers a ClientCallback to the server to be used to notify the Client when a event regarding Reservation has occurred.
   * works the same way as the method in LoanServer
   *
   * @param client the Client to be registered as listener/ClientCallback
   * @throws RemoteException if the method is called with no connection between the RMI-server and the RMI-client
   * @see LoanServer
   */
  void registerClientCallBack(ClientCallback client) throws RemoteException;

  /**
   * Gets all the active reservation for a borrower based on the given argument
   *
   * @param cpr The borrower's cpr-number
   * @throws RemoteException if the method is called with no connection between the RMI-server and the RMI-client
   */
  List<Reservation> getAllReservationsByCPR(String cpr) throws RemoteException;
}
