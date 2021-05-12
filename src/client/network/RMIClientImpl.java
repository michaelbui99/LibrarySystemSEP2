package client.network;

import client.model.loan.Address;
import client.model.loan.Loan;
import client.model.material.Material;
import client.model.user.borrower.Borrower;
import client.model.user.librarian.Librarian;
import shared.ClientCallback;
import shared.Server;
import shared.util.Constants;

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
      registry = LocateRegistry.getRegistry(1099);
      server = (Server) registry.lookup(Constants.RMISERVER);
//      server.registerClientCallback(this);
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

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    try
    {
      return server.getLoanServer().getAllLoansByCPR(cpr);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
    }
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
      int placeID, int authorId, String genre, String url)
  {
    try
    {
      server.getMaterialServer().registerBook(title, publisher, releaseDate, description, tags, targetAudience, language, isbn, pageCount, placeID, authorId, genre, url);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Server Connection failed.");
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



  public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      int playDuration, int placeID, String genre, String url)
  {
    try
    {
      server.getMaterialServer().registerDVD(title, publisher, releaseDate, description, tags, targetAudience,
          language, subtitlesLanguage, playDuration, placeID, genre, url);
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


  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, int placeID,
      String genre, String url)
  {
    try
    {
      server.getMaterialServer().registerCD(title, publisher, releaseDate, description, tags, targetAudience, language, playDuration, placeID, genre, url);
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

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      String licenseNr, int authorId, String genre, String url)
  {
    try
    {
      server.getMaterialServer().registerEBook(title, publisher, releaseDate, description, tags, targetAudience, language, isbn, pageCount, licenseNr, authorId, genre, url);
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


  @Override public void registerAudioBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String genre,
      int authorId, String url)
  {
    try
    {
      server.getMaterialServer().registerAudioBook(title, publisher, releaseDate, description, tags, targetAudience, language, playDuration, genre, authorId,url );
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

  @Override public List<Material> findMaterial(String title, String language,
      String keywords, String genre, String targetAudience, String type)
  {
    return null;
  }

  @Override public Material getSelectMaterial()
  {
    return null;
  }

  @Override public void setSelectMaterial(Material selectMaterial)
  {

  }

  @Override public Borrower registerBorrower(String cpr_no, String f_name,
      String l_name, String email, String tel_no, Address address,
      String password)
  {
    try
    {
      return server.getUserServer().registerBorrower(cpr_no, f_name, l_name, email, tel_no, address, password);
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
      return server.getUserServer().registerLibrarian(employee_no, firstName, lastName, cpr, tlfNumber, email, address, password);
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
