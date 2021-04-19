package client.model.library;

import client.model.loan.Loaner;
import client.model.material.Material;
import client.network.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class LibraryModelManager implements LibraryModel
{
  private Client client;
  private PropertyChangeSupport support;

  public LibraryModelManager(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);

    try
    {
      client.startClient();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }


  @Override public void registerLoan(Material material, String loanerCPR,
      String deadline)
  {
    try
    {
      client.registerLoan(material, loanerCPR, deadline);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerBook(Loaner loaner, Material material)
  {

  }

  @Override public void searchMaterial(String arg)
  {

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
}
