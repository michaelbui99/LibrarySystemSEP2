package server.network;

import server.model.chat.ChatModelServer;
import server.model.loan.LoanModelServer;
import server.model.user.UserModelServer;
import server.network.loan.LoanServerImpl;
import server.network.material.MaterialServerImpl;
import server.network.user.UserServerImpl;
import shared.*;
import shared.servers.ChatServer;
import shared.servers.LoanServer;
import shared.servers.MaterialServer;
import shared.servers.UserServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl implements Server
{
  private LoanModelServer loanModel;
  private UserModelServer userModelServer;
  private ChatModelServer chatModelServer;

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

  @Override public UserServer getUserServer()
  {
    return new UserServerImpl();
  }

  @Override public MaterialServer getMaterialServer()
  {
    return new MaterialServerImpl();
  }

  @Override public LoanServer getLoanServer()
  {
    return new LoanServerImpl();
  }

  @Override public ChatServer getChatServer()
  {
    return null;
  }
}
