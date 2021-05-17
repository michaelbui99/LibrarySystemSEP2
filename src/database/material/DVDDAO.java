package database.material;

import shared.materials.DVD;
import shared.materials.Place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface DVDDAO {

    void create(int material_id,String subtitle_lang, int length_,
        Place place) throws SQLException;
    DVD createDVDCopy(int materialID, int copyNo) throws SQLException;

    ResultSet getDVDDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
}


