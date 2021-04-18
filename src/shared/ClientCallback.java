package shared;

import client.model.loan.Loan;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote
{
  void loanRegistered(Loan loan) throws RemoteException;
}
