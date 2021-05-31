package server.network;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.Server;

import static org.junit.jupiter.api.Assertions.*;

//Michael
class ServerImplTest
{
  private Server server;
  @BeforeEach void setup()
  {
    server = new ServerImpl();
  }

  @Test void getUserServerTest()
  {
    assertDoesNotThrow(()->{server.getUserServer();});
  }

  @Test void getLoanServerTest()
  {
    assertDoesNotThrow(() -> {server.getLoanServer();});
  }
}