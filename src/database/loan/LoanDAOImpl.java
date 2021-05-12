package database.loan;

import client.model.loan.Address;
import client.model.loan.Loan;
import client.model.material.Material;
import client.model.material.audio.AudioBook;
import client.model.material.reading.Book;
import client.model.user.borrower.Borrower;
import database.BaseDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
      String deadline, String loanDate)
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
        stm.setString(3, borrower.getCpr());
        stm.setInt(4, material.getMaterialID());
        stm.setInt(5, material.getCopyNumber());
        ResultSet keys = stm.getGeneratedKeys();
        keys.next();
        int generatedKey = keys.getInt(1);

        PreparedStatement stm2 = connection.prepareStatement("update " + material.getMaterialType().toLowerCase() + "set available = false where material_id = ? and copy_no = ?");
        stm.setInt(1, material.getMaterialID());
        stm.setInt(2, material.getCopyNumber());
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
    //TODO: Hvis DAO Metoder for getBorrowerByCPR, getAddressByCPR og getBookByMaterialID,get... getDVDByMaterialID findes, så brug de metoder i denne metode.
    try
    {
      try (Connection connection = getConnection())
      {
        List<Loan> loans = new ArrayList<>();
        //Get all loans where material is known to be book
        PreparedStatement stm = connection.prepareStatement(
            "SELECT * from loan join borrower using (cpr_no) join address using (address_id) join material_copy using (material_id, copy_no) join material using (material_id) join book using (material_id) join material_creator mc ON book.author = mc.person_id where cpr_no = ?;");
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
        }
        //Get all loans where material is known to be audiobook
        PreparedStatement stm2 = connection.prepareStatement(
            "SELECT * from loan join borrower using (cpr_no) join address using (address_id) join material_copy using (material_id, copy_no) join material using (material_id) join audiobook using (material_id) join material_creator mc ON audiobook.author = mc.person_id where cpr_no = ?;");
        stm.setString(1, cpr);
        ResultSet audiobookLoans = stm2.executeQuery();
        while (audiobookLoans.next())
        {
          AudioBook audioBook = new AudioBook(audiobookLoans.getInt("material_id"),
              audiobookLoans.getInt("copy_no"), audiobookLoans.getString("title"),
              audiobookLoans.getString("publisher"),
              String.valueOf(audiobookLoans.getDate("release_date")),
              audiobookLoans.getString("description_of_the_content"), null,
              audiobookLoans.getString("audience"), audiobookLoans.getString("language_"),
              audiobookLoans.getInt("length_"),
              audiobookLoans.getString("mc.f_name") + " "+audiobookLoans
                  .getString("mc.l_name"));
          Address address = new Address(audiobookLoans.getInt("address_id"),
              audiobookLoans.getString("street_name"), audiobookLoans.getInt("street_no"),
              audiobookLoans.getInt("zip_code"), audiobookLoans.getString("city"));
          Borrower borrower = new Borrower(cpr,
              audiobookLoans.getString("borrower.f_name"),
              audiobookLoans.getString("borrower.l_name"),audiobookLoans.getString("email"),
              audiobookLoans.getString("tel_no"), address, audiobookLoans.getString("password"));
          Loan loan = new Loan(audioBook, borrower,
              String.valueOf(audiobookLoans.getDate("deadline")),
              String.valueOf(audiobookLoans.getDate("loan_date")),
              String.valueOf(audiobookLoans.getDate("return_date")), audiobookLoans.getInt("loan_no"));
          loans.add(loan);
        }

        return loans;
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public void endLoan(int loanID)
  {
    try
    {
      try (Connection connection = getConnection())
      {
        //TODO: ADD SQL Statement to find the material copy which is in loan and update the status of the copy.
        PreparedStatement stm = connection.prepareStatement("UPDATE loan set return_date = CURRENT_DATE where loan_no = ?");
        stm.setInt(1, loanID);
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }


}


