package database.material;

import shared.materials.Material;
import shared.materials.MaterialStatus;
import shared.materials.Place;
import shared.materials.audio.AudioBook;
import shared.materials.reading.Book;
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

public class AudioBookDAOImpl extends BaseDAO implements AudioBookDAO
{

    private static AudioBookDAO instance;
    private static final Lock lock = new ReentrantLock();

    public static AudioBookDAO getInstance()
    {
        //Double lock check for Thread safety
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new AudioBookDAOImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void create(int material_id, int length_, MaterialCreator author) throws SQLException {
        try (Connection connection = getConnection())
        {
            if (MaterialCreatorImpl.getInstance().getCreatorId(author.getfName(),
                author.getlName(), author.getDob(), author.getCountry()) == -1)
            {
            MaterialCreator mc = MaterialCreatorImpl.getInstance().create(
                author.getfName(), author.getlName(), author.getDob(),
                author.getCountry()
            );
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO audiobook (material_id, length_, author) values (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, material_id);
            stm.setInt(2, length_);
            stm.setInt(3, mc.getPersonId());
                      stm.executeUpdate();
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            connection.commit();
            }
            else
            {
                int mcId = MaterialCreatorImpl.getInstance().getCreatorId(
                    author.getfName(), author.getlName(), author.getDob(),
                    author.getCountry()
                );
                PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO audiobook (material_id, length_, author) values (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
                stm.setInt(1, material_id);
                stm.setInt(2, length_);
                stm.setInt(3, mcId);
                stm.executeUpdate();
                ResultSet keys = stm.getGeneratedKeys();
                keys.next();
                connection.commit();
            }
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
            ResultSet audioBookDetails = getAudioBookDetailsByID(materialID);
            if (audioBookDetails.next())
            {
                //Creates and returns a AudioBook object if a AudioBook with given materialID exists.
                return new AudioBook(audioBookDetails.getInt("material_id"),
                    audioBookDetails.getInt("copy_no"),
                audioBookDetails.getString("title"),
                    audioBookDetails.getString("publisher"),
                    String.valueOf(audioBookDetails.getDate("release_date")),
                    audioBookDetails.getString("description_of_the_content"),
                    audioBookDetails.getString("keywords"),
                    audioBookDetails.getString("audience"),
                    audioBookDetails.getString("language_"),
                    audioBookDetails.getInt("length_"),
                    new MaterialCreator(audioBookDetails.getString("f_name"),
                                        audioBookDetails.getString("l_name"),
                                        String.valueOf(audioBookDetails.getDate("dob")),
                                        audioBookDetails.getString("country")),
                    audioBookDetails.getString("url"));
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
                    "SELECT * FROM material join material_copy USING (material_id) JOIN AudioBook using (material_id) JOIN material_creator mc on audiobook.author = mc.person_id WHERE material_id = ?");
            stm.setInt(1, materialID);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                return result;
            } else
                throw new NoSuchElementException(
                        "No AudioBook with materialID " + materialID + " exists.");
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
                + "join audiobook  on material.material_id = audiobook.material_id  "
                + "join material_copy mt on audiobook.material_id = mt.material_id "
                + "join place p on audiobook.place_id = p.place_id"
                + "join material_creator mc on audiobook.author = mc.person_id"
                + "join material_keywords mk on audiobook.material_id = mk.material_id";

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

                    AudioBook audiobook = new AudioBook(resultSet.getInt("material_id"),
                        MaterialDAOImpl.getInstance().getCopyNumberForMaterial(
                            resultSet.getInt("material_id")), resultSet.getString("title"),
                        resultSet.getString("publisher"), String.valueOf(resultSet.getDate("release_date")),
                        resultSet.getString("description_of_the_content"), "",
                        resultSet.getString("audience"), resultSet.getString("language_"),
                        resultSet.getInt("length_"), new MaterialCreator(resultSet.getString("f_name"),
                        resultSet.getString("l_name"), String.valueOf(resultSet.getDate("dob")),
                        resultSet.getString("country")), resultSet.getString("url"));
                    audiobook.setMaterialStatus(MaterialDAOImpl.getInstance()
                        .checkIfCopyAvailable(MaterialDAOImpl.getInstance().getCopyNumberForMaterial(resultSet.getInt("material_id"))) ?
                        MaterialStatus.Available :
                        MaterialStatus.NotAvailable);
                    audiobook.setKeywords(materialKeywords);
                    ml.add(audiobook);
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

