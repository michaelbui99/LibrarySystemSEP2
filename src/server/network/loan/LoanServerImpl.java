package server.network.loan;

import client.model.loan.Loan;
import client.model.material.Material;
import client.model.user.Borrower;
import server.model.loan.LoanModelServer;
import shared.ClientCallback;
import shared.LoanServer;
import shared.util.EventTypes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class LoanServerImpl implements LoanServer
{
  private LoanModelServer model;

  public LoanServerImpl(LoanModelServer model)
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    this.model = model;
  }

  @Override public void registerLoan(Material material, Borrower borrower,
      String deadline) throws IllegalStateException
  {
    model.registerLoan(material, borrower, deadline);
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    return model.getAllLoansByCPR(cpr);
  }

  public void registerClientCallBack(ClientCallback client)
  {
    PropertyChangeListener listener = new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        try
        {
          //TODO: let client/rmiclient implement ClientCallBack and implement the loanRegistered Method. The method should forward/fire the same event.
          client.loanRegistered((Loan) evt.getNewValue());
        }
        catch (RemoteException e)
        {
          //Removes listener if connection failed
          e.printStackTrace();
          model.removePropertyChangeListener(EventTypes.LOANREGISTERED, this);
        }
      }
    };
    model.addPropertyChangeListener(EventTypes.LOANREGISTERED, listener);
  }
}
