package database.material;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import shared.materials.Place;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CD test
 *
 * @author Kutaiba
 * @version 1.0
 */
class CDDAOImplTest
{
  private CdDAO cddao;
  private DatabaseBuilder databaseBuilder;
  private Place place;

  @BeforeEach void setup()
  {
    cddao = CDDAOImpl.getInstance();
    databaseBuilder = new DatabaseBuilder();
    place = new Place(1, 1, "A", "lastNameTest", "genreTest");
  }

  @Test void createCDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(() -> cddao.create(materialID, 123, place));
  }

  @Test void createCdWhereMaterialIDDoesNotExistTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(PSQLException.class, () -> cddao.create(5000, 123, place));
  }

  @Test void createCdWhereLengthIsLessThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> cddao.create(materialID, -1, place));
  }

  @Test void createCdWhereLengthEqual0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> cddao.create(materialID, 0, place));
  }

  @Test void createCDWherePlaceIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> cddao.create(materialID, 55, null));
  }

  @Test void createCDCopyTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    cddao.create(materialID, 55, place);
    assertDoesNotThrow(() -> cddao.createCDCopy(materialID, 2));
  }

  @Test void createCDCopyWhereCopyNumberEqual1Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    cddao.create(materialID, 55, place);
    assertThrows(PSQLException.class, () -> cddao.createCDCopy(materialID, 1));
  }

  @Test void createCDCopyWhereCopyNumberEquals0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    cddao.create(materialID, 55, place);
    assertThrows(IllegalArgumentException.class,
        () -> cddao.createCDCopy(materialID, 0));
  }

  @Test void createCDCopyWhereCopyNumberLessThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    cddao.create(materialID, 55, place);
    assertThrows(IllegalArgumentException.class,
        () -> cddao.createCDCopy(materialID, -1));
  }

  @Test void createCDCopyForUnExistingMaterialTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(PSQLException.class, () -> cddao.createCDCopy(6000, 2));
  }

  @Test void cdAlreadyExistsReturnTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    cddao.create(materialID, 55, place);
    assertTrue(cddao.cdAlreadyExists("titleTest", "publisherTest", "2000-02-02",
        "descriptionTest", "Voksen", "Dansk", 55, "genreTest"));
  }

  @Test void cdAlreadyExistsReturnFalseTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    cddao.create(materialID, 55, place);
    assertFalse(cddao
        .cdAlreadyExists("titleTest1", "publisherTest1", "2000-02-06",
            "descriptionTest1", "Barn", "Engelsk", 56, "genreTestt"));
  }

  @Test void getCDDetailsByIdTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    cddao.create(materialID, 55, place);
    assertDoesNotThrow(() -> cddao.getCDDetailsByID(materialID));
  }

  @Test void getCDDetailsByIDForUnExistingMaterialIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    cddao.create(materialID, 55, place);
    assertThrows(NoSuchElementException.class,
        () -> cddao.getCDDetailsByID(6000));
  }

  @Test void findCDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    cddao.create(materialID, 55, place);
    assertDoesNotThrow(() -> cddao
        .findMaterial("titleTest", "Dansk", "keywordsTest", "genreTest",
            "Voksen"));
  }

  @Test void deleteCDCopyTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    cddao.create(materialID, 55, place);
    cddao.createCDCopy(materialID, 2);
    assertDoesNotThrow(() -> cddao.deleteCDCopy(materialID, 2));
  }
}