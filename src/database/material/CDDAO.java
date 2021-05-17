package database.material;

import shared.materials.Place;
import shared.materials.audio.CD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public interface CDDAO {

    void create(int material_id,  int length_, Place place) throws SQLException;

    CD createCDCopy(int materialID, int copyNo) throws SQLException;
    
    ResultSet getCDDetailsByID(int materialID) throws SQLException,
            NoSuchElementException;
}
