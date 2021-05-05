package database;

import client.model.library.Librarian;
import client.model.loan.Address;
import client.model.loan.Borrower;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LibrarianImpl extends  BaseDAO implements LibrarianDAO {


    private static LibrarianDAO instance;
    private static final Lock lock = new ReentrantLock();

    public static LibrarianDAO getInstance()
    {
        if (instance == null)
        {
            synchronized (lock)
            {
                if (instance == null)
                {
                    instance = new LibrarianImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public Librarian create(String cpr, String firstName, String lastName, String tlfNumber, Address address, String email, String password) throws SQLException {
        try (Connection connection = getConnection())
        {
            PreparedStatement stm = connection.prepareStatement( //the table structure needs to change to the values from the query so we can test it
                    "INSERT INTO Librarian(cpr,fName,lName, telNo, address, email, password) values (?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, cpr);
            stm.setString(2, firstName);
            stm.setString(3, lastName);
            stm.setString(4, tlfNumber);
            stm.setObject(5, address);
            stm.setString(6, email);
            stm.setString(7, password);
            stm.executeUpdate();
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            connection.commit();
            return new Librarian(cpr, firstName, lastName, tlfNumber, address, email, password);
        }
    }
}
