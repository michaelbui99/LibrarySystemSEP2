package client.view.myreservation;

import client.core.ModelFactoryClient;
import client.view.myreservations.MyReservationsVM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.person.borrower.Borrower;

public class MyReservationsVMTest
{
  private MyReservationsVM viewmodel;
  Borrower borrower;

  @BeforeEach void setup()
  {
    borrower = new Borrower("111111-1122","Lilian", "Bittar","bittarlily@gmail.com",
        "+4526700792", null,"password");
    viewmodel = new MyReservationsVM(ModelFactoryClient.getInstance().getReservationModelClient(),
        ModelFactoryClient.getInstance().getUserModelClient());
  }
  @Test public void getReservationListTest(){

  }
}
