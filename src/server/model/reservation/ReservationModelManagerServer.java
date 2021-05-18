package server.model.reservation;

import database.loan.ReservationDAOImpl;
import server.core.ModelFactoryServer;
import shared.loan.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ReservationModelManagerServer implements ReservationModelServer
{
  private PropertyChangeSupport support;

  public ReservationModelManagerServer()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void registerReservation(Material material,
      Borrower borrower)
  {
      Reservation reservation = ReservationDAOImpl
          .getInstance().create(borrower, material);
      //Event is fired and caught in LoanServer. LoanSever redirects the event to the client using the Client Callback.
      support.firePropertyChange(EventTypes.RESERVATIONREGISTERED, null, reservation);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
