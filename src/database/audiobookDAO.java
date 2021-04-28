package database;

import client.model.material.audio.AudioBook;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface audiobookDAO
{

    void create(int material_id, double length_,int authorId) throws SQLException;
    AudioBook createAudioBookCopy(int materialID, int copyNo) throws SQLException;

    ResultSet getAudioBookDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
}
