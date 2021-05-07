package database.material;

import client.model.material.audio.AudioBook;
import database.BaseDAO;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class audiobookDAOImpl extends BaseDAO implements audiobookDAO
{

    private static audiobookDAO instance;
    private static final Lock lock = new ReentrantLock();

    public static audiobookDAO getInstance()
    {
        //Double lock check for Thread safety
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new audiobookDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void create(int material_id, double length_,int authorId) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO Bog (material_id, length_, authorId) values (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, material_id);
            stm.setDouble(2, length_);
            stm.setInt(3, authorId);
                      stm.executeUpdate();
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            connection.commit();
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
                    AudioBookDetails.getInt("length_"),
                    AudioBookDetails.getString("author"));
            }
            return null;
        }
    }
    //calculate how many copies for each material
    public  int getCopyNumberForMaterial(int materialid){
        int copyno = 0;
        try(Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement("SELECT count (*) as copy_no  FROM material_copy where material_id = " + materialid);
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("copy_no");
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return copyno;
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
