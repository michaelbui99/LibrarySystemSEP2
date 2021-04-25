package database;

import java.sql.*;
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
    public void create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato, int spillelængde, String genre, Connection connection) {

    }
}
