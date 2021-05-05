package server.model;

import client.model.material.*;
import client.model.material.audio.AudioBook;
import client.model.material.audio.CD;
import client.model.material.reading.Book;
import client.model.material.reading.EBook;
import database.loan.LoanDAOImpl;
import database.material.*;
import shared.util.EventTypes;
import client.model.loan.Loan;
import client.model.loan.LoanList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryModelManager implements LibraryModel
{
  private LoanList loanList;
  private MaterialList materialList;
  private PropertyChangeSupport support;
  private MaterialSearchStrategyNavigator materialSearchStrategyNavigator;

  public LibraryModelManager()
  {
    loanList = new LoanList();
    support = new PropertyChangeSupport(this);
    materialList = new MaterialList();
    materialSearchStrategyNavigator = new MaterialSearchStrategyNavigator("all");
  }

  /**
   * Formats the current time to dd/MM/yyyy format
   *
   * @return Current time in dd/MM/yyyy as String
   */
  private String calcDateTime()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    return sdf.format(now);
  }

  @Override public Loan registerLoan(Material material, String loanerCPR,
      String deadline)
  {
    if (material.getMaterialStatus().equals(MaterialStatus.NotAvailable))
    {
      throw new IllegalStateException("Material is not available for loan");
    }
    else
    {
      material.setMaterialStatus(MaterialStatus.NotAvailable);
      Loan loan = null;
      try
      {
        loan = LoanDAOImpl.getInstance()
            .create(material.getMaterialID(), material.getCopyNumber(),
                loanerCPR,  calcDateTime(), deadline);
      }
      catch (SQLException throwables)
      {
        throwables.printStackTrace();
      }
      loanList.addLoan(loan);
      support.firePropertyChange(EventTypes.LOAN_REGISTERED, null, loan);
      return loan;
    }
  }

     public Book getBook(int materialID) throws SQLException
     {

       ResultSet rs = BookDAOImpl.getInstance().getBookDetailsByID(materialID);
        Book temp = new Book(rs.getInt("material_id"), rs.getInt("copy_no"),rs.getString("title"),
            rs.getString("publisher"), rs.getString("release_date"), rs.getString("description_of_the_content"),
            rs.getString("keywords"), rs.getString("audience"), rs.getString("language_"), rs.getString("isbn"),
            rs.getInt("page_no"), rs.getInt("place_id"));
        return temp;
     }

  @Override public void registerBook(String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, int placeID, int authorId, String genre,
      String url)
  {
    try
    {
      //Creates new material in Database and saves the generated id in variable if material does NOT already exist in DB.
      int generatedID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, tags,
              targetAudience, language, genre, url);
      BookDAOImpl.getInstance().create(generatedID, isbn, pageCount,  authorId, placeID);
      support.firePropertyChange(EventTypes.MATERIAL_REGISTERED, null, null);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createBookCopy(int materialID)
  {
    try
    {
      Book book = BookDAOImpl.getInstance().createBookCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID) + 1);
      materialList.addMaterial(book);
      support.firePropertyChange(EventTypes.MATERIAL_COPY_CREATED, null, book);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void registerDVD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage,
      String playDuration, int placeID, String genre, String url)
  {
    //TODO: Mangler muligvis genre i DB, og er derfor sat til null indtil videre.
    try
    {
      int generatedID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, tags,
              targetAudience, language, genre, url);
      DVDDAOImpl
          .getInstance().create(generatedID, subtitlesLanguage, playDuration, placeID);
      support.firePropertyChange(EventTypes.MATERIAL_REGISTERED, null, null);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

  }

  @Override public void createDVDCopy(int materialID)
  {
    try
    {
      DVD dvd = DVDDAOImpl.getInstance().createDVDCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID) + 1);
      materialList.addMaterial(dvd);
      support.firePropertyChange(EventTypes.MATERIAL_COPY_CREATED, null, dvd);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void registerCD(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID,
      String genre, String url)
  {
    try
    {
      int generatedID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, tags,
              targetAudience, language, genre, url);
      CDDAOImpl.getInstance().create(generatedID, (int) playDuration, placeID);
      support.firePropertyChange(EventTypes.MATERIAL_REGISTERED, null, null);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

  }

  @Override public void createCDCopy(int materialID)
  {
    try
    {
      CD cd = CDDAOImpl.getInstance().createCDCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID) + 1);
      materialList.addMaterial(cd);
      support.firePropertyChange(EventTypes.MATERIAL_COPY_CREATED, null, cd);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerEBook(String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, String licenseNr, int authorId, String genre,
      String url)
  {
    try
    {
      //TODO: Find ud af hvordan et licensNR til Ebook ser ud, så vi ved om det skal være String eller Int.
      int generatedID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, tags,
              targetAudience, language, genre, url);
      EbogDAOImpl.getInstance()
          .create(generatedID, pageCount, authorId, Integer.parseInt(licenseNr));
      support.firePropertyChange(EventTypes.MATERIAL_REGISTERED, null, null);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

  }

  @Override public void createEBookCopy(int materialID)
  {
    try
    {
      EBook eBook = EbogDAOImpl.getInstance().createEBookCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID) + 1);
      materialList.addMaterial(eBook);
      support.firePropertyChange(EventTypes.MATERIAL_COPY_CREATED, null, eBook);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

  }

  @Override public void registerAudioBook(String title, String publisher, String releaseDate, String description,
      String tags, String targetAudience, String language, double playDuration, String genre,
      int authorId, String url)
  {
    try
    {
      int generatedID = MaterialDAOImpl.getInstance()
          .create(title, publisher, releaseDate, description, tags,
              targetAudience, language ,genre, url);
    audiobookDAOImpl
        .getInstance().create(generatedID, playDuration, authorId);
    support.firePropertyChange(EventTypes.MATERIAL_REGISTERED,null, null);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createAudioBookCopy(int materialID)
  {
    try
    {
      AudioBook audioBook = audiobookDAOImpl.getInstance().createAudioBookCopy(materialID,
          MaterialDAOImpl.getInstance().getLatestCopyNo(materialID) + 1);
    materialList.addMaterial(audioBook);
    support.firePropertyChange(EventTypes.MATERIAL_COPY_CREATED, null, audioBook);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public MaterialList searchMaterial(String title, String language,
      String keywords, String genre, String targetAudience, String type)
      throws SQLException
  {
    this.materialSearchStrategyNavigator.setType(type);
    MaterialList ml = materialSearchStrategyNavigator.findMaterial(title, language, keywords, genre, targetAudience);
    return ml;
  }


  @Override public boolean deliverMaterial(int materialID, String cpr, int copy_no)
  {
       return  MaterialDAOImpl.getInstance().deliverMaterial(materialID,cpr,copy_no);
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
