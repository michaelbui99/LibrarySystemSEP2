package shared;

import shared.network.*;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Main server interface
 *
 * @author Michael
 * @version 1.0
 */
public interface Server extends Remote
{
  /**
   * Starts the server
   *
   * @throws RemoteException       if the connection can not be established
   * @throws AlreadyBoundException f an attempt
   *                               is made to bind an object in the registry to a name that already
   */
  void start() throws RemoteException, AlreadyBoundException;

  /**
   * Access the user server
   */
  UserServer getUserServer() throws RemoteException;

  /**
   * Access the material server
   */
  MaterialServer getMaterialServer() throws RemoteException;

  /**
   * Access the loan server
   */
  LoanServer getLoanServer() throws RemoteException;

  /**
   * Access the reservation server
   */
  ReservationServer getReservationServer() throws RemoteException;
}
