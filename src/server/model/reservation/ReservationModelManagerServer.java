package server.model.reservation;

import database.loan.ReservationDAOImpl;
import database.material.MaterialDAO;
import database.material.MaterialDAOImpl;
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
    if (MaterialDAOImpl.getInstance().getNumberOfAvailableCopies(material.getMaterialID()) < 0)
    {
      Reservation reservation = ReservationDAOImpl
          .getInstance().create(borrower, material);
      //Event is fired and caught in LoanServer. LoanSever redirects the event to the client using the Client Callback.
      support.firePropertyChange(EventTypes.RESERVATIONREGISTERED, null, reservation);
    }
    else
      throw new IllegalStateException("Material has more than 1 available copies");
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
