package client.view.myreservations;

import client.view.ViewHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.materials.Material;
import shared.reservation.Reservation;

import java.io.IOException;

/**
 * Controller for the borrower reservation
 *
 * @author Lilian
 * @version 1.0
 */
public class MyReservationsController
{

  @FXML private Label noReservationsLabel;
  @FXML private TableView<Reservation> reservationTableView;
  @FXML private TableColumn<String, Material> materialColumn;
  @FXML private TableColumn<String, Reservation> reservationDateColumn;
  @FXML private TableColumn<String, Reservation> readyForPickupColumn;

  private MyReservationsVM myReservationsVM;

  public void init(MyReservationsVM myLoansVM)
  {
    this.myReservationsVM = myLoansVM;

    materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
    reservationDateColumn
        .setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
    readyForPickupColumn
        .setCellValueFactory(new PropertyValueFactory<>("readyForPickup"));
    ObservableList<Reservation> activeReservations = myReservationsVM
        .getReservationList();
    reservationTableView.setItems(activeReservations);
    noReservationsLabel.setVisible(activeReservations.size() == 0);

  }

  @FXML void onReturnButton(ActionEvent event)
  {

    myReservationsVM.reservationProperty()
        .set(reservationTableView.getSelectionModel().getSelectedItem());
    reservationTableView.refresh();
  }

  @FXML void onEndReservationButton(ActionEvent event)
  {
    Reservation selectedReservation = this.reservationTableView
        .getSelectionModel().getSelectedItem();
    if (selectedReservation != null)
    {
      myReservationsVM.endReservation(selectedReservation);
      ObservableList<Reservation> reservations = reservationTableView
          .getItems();
      for (int i = 0; i < reservations.size(); i++)
      {
        if (reservations.get(i).equals(selectedReservation))
        {
          reservations.remove(i);
        }
      }
      reservationTableView.setItems(reservations);
    }
  }

  @FXML void onBackButton(ActionEvent event)
  {
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
