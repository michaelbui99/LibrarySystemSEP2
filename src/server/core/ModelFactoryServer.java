package server.core;

import server.model.chat.ChatModelManagerServer;
import server.model.chat.ChatModelServer;
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
  private ChatModelServer chatModelServer;
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



  public ChatModelServer getChatModel(){
    if (chatModelServer == null){
      chatModelServer = new ChatModelManagerServer();
    }
    return chatModelServer;
  }

  public LoanModelServer getLoanModel(){
    if (loanModelServer == null){
      loanModelServer = new LoanModelManagerServer();
    }
    return loanModelServer;
  }

  public MaterialModelServer getMaterialModel(){
    if (materialModelServer == null){
      materialModelServer =  new MaterialModelManagerServer();
    }
    return materialModelServer;
  }

  public UserModelServer getUserModel(){
    if (userModelServer==null){
      userModelServer = new UserModelManagerServer();
    }
    return userModelServer;
  }

  public ReservationModelServer getReservationModelServer()
  {
    if (reservationModelServer == null)
    {
      reservationModelServer= new ReservationModelManagerServer();
    }
    return reservationModelServer;
  }

}
