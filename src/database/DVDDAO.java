package database;

import client.model.material.DVD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface DVDDAO {

    int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato,String undertitelsprog, int spillelængde,String genre) throws SQLException;
    DVD createDVDCopy(int materialID, int copyNo) throws SQLException;

    ResultSet getDVDDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
}


