package database.loan;

import database.BaseDAO;
import database.material.MaterialDAOImpl;
import shared.loan.Reservation;
import shared.materials.DVD;
import shared.materials.Material;
import shared.materials.Place;
import shared.materials.audio.AudioBook;
import shared.materials.audio.CD;
import shared.materials.reading.Book;
import shared.materials.reading.EBook;
import shared.person.Address;
import shared.person.MaterialCreator;
import shared.person.borrower.Borrower;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
  private boolean canReserve(Borrower borrower, Material material){
    try(Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement("select * from reservation where material_id = ? and cpr_no = ? ");
      stm.setInt(1, material.getMaterialID());
      stm.setString(2, borrower.getCpr());
      ResultSet resultSet = stm.executeQuery();
      if (!resultSet.next()){
        return true;
      }
      else
        return false;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return false;
  }
  // call the method in materialDAo
  private int getCopyNoForMaterial(Material material){
    try(Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement("select * from material_copy where material_id = ? ");
      stm.setInt(1, material.getMaterialID());
      ResultSet resultSet = stm.executeQuery();
      if (resultSet.next()){
        return resultSet.getInt("copy_no");
      }
      else
        return 0;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return 0;
  }

  @Override public Reservation create(Borrower borrower, Material material)

  {
    try(Connection connection = getConnection())
    {
      //todo: lige nu reserverer man en specific kopy. Evt. tilføje materiale + materiale kopi som java object, så man skelner mellem dem?
      //todo: lav et check på om der allerede findes en reservation borrower CPR og MaterialID
      if (!MaterialDAOImpl.getInstance().checkIfCopyAvailable(material.getMaterialID()) && canReserve(borrower,material))
      {
        LocalDate today = LocalDate.now();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO reservation (material_id, cpr_no, reservation_date) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
        stm.setInt(1, material.getMaterialID());
        stm.setString(2, borrower.getCpr());
        stm.setDate(3,Date.valueOf(today));
//        stm.setInt(4, getCopyNoForMaterial(material));
        stm.executeUpdate();
        connection.commit();
        ResultSet keys = stm.getGeneratedKeys();
        keys.next();
        return new Reservation(material, borrower, today, keys.getInt("reservation_id"), false);
      }
      else
        throw new IllegalStateException("Du har allerede reserveret dette materiale");
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public List<Reservation> getAllReservationsByCPR(String cpr)
  {
    String[] materialTypes = new String[]{"book", "audiobook", "e_book", "cd", "dvd"};
    try (Connection connection = getConnection())
    {
      PreparedStatement selectBorrowerAddress = connection.prepareStatement(
          "SELECT * from borrower join address using (address_id) where cpr_no = ?");
      selectBorrowerAddress.setString(1, cpr);
      ResultSet selectBorrowerAddressResult = selectBorrowerAddress
          .executeQuery();
      selectBorrowerAddressResult.next();
      Address address = new Address(
          selectBorrowerAddressResult.getInt("address_id"),
          selectBorrowerAddressResult.getString("street_name"),
          selectBorrowerAddressResult.getString("street_no"),
          selectBorrowerAddressResult.getInt("zip_code"),
          selectBorrowerAddressResult.getString("city"));
      Borrower borrower = new Borrower(cpr,
          selectBorrowerAddressResult.getString("f_name"),
          selectBorrowerAddressResult.getString("l_name"),
          selectBorrowerAddressResult.getString("email"),
          selectBorrowerAddressResult.getString("tel_no"), address,
          selectBorrowerAddressResult.getString("password"));

      List<Reservation> reservations = new ArrayList<>();
      for(int i = 0; i < materialTypes.length; i++){
        String query ="SELECT * FROM reservation "
            + "JOIN material_copy ON reservation.material_id = material_copy.material_id "
            + "JOIN " + materialTypes[i] + " ON reservation.material_id = " + materialTypes[i] + ".material_id "
            + "JOIN material ON reservation.material_id = material.material_id ";
        if(!materialTypes[i].equals("audiobook") && !materialTypes[i].equals("e_book")){
          query += " JOIN place ON " + materialTypes[i] + ".place_id = place.place_id ";
        }
        if(!materialTypes[i].equals("cd") && !materialTypes[i].equals("dvd")){
          query += " JOIN material_creator ON " + materialTypes[i] + ".author = material_creator.person_id ";
        }
        query += " WHERE reservation.cpr_no = '" + cpr + "' AND reservation.copy_no = material_copy.copy_no";
        PreparedStatement selectReservations = connection.prepareStatement(query);
        ResultSet selectReservationsResult = selectReservations.executeQuery();
        while(selectReservationsResult.next()){
          Material material = null;
          switch (materialTypes[i]){
            case "book":
              material = new Book(selectReservationsResult.getInt("material_id"),
                  selectReservationsResult.getInt("copy_no"), selectReservationsResult.getString("title"),
                  selectReservationsResult.getString("publisher"),
                  String.valueOf(selectReservationsResult.getDate("release_date")),
                  selectReservationsResult.getString("description_of_the_content"), null,
                  selectReservationsResult.getString("audience"), selectReservationsResult.getString("language_"),
                  selectReservationsResult.getString("isbn"), selectReservationsResult.getInt("page_no"),
                  new Place(selectReservationsResult.getInt("place_id"),
                      selectReservationsResult.getInt("hall_no"),
                      selectReservationsResult.getString("department"),
                      selectReservationsResult.getString("creator_l_name"),
                      selectReservationsResult.getString("genre")),
                  new MaterialCreator(selectReservationsResult.getInt("person_id"),
                      selectReservationsResult.getString(28), selectReservationsResult.getString(29),
                      String.valueOf(selectReservationsResult.getDate("dob")),
                      selectReservationsResult.getString("country")));
              break;
            case "audiobook":
              material = new AudioBook(
                  selectReservationsResult.getInt("material_id"),
                  selectReservationsResult.getInt("copy_no"),
                  selectReservationsResult.getString("title"),
                  selectReservationsResult.getString("publisher"),
                  String.valueOf(selectReservationsResult.getDate("release_date")),
                  selectReservationsResult.getString("description_of_the_content"), null,
                  selectReservationsResult.getString("audience"),
                  selectReservationsResult.getString("language_"),
                  selectReservationsResult.getInt("length_"),
                  new MaterialCreator(selectReservationsResult.getInt("person_id"),
                      selectReservationsResult.getString(26), selectReservationsResult.getString(27),
                      String.valueOf(selectReservationsResult.getDate("dob")),
                      selectReservationsResult.getString("city")),
                  selectReservationsResult.getString("url"));
              break;
            case "dvd":
              material = new DVD(selectReservationsResult.getInt("material_id"),
                  selectReservationsResult.getInt("copy_no"), selectReservationsResult.getString("title"),
                  selectReservationsResult.getString("publisher"),
                  String.valueOf(selectReservationsResult.getDate("release_date")),
                  selectReservationsResult.getString("description_of_the_content"), null,
                  selectReservationsResult.getString("audience"), selectReservationsResult.getString("language_"),
                  selectReservationsResult.getString("subtitle_lang"),
                  String.valueOf(selectReservationsResult.getInt("length_")),
                  new Place(selectReservationsResult.getInt("place_id"), selectReservationsResult.getInt("hall_no"),
                      selectReservationsResult.getString("department"),
                      selectReservationsResult.getString("creator_l_name"),
                      selectReservationsResult.getString("genre")), selectReservationsResult.getString("URL"));
              break;
            case "cd":
              material = new CD(selectReservationsResult.getInt("material_id"),
                  selectReservationsResult.getInt("copy_no"), selectReservationsResult.getString("title"),
                  selectReservationsResult.getString("publisher"),
                  String.valueOf(selectReservationsResult.getDate("release_date")),
                  selectReservationsResult.getString("description_of_the_content"), null,
                  selectReservationsResult.getString("audience"), selectReservationsResult.getString("language_"),
                  selectReservationsResult.getInt("length_"),
                  new Place(selectReservationsResult.getInt("place_id"), selectReservationsResult.getInt("hall_no"),
                      selectReservationsResult.getString("department"),
                      selectReservationsResult.getString("creator_l_name"),
                      selectReservationsResult.getString("genre")), selectReservationsResult.getString("url"));
              break;
            case "e_book":
              material = new EBook(selectReservationsResult.getInt("material_id"),
                  selectReservationsResult.getInt("copy_no"), selectReservationsResult.getString("title"),
                  selectReservationsResult.getString("publisher"),
                  String.valueOf(selectReservationsResult.getDate("release_date")),
                  selectReservationsResult.getString("description_of_the_content"), null,
                  selectReservationsResult.getString("audience"), selectReservationsResult.getString("language_"),
                  selectReservationsResult.getInt("page_no"), selectReservationsResult.getString("license_no"),
                  selectReservationsResult.getString("genre"),
                  new MaterialCreator(selectReservationsResult.getInt("person_id"),
                      selectReservationsResult.getString(26), selectReservationsResult.getString(27),
                      String.valueOf(selectReservationsResult.getDate("dob")),
                      selectReservationsResult.getString("city")));
              break;
          }

          Reservation reservation = new Reservation(
              material,
              borrower,
              selectReservationsResult.getDate("reservation_date").toLocalDate(),
              selectReservationsResult.getInt("reservation_id"),
              selectReservationsResult.getBoolean("available")
          );
          reservations.add(reservation);
        }

      }


      if (!reservations.isEmpty())
      {
        return reservations;
      }
      else
        throw new NoSuchElementException("Ingen aktive reservationer");
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    } return null;
  }

  @Override public void endReservation(Reservation reservation)
  {
    try(Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement("DELETE FROM reservation WHERE reservation.reservation_id = ?", Statement.RETURN_GENERATED_KEYS);
      stm.setInt(1, reservation.getReservationID());
      stm.executeUpdate();
      connection.commit();
      ResultSet keys = stm.getGeneratedKeys();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }

  @Override public boolean hasReservations(int materialID)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stm = connection.prepareStatement("SELECT * from reservation where materialID = ? ");
      stm.setInt(1, materialID);
      ResultSet result = stm.executeQuery();

      return result.next();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return false;
  }

}
