package database.loan;

import database.reservation.ReservationDAO;
import database.reservation.ReservationDAOImpl;
import shared.loan.ExtendedLoan1State;
import shared.loan.ExtendedLoan2State;
import shared.loan.NewLoanState;
import database.material.MaterialCopyDAOImpl;
import database.material.MaterialDAOImpl;
import shared.person.Address;
import shared.loan.Loan;
import shared.materials.DVD;
import shared.materials.Material;
import shared.materials.Place;
import shared.materials.audio.AudioBook;
import shared.materials.audio.CD;
import shared.materials.reading.Book;
import shared.materials.reading.EBook;
import shared.person.MaterialCreator;
import shared.person.borrower.Borrower;
import database.BaseDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoanDAOImpl extends BaseDAO implements LoanDAO
{
  private static LoanDAO instance;
  private static final Lock lock = new ReentrantLock();

  private LoanDAOImpl()
  {
  }

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

  @Override public synchronized Loan create(Material material,
      Borrower borrower, String deadline, String loanDate)
  {
    try
    {
      try (Connection connection = getConnection())
      {
          LocalDate today = LocalDate.now();
          //Sets deadline to be one month after loan date.
          LocalDate loanDeadline = today.plusMonths(1);
          int copyNo = MaterialCopyDAOImpl.getInstance()
              .getFirstAvailableCopyNo(material.getMaterialID());
          PreparedStatement stm = connection.prepareStatement(
              "INSERT INTO loan (loan_date, deadline, return_date, cpr_no, material_id, copy_no) values (CURRENT_DATE,?,?,?,?,?)",
              Statement.RETURN_GENERATED_KEYS);
          stm.setDate(1, Date.valueOf(loanDeadline));
          stm.setDate(2, null);
          stm.setString(3, borrower.getCpr());
          stm.setInt(4, material.getMaterialID());
          stm.setInt(5, copyNo);
          stm.executeUpdate();
          ResultSet generatedKeys = stm.getGeneratedKeys();
          generatedKeys.next();
          int generatedKey = generatedKeys.getInt(1);

          PreparedStatement stm2 = connection.prepareStatement(
              "update material_copy set available = false where material_id = ? and copy_no = ?");
          stm2.setInt(1, material.getMaterialID());
          stm2.setInt(2, copyNo);
          stm2.executeUpdate();


            connection.commit();
          return new Loan(material, borrower, loanDeadline.toString(), loanDate,
              null, generatedKey);
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
        PreparedStatement selectBorrowerAddress = connection.prepareStatement(
            "SELECT * from borrower join address using (address_id) where cpr_no = ?");
        selectBorrowerAddress.setString(1, cpr);
        ResultSet selectBorrowerAddressResult = selectBorrowerAddress
            .executeQuery();
        selectBorrowerAddressResult.next();
        Address address = new Address(
            selectBorrowerAddressResult.getInt("address_id"),
            selectBorrowerAddressResult.getString("street_name"),
            selectBorrowerAddressResult.getString("street_no"),
            selectBorrowerAddressResult.getInt("zip_code"),
            selectBorrowerAddressResult.getString("city"));
        Borrower borrower = new Borrower(cpr,
            selectBorrowerAddressResult.getString("f_name"),
            selectBorrowerAddressResult.getString("l_name"),
            selectBorrowerAddressResult.getString("email"),
            selectBorrowerAddressResult.getString("tel_no"), address,
            selectBorrowerAddressResult.getString("password"));

        PreparedStatement stm = connection.prepareStatement(
            "SELECT * from loan join borrower using (cpr_no) join address using (address_id) join material_copy using (material_id, copy_no) join material using (material_id) join book using (material_id) join material_creator mc ON book.author = mc.person_id join place using (place_id) where cpr_no = ? and return_date IS NULL;");
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
              new Place(bookLoans.getInt("place_id"),
                  bookLoans.getInt("hall_no"),
                  bookLoans.getString("department"),
                  bookLoans.getString("creator_l_name"),
                  bookLoans.getString("genre")),
              new MaterialCreator(bookLoans.getInt("person_id"),
                  bookLoans.getString(28), bookLoans.getString(29),
                  String.valueOf(bookLoans.getDate("dob")),
                  bookLoans.getString("country")));
          Loan loan = new Loan(book, borrower,
              String.valueOf(bookLoans.getDate("deadline")),
              String.valueOf(bookLoans.getDate("loan_date")),
              String.valueOf(bookLoans.getDate("return_date")),
              bookLoans.getInt("loan_no"));
          int numberOfExtensions = bookLoans.getInt("number_of_extensions");
          switch (numberOfExtensions)
          {
            case 0:
              loan.setLoanState(new NewLoanState());
              break;
            case 1:
              loan.setLoanState(new ExtendedLoan1State());
              break;
            case 2:
              loan.setLoanState(new ExtendedLoan2State());
              break;
          }
          loans.add(loan);
        }
        //Get all loans where material is known to be audiobook
        PreparedStatement stm2 = connection.prepareStatement(
            "SELECT * from loan join borrower using (cpr_no) join address using (address_id) join material_copy using (material_id, copy_no) join material using (material_id) join audiobook using (material_id) join material_creator mc ON audiobook.author = mc.person_id where cpr_no = ? and return_date IS NULL;");
        stm2.setString(1, cpr);
        ResultSet audiobookLoans = stm2.executeQuery();
        while (audiobookLoans.next())
        {
          AudioBook audioBook = new AudioBook(
              audiobookLoans.getInt("material_id"),
              audiobookLoans.getInt("copy_no"),
              audiobookLoans.getString("title"),
              audiobookLoans.getString("publisher"),
              String.valueOf(audiobookLoans.getDate("release_date")),
              audiobookLoans.getString("description_of_the_content"), null,
              audiobookLoans.getString("audience"),
              audiobookLoans.getString("language_"),
              audiobookLoans.getInt("length_"),
              new MaterialCreator(audiobookLoans.getInt("person_id"),
                  audiobookLoans.getString(26), audiobookLoans.getString(27),
                  String.valueOf(audiobookLoans.getDate("dob")),
                  audiobookLoans.getString("city")),
              audiobookLoans.getString("url"));
          Loan loan = new Loan(audioBook, borrower,
              String.valueOf(audiobookLoans.getDate("deadline")),
              String.valueOf(audiobookLoans.getDate("loan_date")),
              String.valueOf(audiobookLoans.getDate("return_date")),
              audiobookLoans.getInt("loan_no"));

          int numberOfExtensions = audiobookLoans
              .getInt("number_of_extensions");
          switch (numberOfExtensions)
          {
            case 0:
              loan.setLoanState(new NewLoanState());
              break;
            case 1:
              loan.setLoanState(new ExtendedLoan1State());
              break;
            case 2:
              loan.setLoanState(new ExtendedLoan2State());
              break;
          }
          loans.add(loan);
        }
        //Get all loans where material is known to be DVD
        PreparedStatement stm3 = connection.prepareStatement(
            "SELECT * FROM loan JOIN borrower USING (cpr_no) JOIN address USING (address_id)  JOIN material_copy USING (material_id, copy_no) JOIN material USING (material_id) JOIN dvd USING (material_id) join place using (place_id) WHERE cpr_no = ? and return_date IS NULL");
        stm3.setString(1, cpr);
        ResultSet dvdLoans = stm3.executeQuery();
        while (dvdLoans.next())
        {
          DVD dvd = new DVD(dvdLoans.getInt("material_id"),
              dvdLoans.getInt("copy_no"), dvdLoans.getString("title"),
              dvdLoans.getString("publisher"),
              String.valueOf(dvdLoans.getDate("release_date")),
              dvdLoans.getString("description_of_the_content"), null,
              dvdLoans.getString("audience"), dvdLoans.getString("language_"),
              dvdLoans.getString("subtitle_lang"),
              String.valueOf(dvdLoans.getInt("length_")),
              new Place(dvdLoans.getInt("place_id"), dvdLoans.getInt("hall_no"),
                  dvdLoans.getString("department"),
                  dvdLoans.getString("creator_l_name"),
                  dvdLoans.getString("genre")), dvdLoans.getString("URL"));
          Loan loan = new Loan(dvd, borrower,
              String.valueOf(dvdLoans.getDate("deadline")),
              String.valueOf(dvdLoans.getDate("loan_date")),
              String.valueOf(dvdLoans.getDate("return_date")),
              dvdLoans.getInt("loan_no"));

          int numberOfExtensions = dvdLoans.getInt("number_of_extensions");
          switch (numberOfExtensions)
          {
            case 0:
              loan.setLoanState(new NewLoanState());
              break;
            case 1:
              loan.setLoanState(new ExtendedLoan1State());
              break;
            case 2:
              loan.setLoanState(new ExtendedLoan2State());
              break;
          }
          loans.add(loan);
        }
        //Get all loans where Material is known to be CD
        PreparedStatement stm4 = connection.prepareStatement(
            "SELECT * FROM loan JOIN borrower USING (cpr_no) JOIN address USING (address_id)  JOIN material_copy USING (material_id, copy_no) JOIN material USING (material_id) JOIN cd USING (material_id) join place using (place_id) WHERE cpr_no = ? and return_date IS NULL");
        stm4.setString(1, cpr);
        ResultSet cdLoans = stm4.executeQuery();
        while (cdLoans.next())
        {
          CD cd = new CD(cdLoans.getInt("material_id"),
              cdLoans.getInt("copy_no"), cdLoans.getString("title"),
              cdLoans.getString("publisher"),
              String.valueOf(cdLoans.getDate("release_date")),
              cdLoans.getString("description_of_the_content"), null,
              cdLoans.getString("audience"), cdLoans.getString("language_"),
              cdLoans.getInt("length_"),
              new Place(cdLoans.getInt("place_id"), cdLoans.getInt("hall_no"),
                  cdLoans.getString("department"),
                  cdLoans.getString("creator_l_name"),
                  cdLoans.getString("genre")), cdLoans.getString("url"));

          Loan loan = new Loan(cd, borrower,
              String.valueOf(cdLoans.getDate("deadline")),
              String.valueOf(cdLoans.getDate("loan_date")),
              String.valueOf(cdLoans.getDate("return_date")),
              cdLoans.getInt("loan_no"));

          int numberOfExtensions = cdLoans.getInt("number_of_extensions");
          switch (numberOfExtensions)
          {
            case 0:
              loan.setLoanState(new NewLoanState());
              break;
            case 1:
              loan.setLoanState(new ExtendedLoan1State());
              break;
            case 2:
              loan.setLoanState(new ExtendedLoan2State());
              break;
          }
          loans.add(loan);
        }
        //Get all loans where material is known to be Ebook.
        PreparedStatement stm5 = connection.prepareStatement(
            "SELECT * FROM loan JOIN borrower USING (cpr_no) JOIN address USING (address_id)  JOIN material_copy USING (material_id, copy_no) JOIN material USING (material_id) JOIN e_book USING (material_id) join material_creator mc ON e_book.author = mc.person_id WHERE cpr_no = ? and return_date IS NULL");
        stm5.setString(1, cpr);
        ResultSet ebookLoans = stm5.executeQuery();
        while (ebookLoans.next())
        {
          EBook eBook = new EBook(ebookLoans.getInt("material_id"),
              ebookLoans.getInt("copy_no"), ebookLoans.getString("title"),
              ebookLoans.getString("publisher"),
              String.valueOf(ebookLoans.getDate("release_date")),
              ebookLoans.getString("description_of_the_content"), null,
              ebookLoans.getString("audience"),
              ebookLoans.getString("language_"), ebookLoans.getInt("page_no"),
              ebookLoans.getString("license_no"), ebookLoans.getString("genre"),
              new MaterialCreator(ebookLoans.getInt("person_id"),
                  ebookLoans.getString(26), ebookLoans.getString(27),
                  String.valueOf(ebookLoans.getDate("dob")),
                  ebookLoans.getString("city")));
          Loan loan = new Loan(eBook, borrower,
              String.valueOf(ebookLoans.getDate("deadline")),
              String.valueOf(ebookLoans.getDate("loan_date")),
              String.valueOf(ebookLoans.getDate("return_date")),
              ebookLoans.getInt("loan_no"));

          int numberOfExtensions = ebookLoans.getInt("number_of_extensions");
          switch (numberOfExtensions)
          {
            case 0:
              loan.setLoanState(new NewLoanState());
              break;
            case 1:
              loan.setLoanState(new ExtendedLoan1State());
              break;
            case 2:
              loan.setLoanState(new ExtendedLoan2State());
              break;
          }
          loans.add(loan);
        }

        if (!loans.isEmpty())
        {
          return loans;
        }
        else
          throw new NoSuchElementException("Ingen aktive lån");

      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public void endLoan(Loan loan)
  {
    try
    {
      try (Connection connection = getConnection())
      {
        //TODO: ADD SQL Statement to find the material copy which is in loan and update the status of the copy.
        PreparedStatement stm = connection.prepareStatement(
            "UPDATE loan set return_date = CURRENT_DATE where loan_no = ?");
        stm.setInt(1, loan.getLoanID());
        stm.executeUpdate();
        PreparedStatement stm2 = connection.prepareStatement(
            "UPDATE material_copy set available = true where material_id = ? and copy_no = ? ;");
        stm2.setInt(1, loan.getMaterial().getMaterialID());
        stm2.setInt(2, loan.getMaterial().getCopyNumber());
        stm2.executeUpdate();
        connection.commit();
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public Loan extendLoan(Loan loan)
  {
    try (Connection connection = getConnection())
    {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate oldDeadline = LocalDate.parse(loan.getDeadline(), formatter);
      LocalDate newDeadline = oldDeadline.plusMonths(1);

      //Checks to see if the number of extension in DB is 2.
      PreparedStatement stm2 = connection.prepareStatement(
          "SELECT number_of_extensions from loan where loan_no = ?");
      stm2.setInt(1, loan.getLoanID());
      ResultSet result2 = stm2.executeQuery();
      result2.next();
      int numberOfExtensions = result2.getInt("number_of_extensions");
      //Throws exception if numberofExtensions is 2, because the Loan cannot be extended further.
      if (numberOfExtensions == 2)
      {
        throw new IllegalStateException();
      }
      else
      {
        PreparedStatement stm = connection.prepareStatement(
            "UPDATE loan SET deadline = ?, number_of_extensions = number_of_extensions+1 where loan_no = ?");
        stm.setDate(1, Date.valueOf(newDeadline));
        stm.setInt(2, loan.getLoanID());
        stm.executeUpdate();
        connection.commit();

        return new Loan(loan.getMaterial(), loan.getBorrower(),
            newDeadline.toString(), loan.getLoanDate(), null, loan.getLoanID(),
            loan.getLoanState());
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

}


