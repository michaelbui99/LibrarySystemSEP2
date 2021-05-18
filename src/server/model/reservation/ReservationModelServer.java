package server.model.reservation;

import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.servers.PropertyChangeSubject;

public interface ReservationModelServer extends PropertyChangeSubject
{
  public void registerReservation(Material material, Borrower borrower);

}
