package database;

import client.model.material.audio.CD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface CDDAO {

    int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato, int spillelængde, String genre) throws SQLException;

    CD createCDCopy(int materialID, int copyNo) throws SQLException;
    
    ResultSet getCDDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
}
