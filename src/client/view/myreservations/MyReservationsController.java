package client.view.myreservations;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import client.view.mymaterial.MyMaterialVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.loan.Loan;
import shared.loan.Reservation;
import shared.materials.Material;

import java.io.IOException;

public class MyReservationsController
{

  @FXML private Label selectedReservationLabel;
  @FXML private TableView<Reservation> reservationTableView;

  @FXML private TableColumn<String, Material> materialColumn;

  @FXML private TableColumn<String, Reservation> reservationDateColumn;

  private ViewHandler viewHandler;
  private MyMaterialVM viewModel;

  public void init()
  {
    //TODO: implement toString for Material such that material can be displayed correctly in tableview.
    materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
    reservationDateColumn.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
    reservationTableView.setItems(
        ViewModelFactory.getInstance().getMyReservationsVM().getReservationList());
  }

  @FXML void onReturnButton(ActionEvent event)
  {
    //TODO: implement this
    //Sets the binded textproperty to the loanId of the selected loan.
    //    selectedLoanLabel.textProperty().setValue(String.valueOf(
    //        loanTableView.getSelectionModel().getSelectedItem().getLoanID()));
    ViewModelFactory.getInstance().getMyReservationsVM().reservationProperty()
        .set(reservationTableView.getSelectionModel().getSelectedItem());
    ViewModelFactory.getInstance().getMyReservationsVM().endReservation();
    reservationTableView.refresh();
  }


  @FXML
  void onBackButton(ActionEvent event) {
    try
    {
      ViewHandler.getInstance().openView("UserWindow");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
