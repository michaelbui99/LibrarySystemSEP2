package database.reservation;

import shared.reservation.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.util.List;
import java.util.NoSuchElementException;

public interface ReservationDAO
{

  Reservation create(Borrower borrower, Material material) throws IllegalStateException,
      NoSuchElementException;
  List<Reservation> getAllReservationsByCPR(String cpr);
  void endReservation(Reservation reservation);
  boolean hasReservations(int materialID);
  String getNextWaitingBorrowerCPRForMaterial(int materialID);
  boolean reservationIsReady(int reservationID);
  int getReservationIDByBorrowerMaterial(String cpr, int materialID);
}
