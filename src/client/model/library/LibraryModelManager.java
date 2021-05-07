package client.model.library;

import client.model.loan.LoanList;
import client.model.material.Material;
import client.model.material.MaterialList;
import client.model.material.strategy.SearchStrategyManager;
import client.network.RMIClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class LibraryModelManager implements LibraryModelClient
{
  LoanList loanList;

  public LibraryModelManager(){

  }
  private RMIClient RMIClient;
  private PropertyChangeSupport support;
  private SearchStrategyManager searchStrategyNavigator;
  public LibraryModelManager(RMIClient RMIClient)
  {
    this.RMIClient = RMIClient;
    support = new PropertyChangeSupport(this);

    try
    {
      RMIClient.startClient();
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
      RMIClient.registerLoan(material, loanerCPR, deadline);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerBook(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount)
  {

  }

  @Override public MaterialList searchMaterial(String title, String language,
      String keywords, String genre, String audience, String type)
  {
    return null;
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
