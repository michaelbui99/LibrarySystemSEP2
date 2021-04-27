package database;

import client.model.material.audio.AudioBook;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface LydbogDAO {

    int create(int materialeid, String titel, String maalgruppe, String beskrivelseafindholdet, String emneord, String forlag, String sprog, String udgivelsesdato, int spillelængde,String genre) throws SQLException;
    AudioBook createAudioBookCopy(int materialID, int copyNo) throws SQLException;

    ResultSet getAudioBookDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
}
