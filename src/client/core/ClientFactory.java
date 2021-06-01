package client.core;

import client.network.RMIClient;
import client.network.RMIClientImpl;

/** Client factory
 * @author Lilian
 * @version 1.0
 * */
public class ClientFactory
{
  private static ClientFactory clientFactory;
  public static ClientFactory getInstance()
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
