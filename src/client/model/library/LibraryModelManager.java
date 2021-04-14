package client.model.library;

import client.model.loaner.Loan;
import client.model.loaner.LoanList;
import client.model.loaner.Loaner;
import client.model.material.Material;
import client.model.material.MaterialStatus;
import shared.util.IDGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryModelManager implements LibraryModel
{
  LoanList loanList;

  public LibraryModelManager()
  {
    loanList = new LoanList();
  }

  private String calcDateTime()
  {
      //Formats the current Date to dd/MM/yyyy HH:mm:ss format and returns it as a String.
      SimpleDateFormat sdf = new SimpleDateFormat(
          "dd/MM/yyyy HH:mm:ss");
      Date now = new Date();
      return sdf.format(now);
  }

  @Override public void registerLoan(Material material, String loanerCPR, String deadline)
  {
    if (material.getMaterialStatus().equals(MaterialStatus.NotAvailable))
    {
      throw new IllegalArgumentException("Material is not available for loan");
    }
    else
    {
      material.setMaterialStatus(MaterialStatus.NotAvailable);
      Loan loan = new Loan(IDGenerator.getInstance().generateLoanId(), material.getMaterialID(),
          material.getCopyNumber(),loanerCPR, material.getMaterialType(), calcDateTime(),deadline);
      loanList.addLoan(loan);
    }
  }

  @Override public void registerBook(Loaner loaner, Material material)
  {

  }

  @Override public void searchMaterial(String arg)
  {

  }
}
