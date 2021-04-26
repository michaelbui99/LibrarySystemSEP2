package server.model;

import client.model.material.MaterialList;
import client.model.material.reading.Book;
import database.*;
import shared.util.Constants;
import shared.util.EventTypes;
import client.model.loan.Loan;
import client.model.loan.LoanList;
import client.model.material.Material;
import client.model.material.MaterialStatus;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryModelManager implements LibraryModel
{
  private LoanList loanList;
  private MaterialList materialList;
  private PropertyChangeSupport support;

  public LibraryModelManager()
  {
    loanList = new LoanList();
    support = new PropertyChangeSupport(this);
    materialList = new MaterialList();
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

  @Override public void registerLoan(Material material, String loanerCPR,
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
                loanerCPR, null, calcDateTime(), deadline);
      }
      catch (SQLException throwables)
      {
        throwables.printStackTrace();
      }
      loanList.addLoan(loan);
      support.firePropertyChange(EventTypes.LOAN_REGISTERED, null, loan);
    }
  }

  @Override public void registerBook(int materialID, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      int placeID)
  {
    try
    {
      //Creates new material in Database and saves the generated id in variable if material does NOT already exist in DB.
      int generatedID = MaterialDAOImpl.getInstance()
          .create("title", publisher, releaseDate, description, tags,
              targetAudience, language);
      BookDAOImpl.getInstance().create(generatedID, isbn, pageCount, placeID);
      support.firePropertyChange(EventTypes.BOOK_REGISTERED, null, null);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public void createBookCopy(int materialID, int copyNo)
  {
    try
    {
      Book book = BookDAOImpl.getInstance().createBookCopy(materialID, copyNo);
      materialList.addMaterial(book);
      support.firePropertyChange(EventTypes.BOOK_COPY_CREATED, null, book);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public Material searchMaterial(String arg)
  {
    //TODO: IMPLEMENT THIS CORRECTLY - THIS IS A PLACE HOLDER IMPL FOR TEST
    return materialList.getMaterialById(Integer.parseInt(arg));
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

  private void createBookCopy(int materialID, int copyNumber, String isbn,
      int pageCount) throws SQLException
  {
    MaterialCopyDAOImpl.getInstance().create(materialID, copyNumber);
    Book book = BookCopyDAOImpl.getInstance()
        .create(materialID, copyNumber, isbn, pageCount);
    materialList.addMaterial(book);
    support.firePropertyChange(EventTypes.BOOK_REGISTERED, null, book);
  }
}
