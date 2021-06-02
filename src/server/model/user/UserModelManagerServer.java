package server.model.user;

import database.user.borrower.BorrowerDAO;
import database.user.librarian.LibrarianDAO;
import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 * User model implementation for server
 *
 * @author Kutaiba
 * @version 1.0
 */
public class UserModelManagerServer implements UserModelServer
{
  private PropertyChangeSupport support;
  private String borrowerCpr;

  private BorrowerDAO borrowerDAO;
  private LibrarianDAO librarianDAO;

  public UserModelManagerServer(BorrowerDAO borrowerDAO,
      LibrarianDAO librarianDAO)
  {
    support = new PropertyChangeSupport(this);

    this.borrowerDAO = borrowerDAO;
    this.librarianDAO = librarianDAO;
  }

  @Override public Borrower create(String cpr, String firstName,
      String lastName, String email, String tlfNumber, Address address,
      String password)
  {
    Borrower borrower = null;
    try
    {
      borrower = borrowerDAO
          .create(cpr, firstName, lastName, email, tlfNumber, address,
              password);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return borrower;
  }

  @Override public boolean logInBorrower(String cprNo, String password)
  {
    boolean login = false;
    try
    {
      login = borrowerDAO.loginBorrower(cprNo, password);

      if (login)
      {
        borrowerCpr = cprNo;
      }

    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    support.firePropertyChange(EventTypes.LOGINREQUESTED, null, login);
    return login;
  }

  @Override public Borrower getLoginBorrower()
  {
    Borrower borrower = null;
    try
    {
      borrower = borrowerDAO.getBorrower(borrowerCpr);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return borrower;
  }

  @Override public Librarian registerLibrarian(int employee_no,
      String firstName, String lastName, String cpr, String tlfNumber,
      String email, Address address, String password)
  {
    Librarian librarian = null;
    try
    {
      librarian = librarianDAO
          .create(employee_no, firstName, lastName, cpr, tlfNumber, email,
              address, password);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return librarian;
  }

  @Override public boolean librarianLogin(int employee_no, String password)
  {
    boolean login = false;
    try
    {
      login = librarianDAO.librarianLogin(employee_no, password);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    support.firePropertyChange(EventTypes.LOGINREQUESTED, null, login);
    return login;
  }

  public void setBorrowerCpr(String borrowerCpr)
  {
    this.borrowerCpr = borrowerCpr;
  }

  @Override public boolean borrowerCprNumberAlreadyExists(String cpr)
  {
    boolean cprIn = false;
    try
    {
      cprIn = borrowerDAO.borrowerCprNumberAlreadyExists(cpr);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return cprIn;
  }

  @Override public boolean borrowerEmailAlreadyExists(String email)
  {
    boolean emailIn = false;
    try
    {
      emailIn = borrowerDAO.borrowerEmailAlreadyExists(email);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return emailIn;
  }

  @Override public boolean borrowerPhoneNumberAlreadyExists(String phone)
  {
    boolean phoneIn = false;
    try
    {
      phoneIn = borrowerDAO.borrowerPhoneNumberAlreadyExists(phone);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return phoneIn;
  }

  @Override public boolean borrowerAlreadyExists(String cpr, String email,
      String phone)
  {
    boolean borrowerIn = false;
    try
    {
      borrowerIn = borrowerDAO.borrowerAlreadyExists(cpr, email, phone);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return borrowerIn;
  }

  @Override public boolean employeeNumberAlreadyExists(int employeeNo)
  {
    boolean employeeNoIn = false;
    try
    {
      employeeNoIn = librarianDAO.employeeNumberAlreadyExists(employeeNo);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return employeeNoIn;
  }

  @Override public boolean librarianCprNumberAlreadyExists(String cpr)
  {
    boolean cprIn = false;
    try
    {
      cprIn = librarianDAO.librarianCprNumberAlreadyExists(cpr);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return cprIn;
  }

  @Override public boolean librarianEmailAlreadyExists(String email)
  {
    boolean emailIn = false;
    try
    {
      emailIn = librarianDAO.librarianEmailAlreadyExists(email);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return emailIn;
  }

  @Override public boolean librarianPhoneNumberAlreadyExists(String phone)
  {
    boolean phoneIn = false;
    try
    {
      phoneIn = librarianDAO.librarianPhoneNumberAlreadyExists(phone);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return phoneIn;
  }

  @Override public boolean librarianAlreadyExists(int employeeNo, String cpr,
      String email, String phone)
  {
    boolean librarianIn = false;
    try
    {
      librarianIn = librarianDAO
          .librarianAlreadyExists(employeeNo, cpr, email, phone);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    return librarianIn;
  }

  @Override public Librarian getLoginLibrarian()
  {
    return null;
  }

  @Override public Borrower getBorrowerByCPR(String cpr)
      throws NoSuchElementException
  {
    try
    {
      return borrowerDAO.getBorrower(cpr);
    }
    catch (SQLException throwable)
    {
      throwable.printStackTrace();
    }
    catch (NoSuchElementException e)
    {
      throw new NoSuchElementException(e.getMessage());
    }
    return null;
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
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
