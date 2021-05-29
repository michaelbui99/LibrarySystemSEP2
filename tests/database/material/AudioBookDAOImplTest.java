package database.material;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import shared.materials.audio.AudioBook;
import shared.person.MaterialCreator;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class AudioBookDAOImplTest
{
  private AudioBookDAO audioBookDAO;
  private DatabaseBuilder databaseBuilder;
  private MaterialCreator materialCreator;

  @BeforeEach void setup()
  {
    audioBookDAO = AudioBookDAOImpl.getInstance();
    databaseBuilder = new DatabaseBuilder();
    materialCreator = new MaterialCreator(1, "fNameTest", "lNameTest",
        "1990-01-01", "countryTest");
  }

  @Test void createAudioBookTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(
        () -> audioBookDAO.create(materialID, 100, materialCreator));
  }

  @Test void createAudioBookWithAUnExistedMaterialIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(PSQLException.class,
        () -> audioBookDAO.create(1000, 200, materialCreator));
  }

  @Test void createAudioBookWithAnEmptyMaterialIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(PSQLException.class,
        () -> audioBookDAO.create(0, 300, materialCreator));
  }

  @Test void createAudioBookWithAnEmptyLengthTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> audioBookDAO.create(materialID, 0, materialCreator));
  }

  @Test void createAudioBookWithANullMaterialCreatorTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> audioBookDAO.create(materialID, 800, null));
  }

  @Test void createAudioBookCopyTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    assertDoesNotThrow(() -> audioBookDAO.createAudioBookCopy(materialID, 2));
  }

  @Test void createAudioBookCopyWhereCopyNumberIs1Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    assertThrows(PSQLException.class,
        () -> audioBookDAO.createAudioBookCopy(materialID, 1));
  }

  @Test void createAudioBookCopyWhereCopyNumberIs0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    assertThrows(IllegalArgumentException.class,
        () -> audioBookDAO.createAudioBookCopy(materialID, 0));
  }

  @Test void createAudioBookCopyWhereCopyNumberIsLessThan0Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    assertThrows(IllegalArgumentException.class,
        () -> audioBookDAO.createAudioBookCopy(materialID, -1));
  }

  @Test void createAudioBookCopyForUnExistingMaterialTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(PSQLException.class,
        () -> audioBookDAO.createAudioBookCopy(6000, 2));
  }

  @Test void audioBookAlreadyExistsReturnTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    assertTrue(audioBookDAO
        .audioBookAlreadyExists("titleTest", "publisherTest", "2000-02-02",
            "descriptionTest", "Voksen", "Dansk", 123, materialCreator,
            "genreTest"));
  }

  @Test void audioBookAlreadyExistsReturnFalseTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    MaterialCreator materialCreator2 = new MaterialCreator(2, "f", "l",
        "2005-05-05", "c");
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    assertFalse(audioBookDAO
        .audioBookAlreadyExists("titleTest1", "publisherTest1", "2000-02-03",
            "descriptionTest1", "Barn", "Engelsk", 124, materialCreator2,
            "genreTest"));
  }

  @Test void getAudioBookDetailsByIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    assertDoesNotThrow(() -> audioBookDAO.getAudioBookDetailsByID(materialID));
  }

  @Test void getAudioBookDetailsByIDForUnExistingMaterialIDTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    assertThrows(NoSuchElementException.class,
        () -> audioBookDAO.getAudioBookDetailsByID(6000));
  }

  @Test void findAudioBookTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    assertDoesNotThrow(() -> audioBookDAO
        .findMaterial("titleTest", "Dansk", "keywordsTest", "genreTest",
            "Voksen"));
  }

  @Test void deleteAudioBookCopyTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    AudioBookDAOImpl.getInstance().createAudioBookCopy(materialID, 3);
    assertDoesNotThrow(() -> audioBookDAO.deleteAudioBookCopy(materialID, 3));
  }

  @Test void deleteAudioBookCopyOnUnExistingCopyNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    AudioBookDAOImpl.getInstance().createAudioBookCopy(materialID, 3);
    assertThrows(PSQLException.class, ()-> audioBookDAO.deleteAudioBookCopy(materialID, 500));
  }

  @Test void deleteAudioBookCopyOnUnExistingMaterialIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    AudioBookDAOImpl.getInstance().create(materialID, 123, materialCreator);
    AudioBookDAOImpl.getInstance().createAudioBookCopy(materialID, 3);
    assertThrows(PSQLException.class, ()-> audioBookDAO.deleteAudioBookCopy(6000, 3));
  }

}