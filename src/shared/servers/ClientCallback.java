package shared.servers;

import shared.loan.Loan;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote
{
  /**
  * Method used to notify the client, when the server have created the new Loan in the system.
  * @param loan the created Loan object. To be used when displaying Loan details in the GUI layer.
  * */
  void loanRegistered(Loan loan) throws RemoteException;
  void loanEnded(Loan loan) throws RemoteException;
  void loanUpdate(PropertyChangeEvent evt) throws RemoteException;
}
