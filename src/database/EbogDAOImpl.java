package database;

import client.model.material.audio.CD;
import client.model.material.reading.EBook;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EbogDAOImpl extends BaseDAO implements EbogDAO{
    private static EbogDAO instance;
    private static final Lock lock = new ReentrantLock();

    public static EbogDAO getInstance()
    {
        //Double lock check for Thread safety
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new EbogDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato, int sidetal, int licensnr , String genre, String forfatter) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO Bog (materialeid, titel, maalgruppe, beskrivelseAfIndholdet, emneord, forlag, sprog, udgivelsesDato, sidetal, licensnr, genre, forfatter) values (?,?,?,?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, materialeid);
            stm.setString(2, titel);
            stm.setString(3, maalgruppe);
            stm.setString(4, beskrivelseafindholdet);
            stm.setString(5, emneord);
            stm.setString(6, forlag);
            stm.setString(7, sprog);
            stm.setDate(8, Date.valueOf(udgivelsesdato));
            stm.setInt(9, sidetal);
            stm.setInt(10, licensnr);
            stm.setString(11, genre);
            stm.setString(12, forfatter);

            stm.executeUpdate();
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            connection.commit();
            return keys.getInt(1);
        }
    }

    @Override
    public EBook createEBookCopy(int materialID, int copyNo) throws SQLException {
        try (Connection connection = getConnection())
        {
            //Creates material_copy
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO material_copy (material_id, copy_no) VALUES (?,?)");
            stm.setInt(1, materialID);
            stm.setInt(2, copyNo);
            stm.executeUpdate();
            connection.commit();

            //Finds the necessary details to create the EBook object from DB.
            ResultSet eBookDetails = getEBookDetailsByID(materialID);
            if (eBookDetails.next())
            {
                //Creates and returns a EBook object if a EBook with given materialID exists.
                return new EBook(eBookDetails.getInt("material_id"),
                        eBookDetails.getInt("copy_no"),
                        eBookDetails.getString("title"),
                        eBookDetails.getString("publisher"),
                        String.valueOf(eBookDetails.getDate("release_date")),
                        eBookDetails.getString("description_of_the_content"),
                        eBookDetails.getString("keywords"),
                        eBookDetails.getString("audience"),
                        eBookDetails.getString("language_"),
                        eBookDetails.getString("ISBN"),
                        eBookDetails.getInt("pageCount"),
                        eBookDetails.getString("licensNo"),
                        eBookDetails.getString("author"),
                        eBookDetails.getString("genre"));
                // added author and genre
            }
            return null;
        }
    }

    @Override
    public ResultSet getEBookDetailsByID(int materialID) throws SQLException, NoSuchElementException {
        try (Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM material join material_copy USING (material_id) JOIN EBook using (material_id) where material_id = ?");
            stm.setInt(1, materialID);
            ResultSet result = stm.executeQuery();
            if (result.next())
            {
                return result;
            }
            else
                throw new NoSuchElementException(
                        "No EBook with materialID " + materialID + " exists.");
        }
    }


}
