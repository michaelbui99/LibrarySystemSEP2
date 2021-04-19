package client.model.loan;

public class Loan
{

  private int loanID, materialID, copyNumber, numberOfExtenseions;
  private String cpr, materialType, loanDate, deadline, returnDate;

  public Loan(int loanID, int materialID, int copyNumber, String cpr,
      String materialType, String loanDate, String deadline)
  {
    this.loanID = loanID;
    this.materialID = materialID;
    this.copyNumber = copyNumber;
    this.cpr = cpr;
    this.materialType = materialType;
    this.loanDate = loanDate;
    this.deadline = deadline;
  }

  public void setReturnDate(String returnDate)
  {
    this.returnDate = returnDate;
  }

  public int getLoanID()
  {
    return loanID;
  }

  public int getMaterialID()
  {
    return materialID;
  }

  public int getCopyNumber()
  {
    return copyNumber;
  }

  public int getNumberOfExtenseions()
  {
    return numberOfExtenseions;
  }

  public String getCpr()
  {
    return cpr;
  }

  public String getMaterialType()
  {
    return materialType;
  }

  public String getLoanDate()
  {
    return loanDate;
  }

  public String getDeadline()
  {
    return deadline;
  }

  public String getReturnDate()
  {
    return returnDate;
  }
}
