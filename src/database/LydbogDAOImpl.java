package database;

import client.model.material.audio.AudioBook;
import client.model.material.audio.CD;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LydbogDAOImpl extends BaseDAO implements LydbogDAO{

    private static LydbogDAO instance;
    private static final Lock lock = new ReentrantLock();

    public static LydbogDAO getInstance()
    {
        //Double lock check for Thread safety
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new LydbogDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato, int spillelængde,String genre) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO Bog (materialeid, titel, maalgruppe, beskrivelseAfIndholdet, emneord, forlag, sprog, udgivelsesDato, spillelængde, genre) values (?,?,?,?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, materialeid);
            stm.setString(2, titel);
            stm.setString(3, maalgruppe);
            stm.setString(4, beskrivelseafindholdet);
            stm.setString(5, emneord);
            stm.setString(6, forlag);
            stm.setString(7, sprog);
            stm.setDate(8, Date.valueOf(udgivelsesdato));
            stm.setInt(9, spillelængde);
            stm.setString(10, genre);

            stm.executeUpdate();
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            connection.commit();
            return keys.getInt(1);
        }
    }

    @Override
    public AudioBook createAudioBookCopy(int materialID, int copyNo) throws SQLException {
        try (Connection connection = getConnection())
        {
            //Creates material_copy
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO material_copy (material_id, copy_no) VALUES (?,?)");
            stm.setInt(1, materialID);
            stm.setInt(2, copyNo);
            stm.executeUpdate();
            connection.commit();

            //Finds the necessary details to create the AudioBook object from DB.
            ResultSet AudioBookDetails = getAudioBookDetailsByID(materialID);
            if (AudioBookDetails.next())
            {
                //Creates and returns a AudioBook object if a AudioBook with given materialID exists.
                return new AudioBook(AudioBookDetails.getInt("material_id"),
                        AudioBookDetails.getInt("copy_no"),
                        AudioBookDetails.getString("title"),
                        AudioBookDetails.getString("publisher"),
                        String.valueOf(AudioBookDetails.getDate("release_date")),
                        AudioBookDetails.getString("description_of_the_content"),
                        AudioBookDetails.getString("keywords"),
                        AudioBookDetails.getString("audience"),
                        AudioBookDetails.getString("language_"),
                        AudioBookDetails.getDouble("playDuration"));
            }
            return null;
        }
    }

    @Override
    public ResultSet getAudioBookDetailsByID(int materialID) throws SQLException, NoSuchElementException {
        try (Connection connection = getConnection()) {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM material join material_copy USING (material_id) JOIN AudioBook using (material_id) where material_id = ?");
            stm.setInt(1, materialID);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                return result;
            } else
                throw new NoSuchElementException(
                        "No AudioBook with materialID " + materialID + " exists.");
        }
    }
}
