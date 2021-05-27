package client.view.myreservations;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import client.view.myLoans.MyLoansVM;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.reservation.Reservation;
import shared.materials.Material;

import java.io.IOException;

public class MyReservationsController
{

  @FXML private Label selectedReservationLabel;
  @FXML private Label noReservationsLabel;
  @FXML private TableView<Reservation> reservationTableView;
  @FXML private TableColumn<String, Material> materialColumn;
  @FXML private TableColumn<String, Reservation> reservationDateColumn;
  @FXML private TableColumn<String, Reservation> readyForPickupColumn;

  private ViewHandler viewHandler;
  private MyLoansVM viewModel;

  public void init()
  {
    //TODO: implement toString for Material such that material can be displayed correctly in tableview.
    materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
    reservationDateColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
    readyForPickupColumn.setCellValueFactory(new PropertyValueFactory<>("readyForPickup"));
    ObservableList<Reservation> activeReservations = ViewModelFactory.getInstance().getMyReservationsVM().getReservationList();
    reservationTableView.setItems(activeReservations);
    if (activeReservations.size() == 0){
      noReservationsLabel.setVisible(true);
    }else{
      noReservationsLabel.setVisible(false);
    }

  }

  @FXML void onReturnButton(ActionEvent event)
  {

    ViewModelFactory.getInstance().getMyReservationsVM().reservationProperty()
        .set(reservationTableView.getSelectionModel().getSelectedItem());
    reservationTableView.refresh();
  }

  @FXML void onEndReservationButton(ActionEvent event)
  {
    Reservation selectedReservation = this.reservationTableView.getSelectionModel().getSelectedItem();
    if(selectedReservation != null){
      ViewModelFactory.getInstance().getMyReservationsVM().endReservation(selectedReservation);
      ObservableList<Reservation> reservations = reservationTableView.getItems();
      for (int i = 0; i < reservations.size(); i++)
      {
        if(reservations.get(i).equals(selectedReservation)){
          reservations.remove(i);
        }
      }
      reservationTableView.setItems(reservations);
    }
  }

  @FXML
  void onBackButton(ActionEvent event) {
    try
    {
      ViewHandler.getInstance().openView("BorrowerWindow");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
