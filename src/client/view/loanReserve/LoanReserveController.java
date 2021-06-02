package client.view.loanReserve;

import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Controller for loans and reservations
 *
 * @author Kasper
 * @author Michael
 * @author Lilian
 * @version 1.0
 */
public class LoanReserveController
{

  @FXML private TextArea materialInfo;
  @FXML private TextArea availNumber;
  @FXML ImageView materialImage;
  @FXML private Label warningLabel;
  @FXML private Label reservationError;

  private Image imageTest;

  private LoanReserveVM loanReserveVM;

  public void init(LoanReserveVM loanReserveVM) throws FileNotFoundException
  {
    this.loanReserveVM = loanReserveVM;

    availNumber.textProperty()
        .bind(loanReserveVM.getAvailNumberProp().asString());
    materialInfo.textProperty().bind(loanReserveVM.getMaterialInfoProp());
    warningLabel.textProperty().bind(loanReserveVM.warningPropertyProperty());
    reservationError.textProperty()
        .bind(loanReserveVM.reservationErrorProperty());
    try
    {
      if (loanReserveVM.getMaterialImageURL() != null)
      {
        imageTest = new Image(
            new FileInputStream(loanReserveVM.getMaterialImageURL()));
        materialImage.setImage(imageTest);
        System.out.println("Image set");
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }

  }

  @FXML void goBack(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Search");
  }

  @FXML void onLoanButton(ActionEvent event)
  {
    loanReserveVM.loanMaterial();
  }

  @FXML void onReserveButton(ActionEvent event)
  {
    loanReserveVM.reserveMaterial();
  }
}
