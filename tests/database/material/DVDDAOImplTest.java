package database.material;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import shared.materials.Place;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DVDDAOImplTest
{
  private DVDDAO dvddao;
  private DatabaseBuilder databaseBuilder;
  private Place place;

  @BeforeEach void setup()
  {
    dvddao = DVDDAOImpl.getInstance();
    databaseBuilder = new DatabaseBuilder();
    place = new Place(1, 1, "A", "lastNameTest", "genreTest");
  }

  @Test void createDVDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(() -> dvddao.create(materialID, "Dansk", 123, place));
  }

  @Test void createDVDWithMaterialIDThatDoesNotExistTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(PSQLException.class,
        () -> dvddao.create(6000, "Dansk", 123, place));
  }

  @Test void createDVDWithNullSubtitleLanguageTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> dvddao.create(materialID, null, 123, place));
  }

  @Test void createDVDWithSubtitleLanguageAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> dvddao.create(materialID, "125", 123, place));
  }

  @Test void createDVDWithSubtitleLanguageAsACombinationOfIntegerAndLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> dvddao.create(materialID, "12s", 123, place));
  }

  @Test void createDVDWithLengthEquals0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> dvddao.create(materialID, "Dansk", 0, place));
  }

  @Test void createDVDWithLengthLessThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> dvddao.create(materialID, "Dansk", -1, place));
  }

  @Test void createDVDWithNullPlaceTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> dvddao.create(materialID, "Dansk", 125, null));
  }

  @Test void createDVDCopyTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    assertDoesNotThrow(() -> dvddao.createDVDCopy(materialID, 2));
  }

  @Test void createDVDCopyWhereCopyNumberEqual1Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    assertThrows(PSQLException.class,
        () -> dvddao.createDVDCopy(materialID, 1));
  }

  @Test void createDVDCopyWhereCopyNumberLessThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    assertThrows(IllegalArgumentException.class,
        () -> dvddao.createDVDCopy(materialID, -1));
  }

  @Test void createDVDCopyWhereCopyNumberEqual0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    assertThrows(IllegalArgumentException.class,
        () -> dvddao.createDVDCopy(materialID, 0));
  }

  @Test void createDVDCopyForUnExistingMaterialTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(PSQLException.class, () -> dvddao.createDVDCopy(6000, 2));
  }

  @Test void dvdAlreadyExistsReturnTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    assertTrue(dvddao
        .dvdAlreadyExists("titleTest", "publisherTest", "2000-02-02",
            "descriptionTest", "Voksen", "Dansk", "157", "genreTest"));
  }

  @Test void dvdAlreadyExistsReturnFalseTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    assertFalse(dvddao
        .dvdAlreadyExists("titleTest1", "publisherTes1", "2000-02-09",
            "descriptionTest1", "Barn", "Engelsk", "159", "genreTestw"));
  }

  @Test void getDVDDetailsByID() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    assertDoesNotThrow(() -> dvddao.getDVDDetailsByID(materialID));
  }

  @Test void getDVDDetailsByIDForUnExistingMaterialIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    assertThrows(NoSuchElementException.class,
        () -> dvddao.getDVDDetailsByID(6000));
  }

  @Test void findDVDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    assertDoesNotThrow(() -> dvddao
        .findMaterial("titleTest", "Dansk", "keywordsTest", "genreTest",
            "Voksen"));
  }

  @Test void deleteDVDCopyTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    dvddao.createDVDCopy(materialID, 2);
    assertDoesNotThrow(() -> dvddao.deleteDVDCopy(materialID, 2));
  }

  @Test void deleteDVDCopyOnUnExistingCopyNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    dvddao.createDVDCopy(materialID, 2);
    assertThrows(PSQLException.class,
        () -> dvddao.deleteDVDCopy(materialID, 6000));
  }

  @Test void deleteDVDCopyOnUnExistingMaterialIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    dvddao.create(materialID, "Dansk", 157, place);
    dvddao.createDVDCopy(materialID, 2);
    assertThrows(PSQLException.class,
        () -> dvddao.deleteDVDCopy(6000, 2));
  }
}