package client.view.borrower;

import client.view.ViewHandler;
import javafx.event.ActionEvent;

import java.io.IOException;

//Kutaiba
public class BorrowerWindowController
{
  private BorrowerWindowVM borrowerWindowVM;

  public void init(BorrowerWindowVM borrowerWindowVM)
  {
    this.borrowerWindowVM = borrowerWindowVM;
  }

  public void onButtonLoanReserve(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Search");
  }

  public void OnButtonMyMaterial(ActionEvent actionEvent) throws IOException
  {
    //    ViewModelFactory.getInstance().getUserVM().setBorrowerCPR();
    ViewHandler.getInstance().openView("MyLoans");
  }

  public void onButtonChatRoom(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Chat");
  }

  public void onButtonViewReservations(ActionEvent actionEvent)
      throws IOException
  {
    ViewHandler.getInstance().openView("MyReservations");
  }

  public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  public void onButtonLogOut(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Main");
  }
}
