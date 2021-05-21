package database.loan;

import shared.loan.Loan;
import shared.loan.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO
{

  Reservation create(Borrower borrower, Material material);
  List<Reservation> getAllReservationsByCPR(String cpr);
  void endReservation(Reservation reservation);
}
