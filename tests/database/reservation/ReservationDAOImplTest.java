package database.reservation;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.loan.Loan;
import shared.materials.reading.Book;
import shared.person.borrower.Borrower;
import shared.reservation.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDAOImplTest
{
  ReservationDAO reservationDAO;
  DatabaseBuilder databaseBuilder;
  Borrower borrower;
  Book book;

  @BeforeEach void setUP() throws SQLException
  {
    reservationDAO = ReservationDAOImpl.getInstance();
    databaseBuilder = new DatabaseBuilder();
    borrower = new Borrower("111111-1122","Lilian","Bittar","bittarlily@gmail.com"
    ,"+4526700792", null, "password");
    book = new Book(1,4,"one","catman","2000-10-10","cat live"
    ,null,"Voksen","Dansk","5444",200,null,null );
  }

  @Test void creatReservationTest() throws SQLException
  {
     databaseBuilder.insertDummyWithOutReservationInfo();
    assertDoesNotThrow(() -> {
      reservationDAO.create(borrower, book);
    });
    assertEquals(1, reservationDAO.getAllReservationsByCPR(borrower.getCpr()).size());
  }

  @Test void getAllReservationByCPRReturnsAllReservationsTest() throws SQLException
  {
    //Creats DB with 1 borrower, 1 reservation and 1 book with 1 copy

    databaseBuilder.insertDummyReservationInfo();
    List<Reservation> reservations = reservationDAO.getAllReservationsByCPR("111111-1122");
    assertEquals(2, reservations.size());
  }

  @Test void getAllReservationsByCPRReturnsCorrectReservation() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    databaseBuilder.insertDummyReservationInfo();
    List<Reservation> reservations = reservationDAO.getAllReservationsByCPR("111111-1122");
    Reservation reservation = reservations.get(0);
    assertEquals("Book", reservation.getMaterial().getMaterialType());
    assertEquals(1,reservation.getReservationID());
    assertEquals(1,reservation.getMaterial().getMaterialID());
    assertEquals(4,reservation.getMaterial().getCopyNumber());
    assertEquals("111111-1122", reservation.getBorrower().getCpr());
    Reservation reservation1 = reservations.get(1);
    assertEquals("Book", reservation1.getMaterial().getMaterialType());
    assertEquals(2,reservation1.getReservationID());
    assertEquals(2,reservation1.getMaterial().getMaterialID());
    assertEquals(4,reservation1.getMaterial().getCopyNumber());
    assertEquals("111111-1122", reservation1.getBorrower().getCpr());

  }

  @Test void getAllReservationsByCPRNoReservationsThrowsNoSuchElementException()
      throws SQLException
  {
    databaseBuilder.insertDummyWithOutReservationInfo();
    assertThrows(NoSuchElementException.class,
        () -> reservationDAO.getAllReservationsByCPR("111111-1122"));
  }

  @Test void endReservationTest() throws SQLException
  {
    databaseBuilder.insertDummyReservationInfo();
    assertEquals(2,reservationDAO.getAllReservationsByCPR(borrower.getCpr()).size());
    Reservation reservation = new Reservation(book, borrower, LocalDate.now(),1,false);
    assertDoesNotThrow(() -> reservationDAO.endReservation(reservation));
    assertEquals(1,reservationDAO.getAllReservationsByCPR(borrower.getCpr()).size());
  }

  @Test void hasReservationsTest() throws SQLException
  {
    databaseBuilder.insertDummyReservationInfo();
    assertEquals(true,reservationDAO.hasReservations(book.getMaterialID()));
  }

}