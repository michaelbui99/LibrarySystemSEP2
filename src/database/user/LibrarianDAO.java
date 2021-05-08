package database.user;

import client.model.user.Librarian;
import client.model.loan.Address;

import java.sql.SQLException;

public interface LibrarianDAO
{
    Librarian create(int employee_no, String firstName, String lastName, String cpr,
        String tlfNumber, String email, Address address, String password) throws SQLException;
}
