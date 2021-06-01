package client.view.myreservations;

import client.model.reservation.ReservationModelClient;
import client.model.user.UserModelClient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.reservation.Reservation;
import shared.util.EventTypes;

/**
 * View model for the borrower reservation
 *
 * @author Lilian
 * @version 1.0
 */
public class MyReservationsVM
{
  private ObservableList<Reservation> activeReservations;

  private ObjectProperty<Reservation> reservationProperty;
  private StringProperty cprProperty;

  private ReservationModelClient reservationModel;
  private UserModelClient userModel;

  public MyReservationsVM(ReservationModelClient reservationModel,
      UserModelClient userModel)
  {
    this.reservationModel = reservationModel;
    this.userModel = userModel;
    activeReservations = FXCollections.observableArrayList();
    cprProperty = new SimpleStringProperty(userModel.getLoginUser().getCpr());
    if (reservationModel.getAllReservationsByCPR(cprProperty.get()) != null)
    {
      activeReservations
          .addAll(reservationModel.getAllReservationsByCPR(cprProperty.get()));
    }
    reservationModel
        /*Listens to for the RESERVATIONREGISTER and RESERVATION event that is specific to the borrowers cpr
         * To ensure that other users Loan events won't affect the specific users window. */
        .addPropertyChangeListener(EventTypes.RESERVATIONREGISTERED, evt -> {
          if (((Reservation) evt.getNewValue()).getBorrower().getCpr()
              .equals(cprProperty.get()))
          {
            activeReservations.add((Reservation) evt.getNewValue());
            System.out.println("RESERVATION REGISTERED CAUGHT");
          }
        });
    reservationProperty = new SimpleObjectProperty<>();
  }

  public ObservableList<Reservation> getReservationList()
  {
    return activeReservations;
  }

  public ObjectProperty<Reservation> reservationProperty()
  {
    return reservationProperty;
  }

  public void endReservation(Reservation reservation)
  {
    reservationModel.endReservation(reservation);
  }
}
