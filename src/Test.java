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
        .registerBook(1, 31, "Hello", "TEST Publisher!", "1999-09-14", "hello",
            "Helolo", "Voksen", "Dansk", "TEST-TEST", 200);


    rmiServer.registerLoan(model.searchMaterial("1"), "111111-1111", "1999-12-12");
  }
}
