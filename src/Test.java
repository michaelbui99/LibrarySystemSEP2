import server.model.LibraryModel;
import server.model.LibraryModelManager;
import server.network.RMIServerImpl;
import shared.RMIServer;

import java.rmi.RemoteException;

public class Test
{
  public static void main(String[] args) throws RemoteException
  {
    LibraryModel model = new LibraryModelManager();
    RMIServer rmiServer = new RMIServerImpl(model);
    rmiServer
        .registerBook(1, 1, "Hello", "TEST Publisher!", "1999-09-14", "hello",
            "Helolo", "Voksen", "Dansk", "DSADSA-TEST", 200);
  }
}
