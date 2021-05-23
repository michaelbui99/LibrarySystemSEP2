package client.view.mymaterial;

import client.core.ModelFactoryClient;
import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.ServerImpl;
import shared.Server;
import shared.person.borrower.Borrower;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MyMaterialVMTest extends DatabaseBuilder
{
   private MyMaterialVM viewModel;
  Borrower borrower;

  //TODO: Spørg om Unit tests på client side. Hvordan startes serveren?
   @BeforeEach void setup()
   {
      borrower = new Borrower("111111-1111", "Michael", "Bui",
          "michael@gmail.com", "+4512345678", null, "password");
     viewModel = new MyMaterialVM(ModelFactoryClient.getInstance()
         .getLoanModelClient(), ModelFactoryClient.getInstance()
         .getUserModelClient());
   }

}