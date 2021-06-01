package client.model.reservation;

import shared.reservation.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;
import shared.network.PropertyChangeSubject;

import java.util.List;

/**
 * Reservation model for client
 *
 * @author Lilian
 * @version 1.0
 */
public interface ReservationModelClient extends PropertyChangeSubject
{
  /**
   * Registers a new Reservation in the system for a given Material and Borrower.
   * The Reservation is made for a non-specific copy of the Material.
   * A reservation can only be made, if the Material has none available copies.
   *
   * @param material The material which the Borrower intends to make a Reservation for.
   * @param borrower The borrower (The owner) of the Reservation.
   * @throws IllegalStateException if the Material has more than 1 available copies
   */
  void registerReservation(Material material, Borrower borrower);

  /**
   * Gets all the reservations for the given cpr-number
   *
   * @param cpr The borrower's cpr number
   * @return a list of reservations
   */
  List<Reservation> getAllReservationsByCPR(String cpr);

  /**
   * Terminates a reservation
   *
   * @param reservation The selected reservation to be deleted
   */
  void endReservation(Reservation reservation);
}
