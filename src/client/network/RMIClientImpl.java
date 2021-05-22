package client.network;

import client.model.material.strategy.SearchStrategy;
import shared.person.Address;
import shared.loan.Loan;
import shared.materials.Material;
import shared.materials.Place;
import shared.person.MaterialCreator;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;
import shared.servers.ClientCallback;
import shared.Server;
import shared.util.Constants;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIClientImpl implements RMIClient, ClientCallback, Client
{

  private PropertyChangeSupport support;
  private Server server;
  private SearchStrategy searchStrategy;

  public RMIClientImpl()
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
      registry = LocateRegistry.getRegistry(1090);
      server = (Server) registry.lookup(Constants.RMISERVER);
      server.getLoanServer().registerClientCallBack(this);
    }
    catch (RemoteException | NotBoundException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void registerLoan(Material material, Borrower borrower)
      throws IllegalStateException
  {
    try
    {
      server.getLoanServer().registerLoan(material, borrower);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void registerReservation(Material material,
      Borrower borrower) throws IllegalStateException
  {
    try
    {
      server.getReservationServer().registerReservation(material, borrower);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    try
    {
      return server.getLoanServer().getAllLoansByCPR(cpr);
    }
    catch (RemoteException e)
    {
      //      throw new RuntimeException("Server Connection failed.");
      e.printStackTrace();
    }
    return null;
  }

  @Override public void deliverMaterial(int loanID)
  {
    //    server.getLoanServer().
  }

  @Override public void extendLoan()
  {

  }

  @Override public void registerBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place place, MaterialCreator author, String genre, String url,
      String keywords)
  {
    try
    {
      server.getMaterialServer()
          .registerBook(title, publisher, releaseDate, description, tags,
              targetAudience, language, isbn, pageCount, place, author, genre,
              url, keywords);
    }
    catch (RemoteException e)
    {
      //throw new RuntimeException("Server Connection failed.");
      e.printStackTrace();
    }
  }

  @Override public void createBookCopy(int materialID)
  {
    try
    {
      server.getMaterialServer().createBookCopy(materialID);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public boolean bookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String isbn, int pageCount, MaterialCreator author,
      String genre)
  {
    try
    {
      return server.getMaterialServer()
          .bookAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, isbn, pageCount, author, genre);
    }
    catch (RemoteException remoteException)
    {
      throw new RuntimeException("server connection failed");
    }
  }

  public void registerDVD(String title, String publisher, String releaseDate,
      String description, String tags, String targetAudience, String language,
      String subtitlesLanguage, int playDuration, Place placeID, String genre,
      String url)
  {
    try
    {
      server.getMaterialServer()
          .registerDVD(title, publisher, releaseDate, description, tags,
              targetAudience, language, subtitlesLanguage, playDuration,
              placeID, genre, url);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void createDVDCopy(int materialID)
  {
    try
    {
      server.getMaterialServer().createDVDCopy(materialID);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public boolean dvdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String playDuration, String genre)
  {
    try
    {
      return server.getMaterialServer()
          .dvdAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, playDuration, genre);
    }
    catch (RemoteException remoteException)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, Place place,
      String genre, String url)
  {
    try
    {
      server.getMaterialServer()
          .registerCD(title, publisher, releaseDate, description, tags,
              targetAudience, language, playDuration, place, genre, url);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void createCDCopy(int materialID)
  {
    try
    {
      server.getMaterialServer().createCDCopy(materialID);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public boolean cdAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int playDuration, String genre)
  {
    try
    {
      return server.getMaterialServer()
          .cdAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, playDuration, genre);
    }
    catch (RemoteException remoteException)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount, int licenseNr,
      MaterialCreator author, String genre, String url)
  {
    try
    {
      server.getMaterialServer()
          .registerEBook(title, publisher, releaseDate, description, tags,
              targetAudience, language, pageCount, licenseNr, author, genre,
              url);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void createEBookCopy(int materialID)
  {
    try
    {
      server.getMaterialServer().createEBookCopy(materialID);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public boolean eBookAlreadyExists(String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, int pageCount, String licenseNr, String genre,
      MaterialCreator author)
  {
    try
    {
      return server.getMaterialServer()
          .eBookAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, pageCount, licenseNr, genre, author);
    }
    catch (RemoteException remoteException)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String genre,
      MaterialCreator author, String url)
  {
    try
    {
      server.getMaterialServer()
          .registerAudioBook(title, publisher, releaseDate, description, tags,
              targetAudience, language, playDuration, genre, author, url);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void createAudioBookCopy(int materialID)
  {
    try
    {
      server.getMaterialServer().createAudioBookCopy(materialID);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public boolean audioBookAlreadyExists(String title,
      String publisher, String releaseDate, String description,
      String targetAudience, String language, int playDuration,
      MaterialCreator author, String genre)
  {
    try
    {
      return server.getMaterialServer()
          .audioBookAlreadyExists(title, publisher, releaseDate, description,
              targetAudience, language, playDuration, author, genre);
    }
    catch (RemoteException remoteException)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience,
      SearchStrategy searchStrategy)
  {
    try
    {
      return server.getMaterialServer()
          .findMaterial(title, language, keywords, genre, targetAudience,
              searchStrategy);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Material getSelectMaterial()
  {
    try
    {
      return server.getMaterialServer().getSelectedMaterial();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void setSelectMaterial(Material material)
  {
    try
    {
      server.getMaterialServer().setSelectedMaterial(material);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public Borrower registerBorrower(String cpr_no, String f_name,
      String l_name, String email, String tel_no, Address address,
      String password)
  {
    try
    {
      return server.getUserServer()
          .registerBorrower(cpr_no, f_name, l_name, email, tel_no, address,
              password);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public boolean borrowerLogin(String cprNo, String password)
  {
    try
    {
      return server.getUserServer().Login(cprNo, password);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public Borrower getLoginUser()
  {
    try
    {
      return server.getUserServer().getLoginBorrower();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public Librarian registerLibrarian(int employee_no,
      String firstName, String lastName, String cpr, String tlfNumber,
      String email, Address address, String password)
  {
    try
    {
      return server.getUserServer()
          .registerLibrarian(employee_no, firstName, lastName, cpr, tlfNumber,
              email, address, password);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public boolean librarianLogin(int employee_no, String password)
  {
    try
    {
      return server.getUserServer().librarianLogin(employee_no, password);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public Librarian getLoginLibrarian()
  {
    try
    {
      return server.getUserServer().getLoginLibrarian();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public void endLoan(Loan loan)
  {
    try
    {
      server.getLoanServer().endLoan(loan);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
  }

  @Override public int numberOfAvailableCopies()
  {
    try
    {
      return server.getMaterialServer().numberOfAvailableCopies();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return -1;
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

  @Override public void setBorrowerCpr(String borrowerCpr)
  {
    try
    {
      server.getUserServer().setBorrowerCpr(borrowerCpr);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public boolean borrowerCprNumberAlreadyExists(String cpr)
  {
    try
    {
      server.getUserServer().borrowerCprNumberAlreadyExists(cpr);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
    return false;
  }

  @Override public boolean borrowerEmailAlreadyExists(String email)
  {
    try
    {
      return server.getUserServer().borrowerEmailAlreadyExists(email);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
    return false;
  }

  @Override public boolean borrowerPhoneNumberAlreadyExists(String phone)
  {
    try
    {
      return server.getUserServer().borrowerPhoneNumberAlreadyExists(phone);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
    return false;
  }

  @Override public boolean borrowerAlreadyExists(String cpr, String email,
      String phone)
  {
    try
    {
      return server.getUserServer().borrowerAlreadyExists(cpr, email, phone);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
    return false;
  }

  @Override public boolean employeeNumberAlreadyExists(int employeeNo)
  {
    try
    {
      return server.getUserServer().employeeNumberAlreadyExists(employeeNo);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
    return false;
  }

  @Override public boolean librarianCprNumberAlreadyExists(String cpr)
  {
    try
    {
      return server.getUserServer().librarianCprNumberAlreadyExists(cpr);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
    return false;
  }

  @Override public boolean librarianEmailAlreadyExists(String email)
  {
    try
    {
      return server.getUserServer().librarianEmailAlreadyExists(email);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
    return false;
  }

  @Override public boolean librarianPhoneNumberAlreadyExists(String phone)
  {
    try
    {
      return server.getUserServer().librarianPhoneNumberAlreadyExists(phone);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
    return false;
  }

  @Override public boolean librarianAlreadyExists(int employeeNo, String cpr,
      String email, String phone)
  {
    try
    {
      return server.getUserServer().librarianAlreadyExists(employeeNo, cpr, email, phone);
    }
    catch (RemoteException remoteException)
    {
      remoteException.printStackTrace();
    }
    return false;
  }

  @Override public void loanRegistered(Loan loan)
  {
    support.firePropertyChange(EventTypes.LOANREGISTERED, null, loan);
  }

  @Override public void loanEnded(Loan loan)
  {
    support.firePropertyChange(EventTypes.LOANENDED, null, loan);
  }

}
