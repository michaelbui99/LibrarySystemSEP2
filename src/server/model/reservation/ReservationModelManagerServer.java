package server.model.reservation;

import database.material.MaterialDAO;
import database.reservation.ReservationDAO;
import database.reservation.ReservationDAOImpl;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.reservation.Reservation;
import shared.util.EventTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.NoSuchElementException;

public class ReservationModelManagerServer implements ReservationModelServer
{
  private PropertyChangeSupport support;
  
  private ReservationDAO reservationDAO;
  private MaterialDAO materialDAO;
  

  public ReservationModelManagerServer(ReservationDAO reservationDAO, MaterialDAO materialDAO)
  {
    this.reservationDAO = reservationDAO;
    this.materialDAO = materialDAO;
    
    support = new PropertyChangeSupport(this);
  }

  @Override public void registerReservation(Material material,
      Borrower borrower) throws IllegalStateException
  {
    if (materialDAO.getNumberOfAvailableCopies(material.getMaterialID()) == 0)
    {
      Reservation reservation = ReservationDAOImpl
          .getInstance().create(borrower, material);
      //Event is fired and caught in ReservationServer. ReservationSever redirects the event to the client using the Client Callback.
      support.firePropertyChange(EventTypes.RESERVATIONREGISTERED, null, reservation);
    }
    else{
       throw new IllegalStateException("Flere tilgængelige kopier, materialet kan lån i stedet");
    }
  }

  @Override public void endReservation(Reservation reservation)
  {
    ReservationDAOImpl
        .getInstance().endReservation(reservation);

    support.firePropertyChange(EventTypes.RESERVATIONCANCELLED, null, reservation);

    //      throw new IllegalStateException("Material has more than 1 available copies");
  }

  @Override public List<Reservation> getAllReservationsByCPR(String cpr)
  {
    try
    {
      return reservationDAO.getAllReservationsByCPR(cpr);
    }
    catch (NoSuchElementException e)
    {
      return null;
    }
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
