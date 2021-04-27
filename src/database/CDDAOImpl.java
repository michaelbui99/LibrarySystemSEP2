package database;

import client.model.material.audio.CD;
import client.model.material.reading.Book;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CDDAOImpl extends BaseDAO implements CDDAO{
    private static CDDAO instance;
    private static final Lock lock = new ReentrantLock();

    public static CDDAO getInstance()
    {
        //Double lock check for Thread safety
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new CDDAOImpl();
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
                    "INSERT INTO CD (materialeid, titel, maalgruppe, beskrivelseAfIndholdet, emneord, forlag, sprog, udgivelsesDato, spillelængde, genre) values (?,?,?,?,?,?,?,?,?,?)",
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
    public CD createCDCopy(int materialID, int copyNo) throws SQLException {
        try (Connection connection = getConnection())
        {
            //Creates material_copy
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO material_copy (material_id, copy_no) VALUES (?,?)");
            stm.setInt(1, materialID);
            stm.setInt(2, copyNo);
            stm.executeUpdate();
            connection.commit();

            //Finds the necessary details to create the CD object from DB.
            ResultSet cdDetails = getCDDetailsByID(materialID);
            if (cdDetails.next())
            {
                //Creates and returns a CD object if a CD with given materialID exists.
                return new CD(cdDetails.getInt("material_id"),
                        cdDetails.getInt("copy_no"),
                        cdDetails.getString("title"),
                        cdDetails.getString("publisher"),
                        String.valueOf(cdDetails.getDate("release_date")),
                        cdDetails.getString("description_of_the_content"),
                        cdDetails.getString("keywords"),
                        cdDetails.getString("audience"),
                        cdDetails.getString("language_"),
                        cdDetails.getDouble("playDuration"),
                        cdDetails.getInt("place_id"));
                       // i added the place_id
            }
            return null;
        }
    }

    @Override
    public ResultSet getCDDetailsByID(int materialID) throws SQLException, NoSuchElementException {
        try (Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM material join material_copy USING (material_id) JOIN CD using (material_id) where material_id = ?");
            stm.setInt(1, materialID);
            ResultSet result = stm.executeQuery();
            if (result.next())
            {
                return result;
            }
            else
                throw new NoSuchElementException(
                        "No CD with materialID " + materialID + " exists.");
        }
    }

}
