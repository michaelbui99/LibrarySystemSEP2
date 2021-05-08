package database.loan;

import client.model.loan.Loan;
import database.BaseDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoanDAOImpl extends BaseDAO implements LoanDAO
{
  private static LoanDAO instance;
  private static final Lock lock = new ReentrantLock();

  public static LoanDAO getInstance()
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new LoanDAOImpl();
        }
      }
    }
    return instance;
  }

  @Override public Loan create(int materialID, int copyNumber, String cpr,
      String loanDate, String deadline) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement(
          //the table structure needs to change to the values from the query so we can test it
          "INSERT INTO loan(loan_date,deadline,return_date, cpr_no, material_id, copy_no) values (?,?,?,?,?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      stm.setDate(1, Date.valueOf(loanDate));
      stm.setDate(2, Date.valueOf(deadline));
      stm.setDate(3, null);
      stm.setString(4, cpr);
      stm.setInt(5, materialID);
      stm.setInt(6, copyNumber);
      stm.executeUpdate();
      ResultSet keys = stm.getGeneratedKeys();
      keys.next();
      connection.commit();
      return new Loan(keys.getInt("loan_no"), materialID, copyNumber, cpr,
          loanDate, deadline);
    }
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr) throws SQLException
  {
    //TODO: Tilføj Number of extensions til Loan i DB og ændre værdien i metoden fra 0 til den faktiske værdi.
    try (Connection connection = getConnection())
    {
      List<Loan> loans = new ArrayList<>();
      PreparedStatement stm = connection.prepareStatement(
          "SELECT * FROM loan JOIN borrower USING(cpr_no) JOIN material_copy USING(material_id, copy_no) JOIN Material using (material_id) WHERE cpr_no = ?");
      stm.setString(1, cpr);
      ResultSet result = stm.executeQuery();
      if (result.next())
      {
        while (result.next())
        {
          Loan loan = new Loan(result.getInt("loan_no"),
              result.getInt("material_id"), result.getInt("copy_no"),
              result.getString("cpr_no"),
              String.valueOf(result.getDate("loan_date")),
              String.valueOf(result.getDate("deadline")));
          loans.add(loan);
        }
        return loans;
      }
      else
        throw new NoSuchElementException("No loans for CPR was found.");
    }
  }

}
