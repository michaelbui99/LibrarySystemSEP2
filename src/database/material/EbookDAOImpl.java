package database.material;

import shared.materials.Material;
import shared.materials.MaterialStatus;
import shared.materials.Place;
import shared.materials.audio.CD;
import shared.materials.reading.EBook;
import shared.person.MaterialCreator;
import database.BaseDAO;
import database.materialcreator.MaterialCreatorImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    @Override public List<Material> findMaterial(String title, String language,
        String keywords, String genre, String targetAudience)
    {
        //list where we store the results
        List<Material> ml = new ArrayList<>();
        try (Connection connection = getConnection())
        {
            List<String> queryFragments = new ArrayList<>();
            String sql = "SELECT * FROM material "
                + "join e_book  on material.material_id = e_book.material_id  "
                + "join material_copy mt on e_book.material_id = mt.material_id "
                + "join place p on e_book.place_id = p.place_id "
                + "join material_creator mc on e_book.author = mc.person_id "
                + "join material_keywords mk on e_book.material_id = mk.material_id ";

            if (!title.isEmpty() || !language.isEmpty() || !genre.isEmpty() || !targetAudience.isEmpty())
            {
                sql += "where ";
            }

            if (!title.isEmpty())
            {
                queryFragments.add(" LOWER(material.title) LIKE  LOWER('%" + title + "%') ");
            }
            if (!language.isEmpty())
            {
                queryFragments.add(" material.language_  = '" + language + "' ");
            }
            if (!genre.isEmpty())
            {
                queryFragments.add(" LOWER(material.genre) LIKE LOWER('%" + genre + "%')");
            }
            if (!targetAudience.isEmpty())
            {
                queryFragments.add(" material.audience = '" + targetAudience + "' ");
            }
            sql += String.join(" and ", queryFragments);
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next())
            {
                //find all keywords related to this material id
                List<String> materialKeywordList = MaterialDAOImpl.getInstance()
                    .getKeywordsForMaterial(resultSet.getInt("material_id"));
                String materialKeywords = String.join(", ", materialKeywordList);
                boolean match = false;
                if (!keywords.isEmpty())
                { //if keywords were specified in search, compare them to material keywords from DB (materialKeywordList)
                    for (int i = 0; i < keywords.split(",").length; i++)
                    {
                        if (materialKeywords.toLowerCase(Locale.ROOT).contains(
                            keywords.split(",")[i].toLowerCase(Locale.ROOT)))
                        {
                            match = true; //search keyword matched material keyword - material will be added to result list
                            break;
                        }
                    }
                }
                else
                {
                    match = true; //if no keywords were specified by user - just add material keywords from DB to its material
                }
                if (match)
                {

                    EBook eBook = new EBook(resultSet.getInt("material_id"),
                        MaterialDAOImpl.getInstance()
                            .getCopyNumberForMaterial(resultSet.getInt("material_id")),
                        resultSet.getString("title"),
                        resultSet.getString("publisher"),
                        String.valueOf(resultSet.getDate("release_date")),
                        resultSet.getString("description_of_the_content"),
                        resultSet.getString("keyword"),
                        resultSet.getString("audience"),
                        resultSet.getString("language_"),
                        resultSet.getInt("page_no"),
                        resultSet.getString("license_no"),
                        resultSet.getString("genre"),
                        new MaterialCreator(resultSet.getString("f_name"),
                            resultSet.getString("l_name"),
                            String.valueOf(resultSet.getDate("dob")),
                            resultSet.getString("country")));
                    eBook.setMaterialStatus(MaterialDAOImpl.getInstance().checkIfCopyAvailable(resultSet.getInt("material_id")) ? MaterialStatus.Available : MaterialStatus.NotAvailable);
                    eBook.setKeywords(materialKeywords);
                    ml.add(eBook);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        System.out.println("result size: " + ml.size());
        return ml;
    }

}
