package client.network;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.ServerImpl;
import shared.Server;
import shared.network.ServerConnectionException;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest
{
  private Client client;
  @BeforeEach void setup()
  {
    client = new RMIClientImpl();
  }

  @Test void startClientServerNotStartedThrowsServerConnectionException()
  {
    assertThrows(ServerConnectionException.class, ()->client.startClient());
  }

  @Test void startClientServerStartedDoesNotThrow()
      throws AlreadyBoundException, RemoteException
  {
    Server server = new ServerImpl();
    server.start();
    assertDoesNotThrow(()->client.startClient());
  }


}