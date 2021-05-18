package database.material;

import shared.materials.reading.EBook;
import shared.person.MaterialCreator;
import database.BaseDAO;
import database.materialcreator.MaterialCreatorImpl;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EbookDAOImpl extends BaseDAO implements EbookDAO
{
    private static EbookDAO instance;
    private static final Lock lock = new ReentrantLock();

    public static EbookDAO getInstance()
    {
        //Double lock check for Thread safety
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new EbookDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void create(int material_id, int page_no, MaterialCreator author, int license_no) throws SQLException {
        try (Connection connection = getConnection())
        {
            if (MaterialCreatorImpl.getInstance().getCreatorId(author.getfName(),
                author.getlName(), author.getDob(), author.getCountry()) == -1)
            {
                MaterialCreator mc = MaterialCreatorImpl.getInstance().create(
                    author.getfName(), author.getlName(), author.getDob(),
                    author.getCountry());

            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO e_book (material_id, page_no, license_no, author) values (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, material_id);
            stm.setInt(2, page_no);
            stm.setInt(3, license_no);
            stm.setInt(4, mc.getPersonId());
            stm.executeUpdate();
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            connection.commit();
            }
            else
            {
                int mcId = MaterialCreatorImpl.getInstance().getCreatorId(
                    author.getfName(), author.getlName(), author.getDob(),
                    author.getCountry());

                PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO e_book (material_id, page_no, license_no, author) values (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
                stm.setInt(1, material_id);
                stm.setInt(2, page_no);
                stm.setInt(3, license_no);
                stm.setInt(4, mcId);
                stm.executeUpdate();
                ResultSet keys = stm.getGeneratedKeys();
                keys.next();
                connection.commit();
            }
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
                        eBookDetails.getInt("pageCount"),
                        eBookDetails.getString("licensNo"),
                        eBookDetails.getString("author"),
                        new MaterialCreator(eBookDetails.getString("f_name"),
                                            eBookDetails.getString("l_name"),
                                            String.valueOf(eBookDetails.getDate("dob")),
                                            eBookDetails.getString("country")));
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
                    "SELECT * FROM material join material_copy USING (material_id) JOIN e_book using (material_id) JOIN material_creator mc on e_book.author = mc.person_id WHERE material_id = ?");
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
