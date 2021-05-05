package database;

import client.model.library.Librarian;
import client.model.loan.Address;

import java.sql.SQLException;

public interface LibrarianDAO {
    Librarian create(String cpr, String firstName, String lastName, String tlfNumber,
                     Address address, String email, String password) throws SQLException;
}
