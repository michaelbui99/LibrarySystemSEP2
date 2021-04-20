package server.network;


import client.model.loan.Loan;
import client.model.material.Material;
import client.model.material.MaterialList;
import server.model.LibraryModel;
import shared.ClientCallback;
import shared.RMIServer;
import shared.util.EventTypes;
import shared.util.constants;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerImpl implements RMIServer
{
  private LibraryModel model;

  public RMIServerImpl(LibraryModel model) throws RemoteException
  {
    this.model = model;
    UnicastRemoteObject.exportObject(this, 0);
  }

  @Override public void startServer()
      throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind(constants.RMI_SERVER, this);
  }

  @Override public void registerLoan(Material material, String loanerCPR,
      String deadline) throws RemoteException
  {
    model.registerLoan(material, loanerCPR, deadline);
  }

  @Override public void registerClientCallback(ClientCallback ccb)
      throws RemoteException
  {
    model.addPropertyChangeListener(EventTypes.LOAN_REGISTERED, new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        try
        {
          ccb.loanRegistered((Loan) evt.getNewValue());
        }
        catch (RemoteException e)
        {
          model.removePropertyChangeListener(this);
        }
      }
    });
  }

  @Override public void registerBook(int materialID, int copyNumber,
      String title, String publisher, String releaseDate, String description,
      String tags, String targetAudience, String language, String isbn,
      int pageCount)
  {
    model.registerBook(materialID, copyNumber, title, publisher, releaseDate, description, tags, targetAudience, language, isbn, pageCount);
  }


}
