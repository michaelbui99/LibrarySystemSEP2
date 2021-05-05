package database.user;

import client.model.user.Librarian;
import client.model.loan.Address;

import java.sql.SQLException;

public interface LibrarianDAO {
    Librarian create(String cpr, String firstName, String lastName, String tlfNumber,
                     Address address, String email, String password) throws SQLException;
}
