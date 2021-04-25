package database;

import java.sql.Connection;
import java.sql.SQLException;

public interface BookDAO {
    int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forfatter,String genre, String forlag, String sprog, String udgivelsesdato, int sidetal, int isbn) throws SQLException;
    void create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forfatter,String genre, String forlag, String sprog, String udgivelsesdato, int sidetal, int isbn, Connection connection);

}
