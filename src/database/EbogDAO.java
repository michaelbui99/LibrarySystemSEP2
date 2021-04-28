package database;

import client.model.material.audio.CD;
import client.model.material.reading.EBook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface EbogDAO {
    int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato, int sidetal, int licensnr, String genre, String forfatter ) throws SQLException;
    EBook createEBookCopy(int materialID, int copyNo) throws SQLException;

    ResultSet getEBookDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
}
