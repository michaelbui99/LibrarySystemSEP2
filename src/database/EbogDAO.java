package database;

import client.model.material.audio.CD;
import client.model.material.reading.EBook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface EbogDAO {
    void create(int material_id, int page_no, int authorId, int license_no) throws SQLException;
    EBook createEBookCopy(int materialID, int copyNo) throws SQLException;

    ResultSet getEBookDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
}
