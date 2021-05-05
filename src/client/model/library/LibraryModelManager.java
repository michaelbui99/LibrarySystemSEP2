package client.model.library;

import client.model.material.Material;
import client.network.RMIClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class LibraryModelManager implements LibraryModelClient
{
  LoanList loanList;

  public LibraryModelManager()
  private RMIClient RMIClient;
  private PropertyChangeSupport support;
  private MaterialSearchStrategyNavigator searchStrategyNavigator;
  public LibraryModelManager(RMIClient RMIClient)
  {
    this.RMIClient = RMIClient;
    support = new PropertyChangeSupport(this);
    searchStrategyNavigator = new MaterialSearchStrategyNavigator("all");
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

  @Override public void searchMaterial(String arg)
  {
    MaterialList ml = new MaterialList();
//    if (type.equals("all") ){
//      MaterialDAOImpl mid =
//    }
//    else {
//      switch (type){
//        case "audiobook" :
//          AudioBookStrategy abs =
//      }
//    }
    return ml;
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
