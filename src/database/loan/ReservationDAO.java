package database.loan;

import shared.loan.Loan;
import shared.loan.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface ReservationDAO
{

  Reservation create(Borrower borrower, Material material) throws IllegalStateException,
      NoSuchElementException;
  List<Reservation> getAllReservationsByCPR(String cpr);
  void endReservation(Reservation reservation);
}
