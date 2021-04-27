package server.network;

import client.model.loan.Loan;
import client.model.material.Material;
import server.model.LibraryModel;
import shared.ClientCallback;
import shared.RMIServer;
import shared.util.EventTypes;
import shared.util.Constants;

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
    registry.bind(Constants.RMI_SERVER, this);
  }

  @Override public void registerLoan(Material material, String loanerCPR,
      String deadline) throws RemoteException
  {
    model.registerLoan(material, loanerCPR, deadline);
  }

  @Override public void registerClientCallback(ClientCallback ccb)
      throws RemoteException
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

  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      int placeID)
  {
    model.registerBook(title, publisher, releaseDate, description, tags,
        targetAudience, language, isbn, pageCount, placeID);
  }

  @Override public void createBookCopy(int materialID)
  {
    model.createBookCopy(materialID);
  }

  @Override public void registerDVB(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      double playDuration, int placeID)
  {
    model.registerDVD(title, publisher, releaseDate, description, tags,
        targetAudience, language, subtitlesLanguage, playDuration, placeID);
  }

  @Override public void createDVDCopy(int materialID)
  {
    model.createDVDCopy(materialID);
  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID)
  {
    model.registerCD(title, publisher, releaseDate, description, tags, targetAudience, language, playDuration, placeID);
  }

  @Override public void createCDCopy(int materialID)
  {
    model.createCDCopy(materialID);
  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      String licenseNr, String author, String genre)
  {
    model.registerEBook(title, publisher, releaseDate, description, tags, targetAudience, language, isbn, pageCount, licenseNr, author, genre);
  }

  @Override public void createEBookCopy(int materialID)
  {
    model.createEBookCopy(materialID);
  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration)
  {
    model.registerAudioBook(title, publisher, releaseDate, description, tags, targetAudience, language, playDuration);
  }

  @Override public void createAudioBookCopy(int materialID)
  {
    model.createBookCopy(materialID);
  }

}
