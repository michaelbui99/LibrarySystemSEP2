package database.loan;

import client.model.loan.Address;
import client.model.loan.Loan;
import client.model.material.Material;
import client.model.material.reading.Book;
import client.model.user.Borrower;
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

  @Override public Loan create(Material material, Borrower borrower,
      String deadline, String loanDate, String returnDate)
  {
    //todo: Change stm.setString(3, 111111-1111) to borrower.getcpr()
    try
    {
      try (Connection connection = getConnection())
      {
        PreparedStatement stm = connection.prepareStatement(
            "INSERT INTO loan (loan_date, deadline, return_date, cpr_no, material_id, copy_no) values (CURRENT_DATE,?,?,?,?,?)",
            Statement.RETURN_GENERATED_KEYS);
        stm.setDate(1, Date.valueOf(deadline));
        stm.setDate(2, null);
        stm.setString(3, /*borrower.getCPR()*/"111111-1111");
        stm.setInt(4, material.getMaterialID());
        stm.setInt(5, material.getCopyNumber());

        ResultSet keys = stm.getGeneratedKeys();
        keys.next();
        int generatedKey = keys.getInt(1);
        return new Loan(material, borrower, deadline, loanDate, null, generatedKey);
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public List<Loan> getAllLoansByCPR(String cpr)
  {
    //TODO: Tilføj en metode der giver alle keywords til et materiale i materialdao og ret herefter tags.

    try
    {
      try (Connection connection = getConnection())
      {
        List<Loan> loans = new ArrayList<>();
        //Get all loans where we know the material is book.
        PreparedStatement stm = connection.prepareStatement(
            "SELECT * from loan join borrower using (cpr_no) join material_copy using (material_id, copy_no) join material using (material_id) join book using (material_id) join material_creator mc ON book.author = mc.person_id join address using (address_id)  where cpr_no = ?;");
        stm.setString(1, cpr);
        ResultSet bookLoans = stm.executeQuery();
        while (bookLoans.next())
        {
          Book book = new Book(bookLoans.getInt("material_id"),
              bookLoans.getInt("copy_no"), bookLoans.getString("title"),
              bookLoans.getString("publisher"),
              String.valueOf(bookLoans.getDate("release_date")),
              bookLoans.getString("description_of_the_content"), null,
              bookLoans.getString("audience"), bookLoans.getString("language_"),
              bookLoans.getString("isbn"), bookLoans.getInt("page_no"),
              bookLoans.getInt("place_id"),
              bookLoans.getString("mc.f_name") + " "+bookLoans
                  .getString("mc.l_name"));
          Address address = new Address(bookLoans.getInt("address_id"),
              bookLoans.getString("street_name"), bookLoans.getInt("street_no"),
              bookLoans.getInt("zip_code"), bookLoans.getString("city"));
          Borrower borrower = new Borrower(cpr,
              bookLoans.getString("borrower.f_name"),
              bookLoans.getString("borrower.l_name"),bookLoans.getString("email"),
              bookLoans.getString("tel_no"), address, bookLoans.getString("password"));

          Loan loan = new Loan(book, borrower,
              String.valueOf(bookLoans.getDate("deadline")),
              String.valueOf(bookLoans.getDate("loan_date")),
              String.valueOf(bookLoans.getDate("return_date")), bookLoans.getInt("loan_no"));
          loans.add(loan);
        } return loans;
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }
}


