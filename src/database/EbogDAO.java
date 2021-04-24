package database;

import java.sql.Connection;
import java.sql.SQLException;

public interface EbogDAO {
    int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato, int sidetal, int licensnr) throws SQLException;
    void create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog,  int sidetal, int licensnr,  Connection connection);
}
