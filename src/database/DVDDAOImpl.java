package database;

import client.model.material.DVD;
import client.model.material.audio.CD;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DVDDAOImpl extends BaseDAO implements DVDDAO{
    private static DVDDAO instance;
    private static final Lock lock = new ReentrantLock();

    public static DVDDAO getInstance()
    {
        //Double lock check for Thread safety
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new DVDDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato, String undertitelsprog, int spillelængde, String genre) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO DVD (materialeid, titel, maalgruppe, beskrivelseAfIndholdet, emneord, forlag, sprog, udgivelsesDato, undertitelsprog, spillelængde, genre) values (?,?,?,?,?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, materialeid);
            stm.setString(2, titel);
            stm.setString(3, maalgruppe);
            stm.setString(4, beskrivelseafindholdet);
            stm.setString(5, emneord);
            stm.setString(6, forlag);
            stm.setString(7, sprog);
            stm.setDate(8, Date.valueOf(udgivelsesdato));
            stm.setString(9, undertitelsprog);
            stm.setInt(10, spillelængde);
            stm.setString(11, genre);

            stm.executeUpdate();
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            connection.commit();
            return keys.getInt(1);
        }
    }

    @Override
    public DVD createDVDCopy(int materialID, int copyNo) throws SQLException {
        try (Connection connection = getConnection())
        {
            //Creates material_copy
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO material_copy (material_id, copy_no) VALUES (?,?)");
            stm.setInt(1, materialID);
            stm.setInt(2, copyNo);
            stm.executeUpdate();
            connection.commit();

            //Finds the necessary details to create the DVD object from DB.
            ResultSet DVDDetails = getDVDDetailsByID(materialID);
            if (DVDDetails.next())
            {
                //Creates and returns a DVD object if a DVD with given materialID exists.
                return new DVD(DVDDetails.getInt("material_id"),
                        DVDDetails.getInt("copy_no"),
                        DVDDetails.getString("title"),
                        DVDDetails.getString("publisher"),
                        String.valueOf(DVDDetails.getDate("release_date")),
                        DVDDetails.getString("description_of_the_content"),
                        DVDDetails.getString("keywords"),
                        DVDDetails.getString("audience"),
                        DVDDetails.getString("language_"),
                        DVDDetails.getString("subtitlesLanguage"),
                        DVDDetails.getDouble("playDuration"),
                        DVDDetails.getInt("place_id"));
                // i removed the creator from here and i added place_id
            }
            return null;
        }
    }

    @Override
    public ResultSet getDVDDetailsByID(int materialID) throws SQLException, NoSuchElementException {
        try (Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM material join material_copy USING (material_id) JOIN DVD using (material_id) where material_id = ?");
            stm.setInt(1, materialID);
            ResultSet result = stm.executeQuery();
            if (result.next())
            {
                return result;
            }
            else
                throw new NoSuchElementException(
                        "No DVD with materialID " + materialID + " exists.");
        }
    }

}
