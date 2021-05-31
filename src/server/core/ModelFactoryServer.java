package server.core;

import database.loan.LoanDAOImpl;
import database.material.*;
import database.reservation.ReservationDAOImpl;
import database.user.borrower.BorrowerImpl;
import database.user.librarian.LibrarianImpl;
import server.model.loan.LoanModelManagerServer;
import server.model.loan.LoanModelServer;
import server.model.material.MaterialModelManagerServer;
import server.model.material.MaterialModelServer;
import server.model.reservation.ReservationModelManagerServer;
import server.model.reservation.ReservationModelServer;
import server.model.user.UserModelManagerServer;
import server.model.user.UserModelServer;

public class ModelFactoryServer
{
  private static ModelFactoryServer instance;
  private LoanModelServer loanModelServer;
  private MaterialModelServer materialModelServer;
  private UserModelServer userModelServer;
  private ReservationModelServer reservationModelServer;

  private ModelFactoryServer()
  {
  }

  public static ModelFactoryServer getInstance()
  {
    if (instance == null)
    {
      instance = new ModelFactoryServer();
    }
    return instance;
  }

  public LoanModelServer getLoanModel()
  {
    if (loanModelServer == null)
    {
      loanModelServer = new LoanModelManagerServer(LoanDAOImpl.getInstance(),
          ReservationDAOImpl.getInstance(), MaterialDAOImpl.getInstance());
    }
    return loanModelServer;
  }

  public MaterialModelServer getMaterialModel()
  {
    if (materialModelServer == null)
    {
      materialModelServer = new MaterialModelManagerServer(
          AudioBookDAOImpl.getInstance(), BookDAOImpl.getInstance(),
          EbookDAOImpl.getInstance(), CDDAOImpl.getInstance(),
          DVDDAOImpl.getInstance(), MaterialDAOImpl.getInstance(),
          MaterialCopyDAOImpl.getInstance());
    }
    return materialModelServer;
  }

  public UserModelServer getUserModel()
  {
    if (userModelServer == null)
    {
      userModelServer = new UserModelManagerServer(BorrowerImpl.getInstance(),
          LibrarianImpl.getInstance());
    }
    return userModelServer;
  }

  public ReservationModelServer getReservationModelServer()
  {
    if (reservationModelServer == null)
    {
      reservationModelServer = new ReservationModelManagerServer(
          ReservationDAOImpl.getInstance(), MaterialDAOImpl.getInstance());
    }
    return reservationModelServer;
  }

}
