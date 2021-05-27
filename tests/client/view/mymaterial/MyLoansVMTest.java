package client.view.mymaterial;

import client.core.ClientFactory;
import client.core.ModelFactoryClient;
import client.network.Client;
import client.view.myLoans.MyLoansVM;
import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.ServerImpl;
import shared.Server;
import shared.person.borrower.Borrower;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MyLoansVMTest extends DatabaseBuilder
{
   private MyLoansVM viewModel;
  Borrower borrower;
  private Client client;
  private Server server;
  private DatabaseBuilder databaseBuilder;

   @BeforeEach void setup()
   {
     client = (Client) ClientFactory.getInstance().getClient();
     Server server = new ServerImpl();
     Thread serverThread = new Thread(()-> {
       try
       {
         server.start();
       }
       catch (RemoteException | AlreadyBoundException e)
       {
         e.printStackTrace();
       }
     });
     serverThread.start();
     client.startClient();
      borrower = new Borrower("111111-1111", "Michael", "Bui",
          "michael@gmail.com", "+4512345678", null, "password");
      databaseBuilder = new DatabaseBuilder();
   }

   @Test void ViewModelIsInitialisedWithCorrectListOfLoans() throws SQLException
   {
     databaseBuilder.createDummyDatabaseDataWithLoan();
     ModelFactoryClient.getInstance().getUserModelClient().setBorrowerCpr(borrower.getCpr());

     viewModel = new MyLoansVM(ModelFactoryClient.getInstance()
         .getLoanModelClient(), ModelFactoryClient.getInstance()
         .getUserModelClient());
     assertEquals(1, viewModel.getLoanList().size());
   }



}