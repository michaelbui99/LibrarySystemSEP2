package database;

import client.model.material.DVD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface DVDDAO {

    void create(int material_id,String subtitle_lang, String length_,int place_id) throws SQLException;
    DVD createDVDCopy(int materialID, int copyNo) throws SQLException;

    ResultSet getDVDDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
}


