package client.view.administration;

import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Controller for the administration window for the librarian
 *
 * @author Kutaiba
 * @version 1.0
 */
public class AdministrationController
{
  public void init()
  {
  }

  @FXML public void onButtonSignOut(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Main");
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }


  @FXML public void onButtonAddRemoveCopy(ActionEvent actionEvent)
      throws IOException
  {
    ViewHandler.getInstance().openView("Copies");
  }

  @FXML public void onButtonAddMaterial(ActionEvent actionEvent)
      throws IOException
  {
    ViewHandler.getInstance().openView("RegisterMaterial");
  }

  @FXML void onBorrowerContactButton(ActionEvent event) throws IOException
  {
    ViewHandler.getInstance().openView("BorrowerContactInfo");
  }
}
