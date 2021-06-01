package shared.network;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Client callback interface
 *
 * @author Michael
 * @version 1.0
 */
public interface ClientCallback extends Remote
{
  /**
   * Method used to notify the client when a Loan event has occurred on the server.
   *
   * @param evt the PropertyChangeEvent that is fired, when an Loan event has occured.
   * @throws RemoteException if the connection between the client and server can not be established
   */
  void loanUpdate(PropertyChangeEvent evt) throws RemoteException;

  /**
   * Method used to notify the client when a Reservation event has occurred on the server.
   *
   * @param evt the PropertyChangeEvent that is fired, when an Reservation event has occurred.
   * @throws RemoteException if the connection between the client and server can not be established
   */
  void reservationUpdate(PropertyChangeEvent evt) throws RemoteException;
}
