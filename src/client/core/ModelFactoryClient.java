package client.core;

import client.model.loan.LoanModelClient;
import client.model.loan.LoanModelManagerClient;
import client.model.material.MaterialModelClient;
import client.model.material.MaterialModelManagerClient;
import client.model.reservation.ReservationModelClient;
import client.model.reservation.ReservationModelManagerClient;
import client.model.user.UserModelClient;
import client.model.user.UserModelManagerClient;
import client.network.Client;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Model factory for client models
 *
 * @author Lilian
 * @version 1.0
 */
public class ModelFactoryClient
{
  private static ModelFactoryClient modelFactory;
  private static Lock lock = new ReentrantLock();

  public static ModelFactoryClient getInstance()
  {
    if (modelFactory == null)
    {
      synchronized (lock)
      {
        if (modelFactory == null)
        {
          modelFactory = new ModelFactoryClient();
        }
      }
    }
    return modelFactory;
  }

  private LoanModelClient loanModelClient;

  public LoanModelClient getLoanModelClient()
  {
    if (loanModelClient == null)
    {
      loanModelClient = new LoanModelManagerClient(
          (Client) ClientFactory.getInstance().getClient());
    }
    return loanModelClient;
  }

  private MaterialModelClient materialModelClient;

  public MaterialModelClient getMaterialModelClient()
  {
    if (materialModelClient == null)
    {
      materialModelClient = (MaterialModelClient) new MaterialModelManagerClient(
          (Client) ClientFactory.getInstance().getClient());
    }
    return materialModelClient;
  }

  private UserModelClient userModelClient;

  public UserModelClient getUserModelClient()
  {
    if (userModelClient == null)
    {
      userModelClient = new UserModelManagerClient(
          (Client) ClientFactory.getInstance().getClient());
    }
    return userModelClient;
  }

  private ReservationModelClient reservationModelClient;

  public ReservationModelClient getReservationModelClient()
  {
    if (reservationModelClient == null)
    {
      reservationModelClient = new ReservationModelManagerClient(
          (Client) ClientFactory.getInstance().getClient());
    }
    return reservationModelClient;
  }
}
