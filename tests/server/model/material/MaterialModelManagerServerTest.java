package server.model.material;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.materials.Place;

import static org.junit.jupiter.api.Assertions.*;

class MaterialModelManagerServerTest
{
  private MaterialModelServer materialModelServer;

  @BeforeEach void setup()
  {
    materialModelServer = new MaterialModelManagerServer();
  }

  @Test void addDVDTest()

  {
    assertDoesNotThrow(() -> materialModelServer
        .registerDVD("test", "test", "2004-01-01", "test", "test", "Voksen",
            "Dansk", "test", 100, new Place(1, "D", "test", "test"), "test",
            null));
  }
}