package server.network.loan;

import client.model.loan.Loan;
import client.model.material.Material;
import database.loan.LoanDAOImpl;
import shared.LoanServer;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class LoanServerImpl implements LoanServer
{
  @Override public void registerLoan(Material material, String loanerCPR,
      String deadline) throws RemoteException
  {

  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    try
    {
      return LoanDAOImpl.getInstance().getAllLoansByCPR(cpr);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }
}
