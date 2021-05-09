package client.view.user;

import client.core.ViewHandler;
import javafx.event.ActionEvent;

public class UserController
{
private ViewHandler viewHandler;

  public UserController(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
  }

  public void onButtonLoanReserve(ActionEvent actionEvent)
  {
    //TODO open loan/reserve window//
  }

  public void OnButtonMyMaterial(ActionEvent actionEvent)
  {
    //TODO open my material window//
  }

  public void onButtonChatRoom(ActionEvent actionEvent)
  {
    //TODO open the chat room view window//
  }

  public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  public void onButtonLogOut(ActionEvent actionEvent)
  {
    //TODO open the main window//
  }
}
