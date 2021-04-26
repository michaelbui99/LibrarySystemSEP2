package client.network;

import client.model.loan.Loan;
import client.model.material.Material;
import shared.ClientCallback;
import shared.RMIServer;
import shared.util.Constants;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient implements Client, ClientCallback
{
  private PropertyChangeSupport support;
  private RMIServer server;

  public RMIClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    Registry registry = null;

    try
    {
      registry = LocateRegistry.getRegistry(1099);
      server = (RMIServer) registry.lookup(Constants.RMI_SERVER);
      server.registerClientCallback(this);
    }
    catch (RemoteException | NotBoundException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }


  @Override public void registerLoan(Material material, String loanerCPR,
      String deadline)
  {
    try
    {
      server.registerLoan(material,loanerCPR,deadline);
    }
    catch (RemoteException e)
    {
     throw new RuntimeException("Server connection failed");
    }
  }

  @Override public void registerBook(int materialID, int copyNumber,
      String title, String publisher, String releaseDate, String description,
      String tags, String targetAudience, String language, String isbn,
      int pageCount)
  {
    try
    {
      server.registerBook(materialID, copyNumber, title, publisher, releaseDate, description, tags, targetAudience, language, isbn, pageCount);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server connection failed");
    }
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void loanRegistered(Loan loan) throws RemoteException
  {
    support.firePropertyChange("LoanRegistered", null, loan);
  }
}
