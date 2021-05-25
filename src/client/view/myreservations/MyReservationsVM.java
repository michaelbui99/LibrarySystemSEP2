package client.view.myreservations;

import client.core.ModelFactoryClient;
import client.model.loan.LoanModelClient;
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

public class MyReservationsVM
{
  private ObservableList<Reservation> activeReservations;
  private ObjectProperty<Reservation> reservationProperty;
  private StringProperty cprProperty;
  private ReservationModelClient reservationModel;
  private UserModelClient userModel;

  public MyReservationsVM(ReservationModelClient reservationModel, UserModelClient userModel)
  {
    this.reservationModel = reservationModel;
    this.userModel = userModel;
    activeReservations = FXCollections.observableArrayList();
    cprProperty = new SimpleStringProperty(ModelFactoryClient.getInstance().getUserModelClient().getLoginUser().getCpr());
    if (ModelFactoryClient.getInstance().getReservationModelClient()
        .getAllReservationsByCPR(cprProperty.get()) != null)
    {
      activeReservations.addAll(ModelFactoryClient.getInstance().getReservationModelClient()
          .getAllReservationsByCPR(cprProperty.get()));
    }
    ModelFactoryClient.getInstance().getReservationModelClient()
        /*Listens to for the RESERVATIONREGISTER and RESERVATION event that is specific to the borrowers cpr
        * To ensure that other users Loan events won't affect the specific users window. */
        .addPropertyChangeListener(EventTypes.RESERVATIONREGISTERED,
            evt -> {
              if (((Reservation) evt.getNewValue()).getBorrower().getCpr().equals(cprProperty.get()))
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
    ModelFactoryClient.getInstance().getReservationModelClient().endReservation(reservation);
  };

}
