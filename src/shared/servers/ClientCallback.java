package shared.servers;

import shared.loan.Loan;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote
{
  /**
  * Method used to notify the client when a Loan event has occured on the server.
  * @param evt the PropertyChangeEvent that is fired, when an Loan event has occured.
  * */
  void loanUpdate(PropertyChangeEvent evt) throws RemoteException;
  void reservationUpdate(PropertyChangeEvent evt) throws RemoteException;
}
