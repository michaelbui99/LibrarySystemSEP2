package database.loan;

import shared.loan.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.sql.SQLException;

public interface ReservationDAO
{
  Reservation create(Borrower borrower, Material material);
}
