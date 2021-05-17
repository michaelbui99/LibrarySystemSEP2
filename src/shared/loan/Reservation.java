package shared.loan;

import shared.materials.Material;
import shared.person.borrower.Borrower;
//joda time library
import java.time.*;

public class Reservation
{
  private Material material;
  private Borrower borrower;
  private LocalDate reservationDate;
  private int reservationID;

  public Reservation(Material material, Borrower borrower, LocalDate reservationDate)
  {
    this.material = material;
    this.borrower = borrower;
    this.reservationDate = reservationDate;
  }

  public Reservation(Material material, Borrower borrower,
      LocalDate reservationDate, int reservationID)
  {
    this.material = material;
    this.borrower = borrower;
    this.reservationDate = reservationDate;
    this.reservationID = reservationID;
  }

  public Material getMaterial()
  {
    return material;
  }

  public Borrower getBorrower()
  {
    return borrower;
  }

  public LocalDate getReservationDate()
  {
    return reservationDate;
  }

  public int getReservationID()
  {
    return reservationID;
  }
}
