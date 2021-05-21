package shared.loan;

import shared.materials.Material;
import shared.person.borrower.Borrower;
//joda time library
import java.io.Serializable;
import java.time.*;

public class Reservation implements Serializable
{
  private Material material;
  private Borrower borrower;
  private LocalDate reservationDate;
  private int reservationID;
  private boolean readyForPickup;

  public Reservation(Material material, Borrower borrower, LocalDate reservationDate)
  {
    this.material = material;
    this.borrower = borrower;
    this.reservationDate = reservationDate;
  }

  public Reservation(Material material, Borrower borrower,
      LocalDate reservationDate, int reservationID, boolean readyForPickup)
  {
    this.material = material;
    this.borrower = borrower;
    this.reservationDate = reservationDate;
    this.reservationID = reservationID;
    this.readyForPickup = readyForPickup;
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
