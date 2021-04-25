package database;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookDAOImpl extends BaseDAO implements BookDAO{

    private static BookDAO instance;
    private static final Lock lock = new ReentrantLock();

    public static BookDAO getInstance()
    {
        //Double lock check for Thread safety
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new BookDAOImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forfatter,String genre, String forlag, String sprog, String udgivelsesdato, int sidetal, int isbn) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO Bog (materialeid, titel, maalgruppe, beskrivelseAfIndholdet, emneord, forfatter, genre, kopiNr,sprog, udgivelsesDato, sidetal, isbn) values (?,?,?,?,?,?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setInt(1, materialeid);
            stm.setString(2, titel);
            stm.setString(3, maalgruppe);
            stm.setString(4, beskrivelseafindholdet);
            stm.setString(5, emneord);
            stm.setString(6, forfatter);
            stm.setString(7, genre);
            stm.setString(8, forlag);
            stm.setString(9, sprog);
            stm.setDate(10, Date.valueOf(udgivelsesdato));
            stm.setInt(11, sidetal);
            stm.setInt(12, isbn);

            stm.executeUpdate();
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            connection.commit();
            return keys.getInt(1);
        }
    }

    @Override
    public void create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forfatter,String genre, String forlag, String sprog, String udgivelsesdato, int sidetal, int isbn, Connection connection) {

    }
}
