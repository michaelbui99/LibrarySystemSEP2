package client.core;

import client.network.RMIClient;
import client.network.RMIClientImpl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Client factory
 *
 * @author Lilian
 * @version 1.0
 */
public class ClientFactory
{
  private static ClientFactory clientFactory;
  private static Lock lock = new ReentrantLock();

  public static ClientFactory getInstance()
  {
    if (clientFactory == null)
    {
      synchronized (lock)
      {
        if (clientFactory == null)
        {
          clientFactory = new ClientFactory();
        }
      }
    }
    return clientFactory;
  }

  private RMIClient client;

  public RMIClient getClient()
  {
    if (client == null)
    {
      client = new RMIClientImpl();
    }
    return client;
  }

  private ClientFactory()
  {

  }
}
