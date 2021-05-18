package client.model.reservation;

import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.servers.PropertyChangeSubject;

import java.util.NoSuchElementException;

public interface ReservationModelClient extends PropertyChangeSubject
{
  void registerReservation(Material material, Borrower borrower);
}
