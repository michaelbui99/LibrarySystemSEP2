package server.network.reservation;

import server.model.reservation.ReservationModelServer;
import shared.reservation.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.network.ClientCallback;
import shared.network.ReservationServer;
import shared.util.EventTypes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Reservation server implementation
 *
 * @author Lilian
 * @version 1.0
 */
public class ReservationServerImpl implements ReservationServer
{
  private ReservationModelServer reservationModel;

  public ReservationServerImpl(ReservationModelServer reservationModel)
  {
    try
    {
      this.reservationModel = reservationModel;
      UnicastRemoteObject.exportObject(this, 0);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void registerClientCallBack(ClientCallback client)
      throws RemoteException
  {
    PropertyChangeListener listenerReservationRegistered = new PropertyChangeListener()
    {
      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        try
        {
          client.reservationUpdate(evt);
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
          reservationModel.removePropertyChangeListener(this);
        }
      }
    };
    reservationModel.addPropertyChangeListener(EventTypes.RESERVATIONREGISTERED,
        listenerReservationRegistered);
    reservationModel.addPropertyChangeListener(EventTypes.RESERVATIONCANCELLED,
        listenerReservationRegistered);
  }

  @Override public List<Reservation> getAllReservationsByCPR(String cpr)
  {
    return reservationModel.getAllReservationsByCPR(cpr);
  }

  @Override public void endReservation(Reservation reservation)
      throws RemoteException
  {
    reservationModel.endReservation(reservation);
  }

  @Override public void registerReservation(Material material,
      Borrower borrower) throws RemoteException
  {
    reservationModel.registerReservation(material, borrower);
  }
}
