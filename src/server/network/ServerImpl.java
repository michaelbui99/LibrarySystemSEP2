package server.network;

import server.core.ModelFactoryServer;
import server.model.loan.LoanModelServer;
import server.model.user.UserModelServer;
import server.network.loan.LoanServerImpl;
import server.network.material.MaterialServerImpl;
import server.network.reservation.ReservationServerImpl;
import server.network.user.UserServerImpl;
import shared.Server;
import shared.network.LoanServer;
import shared.network.MaterialServer;
import shared.network.ReservationServer;
import shared.network.UserServer;
import shared.util.Constants;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//Michael-Kutaiba-Lilian-Kasper
public class ServerImpl implements Server
{
  private LoanModelServer loanModel;
  private UserModelServer userModelServer;

  public ServerImpl()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0 );
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void start()
      throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind(Constants.RMISERVER, this);
    System.out.println("Server started...");
  }

  @Override public UserServer getUserServer()
  {
    return new UserServerImpl(ModelFactoryServer.getInstance().getUserModel());
  }

  @Override public MaterialServer getMaterialServer()
  {
    return new MaterialServerImpl(ModelFactoryServer.getInstance()
        .getMaterialModel());
  }

  @Override public LoanServer getLoanServer()
  {
    return new LoanServerImpl(ModelFactoryServer.getInstance().getLoanModel());
  }

  @Override public ReservationServer getReservationServer()
  {
    return new ReservationServerImpl(ModelFactoryServer.getInstance().getReservationModelServer());
  }
}
