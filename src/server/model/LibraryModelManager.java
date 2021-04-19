package server.model;

import client.model.library.LibraryModel;
import client.model.loan.Loan;
import client.model.loan.LoanList;
import client.model.loan.Loaner;
import client.model.material.Material;
import client.model.material.MaterialStatus;
import shared.util.IDGenerator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryModelManager implements LibraryModel
{
  private LoanList loanList;
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
      support.firePropertyChange("LoanRegistered", null, loan);
    }
  }

  @Override public void registerBook(Loaner loaner, Material material)
  {

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
