package server.model;

import client.model.material.MaterialList;
import client.model.material.reading.Book;
import database.*;
import shared.util.EventTypes;
import server.model.LibraryModel;
import client.model.loan.Loan;
import client.model.loan.LoanList;
import client.model.material.Material;
import client.model.material.MaterialStatus;
import shared.util.IDGenerator;

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
  }


  /**
  * Formats the current time to dd/MM/yyyy format
   * @return Current time in dd/MM/yyyy as String
  * */
  private String calcDateTime()
  {
      SimpleDateFormat sdf = new SimpleDateFormat(
          "dd/MM/yyyy");
      Date now = new Date();
      return sdf.format(now);
  }


  /**
  * Registers a new Loan for the given material and loaner.
   *{@inheritDoc}
  * */
  @Override public void registerLoan(Material material, String loanerCPR, String deadline)
  {
    if (material.getMaterialStatus().equals(MaterialStatus.NotAvailable))
    {
      throw new IllegalStateException("Material is not available for loan");
    }
    else
    {
      material.setMaterialStatus(MaterialStatus.NotAvailable);
      Loan loan = new Loan(IDGenerator.getInstance().generateLoanId(), material.getMaterialID(),
          material.getCopyNumber(),loanerCPR, material.getMaterialType(), calcDateTime(),deadline);
      loanList.addLoan(loan);
      support.firePropertyChange(EventTypes.LOAN_REGISTERED, null, loan);
    }
  }


  @Override public void registerBook(int materialID, int copyNumber,
      String title, String publisher, String releaseDate, String description,
      String tags, String targetAudience, String language, String isbn,
      int pageCount)
  {
    try
    {
      int generatedID = MaterialDAOImpl.getInstance().create(title,publisher, releaseDate,description, tags, targetAudience, language);
      MaterialCopyDAOImpl.getInstance().create(generatedID, copyNumber);
      Book book = BookCopyDAOImpl.getInstance().create(generatedID,copyNumber,isbn,pageCount);
      materialList.addMaterial(book);
      support.firePropertyChange(EventTypes.BOOK_REGISTERED, null, book);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

  }


  @Override public void searchMaterial(String arg)
  {

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
    support.removePropertyChangeListener(name,listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
