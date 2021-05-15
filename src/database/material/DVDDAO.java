package database.material;

import client.model.material.DVD;
import client.model.material.Place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface DVDDAO {

    void create(int material_id,String subtitle_lang, String length_,
        Place place) throws SQLException;
    DVD createDVDCopy(int materialID, int copyNo) throws SQLException;

    ResultSet getDVDDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
}


