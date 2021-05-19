package database.loan;

import database.BaseDAO;
import shared.loan.Reservation;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.sql.*;
import java.time.LocalDate;

public class ReservationDAOImpl extends BaseDAO implements ReservationDAO
{
  private static ReservationDAO instance;

  public static ReservationDAO getInstance()
  {
    if (instance == null)
    {
      instance = new ReservationDAOImpl();
    }
    return instance;
  }

  private ReservationDAOImpl()
  {

  }

  @Override public Reservation create(Borrower borrower, Material material)

  {
    try
    {
      try(Connection connection = getConnection())
      {
        //todo: lige nu reserverer man en specific kopy. Evt. tilføje materiale + materiale kopi som java object, så man skelner mellem dem?
        //todo:
        LocalDate today = LocalDate.now();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO reservation (material_id, cpr_no, reservation_date, ready) values (?,?,?,false)", Statement.RETURN_GENERATED_KEYS);
        stm.setInt(1, material.getMaterialID());
        stm.setString(2, borrower.getCpr());
        stm.setDate(3,Date.valueOf(today));
        stm.executeUpdate();
        ResultSet keys = stm.getGeneratedKeys();
        keys.next();
        connection.commit();
        return new Reservation(material, borrower, today, keys.getInt(1), false);
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }
}
