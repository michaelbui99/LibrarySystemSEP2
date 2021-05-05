package server.network;

import client.model.loan.Loan;
import client.model.material.Material;
import server.model.LibraryModel;
import shared.ClientCallback;
import shared.RMIServer;
import shared.util.EventTypes;

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

  public RMIServerImpl(LibraryModel model)
  {
    this.model = model;
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void startServer()
  {
    Registry registry = null;
    try
    {
      registry = LocateRegistry.createRegistry(1099);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    try
    {
      registry.bind(EventTypes.RMI_SERVER, this);
    }
    catch (RemoteException | AlreadyBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerLoan(Material material, String loanerCPR,
      String deadline)
  {
    model.registerLoan(material, loanerCPR, deadline);
  }

  @Override public void registerClientCallback(ClientCallback ccb)
  {
    model.addPropertyChangeListener(EventTypes.LOAN_REGISTERED,
        new PropertyChangeListener()
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

  @Override public void registerBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, int placeID, int authorId, String genre,
      String url)
  {
    model.registerBook(title, publisher, releaseDate, description, tags, targetAudience, language, isbn, pageCount, placeID, authorId, genre, url);
  }

  @Override public void createBookCopy(int materialID)
  {
    model.createBookCopy(materialID);
  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      String playDuration, int placeID,String genre, String url)
  {
    model.registerDVD(title, publisher, releaseDate, description, tags,
        targetAudience, language, subtitlesLanguage, playDuration, placeID, genre, url);
  }

  @Override public void createDVDCopy(int materialID)
  {
    model.createDVDCopy(materialID);
  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID, String genre, String url)
  {
    model.registerCD(title, publisher, releaseDate, description, tags, targetAudience, language, playDuration, placeID, genre, url);
  }

  @Override public void createCDCopy(int materialID)
  {
    model.createCDCopy(materialID);
  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, String licenseNr, int authorId, String genre,
      String url)
  {
    model.registerEBook(title, publisher, releaseDate, description, tags, targetAudience, language, isbn, pageCount, licenseNr, authorId, genre, url);
  }

  @Override public void createEBookCopy(int materialID)
  {
    model.createEBookCopy(materialID);
  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, String genre,
      int authorId, String url)
  {
    model.registerAudioBook(title, publisher, releaseDate, description, tags, targetAudience, language, playDuration, genre, authorId, url);
  }

  @Override public void createAudioBookCopy(int materialID)
  {
    model.createBookCopy(materialID);
  }

}
