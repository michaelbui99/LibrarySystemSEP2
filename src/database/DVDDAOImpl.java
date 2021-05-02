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
    public void create(int material_id,String subtitle_lang, String length_,int place_id) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO DVD (material_id, subtitle_lang, length_, place_id) values (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, material_id);
            stm.setString(2, subtitle_lang);
            stm.setString(3, length_);
            stm.setInt(4, place_id);

            stm.executeUpdate();
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            connection.commit();
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
                        DVDDetails.getString("subtitle_lang"),
                        DVDDetails.getString("length_"),
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
