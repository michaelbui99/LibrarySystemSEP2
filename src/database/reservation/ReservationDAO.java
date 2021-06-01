package database.reservation;

import shared.reservation.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Reservation data access object
 *
 * @author Lilian
 * @author Michael
 * @version 1.0
 */
public interface ReservationDAO
{
  /**
   * Creates a new Reservation in the system for a given Material and Borrower.
   * The Reservation is made for a non-specific copy of the Material.
   * A reservation can only be made, if the Material has none available copies.
   *
   * @param material The material which the Borrower intends to make a Reservation for.
   * @param borrower The borrower (The owner) of the Reservation.
   * @throws IllegalStateException  if the Material has more than 1 available copies
   * @throws NoSuchElementException if there is no borrower or material matches the given argument
   */
  Reservation create(Borrower borrower, Material material)
      throws IllegalStateException, NoSuchElementException;

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

  /**
   * Chek if a specific material has an active reservation
   *
   * @param materialID The material's id
   * @return true if there is any active reservation for the given material id
   */
  boolean hasReservations(int materialID);

  /**
   * Gets the cpr-number for the next borrower in queue who reserved the material
   *
   * @param materialID The material's id
   * @return a string as the borrower's cpr-number
   */
  String getNextWaitingBorrowerCPRForMaterial(int materialID);

  /**
   * Check if the reservation is ready for loan using the reservation id
   *
   * @param reservationID The reservation's id
   * @return true if the reservation is ready for loan
   */
  boolean reservationIsReady(int reservationID);

  /**
   * Gets the reservation id based on the given borrower's cpr-number and the material's id
   *
   * @param cpr        The borrower's cpr-number
   * @param materialID The material's id
   * @return an Integer as the reservation id
   */
  int getReservationIDByBorrowerMaterial(String cpr, int materialID);
}
