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

  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      int placeID, int authorId, String genre, String url)
  {
    try
    {
      server.registerBook(title, publisher, releaseDate, description, tags, targetAudience, language, isbn, pageCount, placeID, authorId, genre, url);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void createBookCopy(int materialID)
  {
    try
    {
      server.createBookCopy(materialID);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

 public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      String playDuration, int placeID, String genre, String url)
  {
    try
    {
      server.registerDVD(title, publisher, releaseDate, description, tags, targetAudience, language, subtitlesLanguage, playDuration, placeID, genre, url);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void createDVDCopy(int materialID)
  {
    try
    {
      server.createDVDCopy(materialID);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID,
      String genre, String url)
  {
    try
    {
      server.registerCD(title, publisher, releaseDate, description, tags, targetAudience, language, playDuration, placeID, genre, url);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void createCDCopy(int materialID)
  {
    try
    {
      server.createCDCopy(materialID);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      String licenseNr, int authorId, String genre, String url)
  {
    try
    {
      server.registerEBook(title, publisher, releaseDate, description, tags, targetAudience, language, isbn, pageCount, licenseNr, authorId, genre, url);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void createEBookCopy(int materialID)
  {
    try
    {
      server.createEBookCopy(materialID);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, String genre,
      int authorId, String url)
  {
    try
    {
      server.registerAudioBook(title, publisher, releaseDate, description, tags, targetAudience, language, playDuration, genre, authorId,url );
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void createAudioBookCopy(int materialID)
  {
    try
    {
      server.createAudioBookCopy(materialID);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
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
