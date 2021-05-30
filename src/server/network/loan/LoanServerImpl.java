package server.network.loan;

import server.model.loan.LoanModelServer;
import shared.loan.Loan;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import server.core.ModelFactoryServer;
import shared.servers.ClientCallback;
import shared.servers.LoanServer;
import shared.util.EventTypes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.NoSuchElementException;

public class LoanServerImpl implements LoanServer
{
  private LoanModelServer loanModel;

  public LoanServerImpl(LoanModelServer loanModel)
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      this.loanModel = loanModel;
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void registerLoan(Material material, Borrower borrower) throws IllegalStateException
  {
    try
    {
      loanModel.registerLoan(material, borrower);
    }
    catch (IllegalStateException e)
    {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    return loanModel.getAllLoansByCPR(cpr);
  }

  @Override public void extendLoan(Loan loan)
  {
    try
    {
      loanModel.extendLoan(loan);
    }
    catch (IllegalStateException e)
    {
    }
  }

  @Override public void endLoan(Loan loan)
  {
   loanModel.endLoan(loan);
  }

  public void registerClientCallBack(ClientCallback client)
  {

    PropertyChangeListener listener = new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        try
        {
          client.loanUpdate(evt);
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
          loanModel.removePropertyChangeListener(this);
        }
      }
    };
    loanModel.addPropertyChangeListener(EventTypes.LOANREGISTERED, listener);
    loanModel.addPropertyChangeListener(EventTypes.LOANENDED, listener);
    loanModel.addPropertyChangeListener(EventTypes.LOANEXTENDED, listener);
    loanModel.addPropertyChangeListener(EventTypes.LOANEXTENDERROR, listener);

  }

}
