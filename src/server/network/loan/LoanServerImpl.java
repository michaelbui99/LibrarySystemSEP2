package server.network.loan;

import client.model.loan.Loan;
import client.model.material.Material;
import client.model.user.Borrower;
import database.loan.LoanDAOImpl;
import server.model.loan.LoanModelServer;
import shared.LoanServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

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

  @Override public Loan registerLoan(Material material, Borrower borrower,
      String deadline) throws IllegalStateException
  {
    return model.registerLoan(material,borrower,deadline);
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    return model.getAllLoansByCPR(cpr);
  }
}
