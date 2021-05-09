package client.core;

import client.network.Client;
import client.network.RMIClient;
import client.network.RMIClientImpl;

public class ClientFactory
{
  private static ClientFactory clientFactory;
  public static ClientFactory getClientFactory()
  {
    if (clientFactory == null){
      clientFactory = new ClientFactory();
    }
    return clientFactory;
  }
  private RMIClient client;

  public RMIClient getClient(){
    if (client == null){
      client = new RMIClientImpl();
    }
    return client;
  }

  private ClientFactory(){

  }
}
