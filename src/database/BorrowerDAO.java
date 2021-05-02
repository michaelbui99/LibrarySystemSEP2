package database;

import client.model.loan.Address;
import client.model.loan.Borrower;


import java.sql.SQLException;

public interface BorrowerDAO {
    Borrower create(String cpr, String firstName, String lastName, String tlfNumber,
                    Address address, String email, String password) throws SQLException;
}
