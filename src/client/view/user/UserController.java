package client.view.user;

import client.view.ViewHandler;
import javafx.event.ActionEvent;

import java.io.IOException;

public class UserController
{

  public void init(ViewHandler viewHandler)
  {
    viewHandler = ViewHandler.getInstance();
  }

  public void onButtonLoanReserve(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Search");
  }

  public void OnButtonMyMaterial(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("MyMaterial");
  }

  public void onButtonChatRoom(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Chat");
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
