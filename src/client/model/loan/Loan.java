package client.model.loan;

public class Loan
{

  private int loanID, materialID, copyNumber, numberOfExtensions;
  private String cpr,  loanDate, deadline, returnDate, title;

  public Loan(int loanID, int materialID, int copyNumber, String cpr, String loanDate, String deadline, String title, int numberOfExtensions)
  {
    this.loanID = loanID;
    this.materialID = materialID;
    this.copyNumber = copyNumber;
    this.cpr = cpr;
    this.title = title;
    this.loanDate = loanDate;
    this.deadline = deadline;
    this.numberOfExtensions = numberOfExtensions;
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

  public int getNumberOfExtensions()
  {
    return numberOfExtensions;
  }

  public String getCpr()
  {
    return cpr;
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

  public String getTitle()
  {
    return title;
  }

}
