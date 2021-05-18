package client.view.user;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class UserController
{
  @FXML
  private Label cprLabel;

  public void init()
  {
    /*cprLabel.textProperty().bind(ViewModelFactory.getInstance().getUserVM()
        .cprPropertyProperty());*/
  }

  public void onButtonLoanReserve(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Search");
  }

  public void OnButtonMyMaterial(ActionEvent actionEvent) throws IOException
  {
//    ViewModelFactory.getInstance().getUserVM().setBorrowerCPR();
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
