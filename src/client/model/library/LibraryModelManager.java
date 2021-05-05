package client.model.library;

import client.model.material.Material;
import client.model.material.MaterialList;
import client.model.material.MaterialSearchStrategyNavigator;
import client.model.material.audio.AudioBookStrategy;
import client.network.Client;
import database.MaterialDAOImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class LibraryModelManager implements LibraryModel
{
  private Client client;
  private PropertyChangeSupport support;
  private MaterialSearchStrategyNavigator searchStrategyNavigator;
  public LibraryModelManager(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
    searchStrategyNavigator = new MaterialSearchStrategyNavigator("all");
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

  @Override public void registerBook(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount)
  {

  }



  @Override public MaterialList searchMaterial(String title, String language, String keywords,
      String genre, String audience, String type)
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
