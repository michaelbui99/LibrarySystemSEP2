package client.network;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RMIClientImplTest
{
  private Client client;
  @BeforeEach void setup()
  {
    client = new RMIClientImpl();
  }

  @Test void getUserServerDoesNotThrowTest()
  {
    assertDoesNotThrow(()->client.borrowerLogin("111111-1111", "password"));
    assertTrue(client.borrowerLogin("111111-1111", "password"));
  }
}