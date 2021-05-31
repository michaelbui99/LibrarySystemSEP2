package server.model.reservation;

import shared.reservation.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.network.PropertyChangeSubject;

import java.util.List;
//Kutaiba-Lilian
public interface ReservationModelServer extends PropertyChangeSubject
{
  /**
   * Registers a new Reservation in the system for a given Material and Borrower.
   * The Reservation is made for a non-specific copy of the Material.
   * A reservation can only be made, if the Material has none available copies.
   * @param material material is The Material which the Borrower intends to make a Reservation for.
   * @param borrower borrower is the owner of the Reservation.
   * @throws  IllegalStateException if the Material has more than 1 available copies
   * */
  public void registerReservation(Material material, Borrower borrower) throws IllegalStateException;
  public void endReservation(Reservation reservation);
  List<Reservation> getAllReservationsByCPR(String cpr);
}
