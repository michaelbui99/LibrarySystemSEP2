package shared.person.borrower;

import java.util.ArrayList;
import java.util.List;

public class BorrowerList
{
  private List<Borrower> borrowers;

  public BorrowerList()
  {
    borrowers = new ArrayList<>();
  }

  public void addBorrower(Borrower borrower)
  {
    borrowers.add(borrower);
  }

  public Borrower getBorrowerByCPR(String cpr)
  {
    for (Borrower borrower : borrowers)
    {
      if (borrower.getCpr().equals(cpr))
      {
        return borrower;
      }
    }
    return null;
  }

}
