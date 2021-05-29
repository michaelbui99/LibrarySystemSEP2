package database.material;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import shared.materials.Place;
import shared.person.MaterialCreator;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOImplTest
{
  private BookDAO bookDAO;
  private DatabaseBuilder databaseBuilder;
  private MaterialCreator materialCreator;
  private Place place;

  @BeforeEach void setup()
  {
    bookDAO = BookDAOImpl.getInstance();
    databaseBuilder = new DatabaseBuilder();
    materialCreator = new MaterialCreator(1, "fNameTest", "lNameTest",
        "1990-01-01", "countryTest");
    place = new Place(1, 1, "A", "lastNameTest", "genreTest");
  }

  @Test void createBookTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(
        () -> bookDAO.create(materialID, "1234", 12, materialCreator, place));
  }

  @Test void createBookWthAUnExistedMaterialIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(PSQLException.class,
        () -> bookDAO.create(10000, "12345", 50, materialCreator, place));
  }

  @Test void createBookWithAnEmptyMaterialIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(PSQLException.class,
        () -> bookDAO.create(0, "1234", 95, materialCreator, place));
  }

  @Test void createBookWithEmptyISBNTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> bookDAO.create(materialID, null, 23, materialCreator, place));
  }

  @Test void createBookWithPageCountLestThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> bookDAO.create(materialID, "784", -1, materialCreator, place));
  }

  @Test void createBookWithPageCountEqual0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> bookDAO.create(materialID, "784", 0, materialCreator, place));
  }

  @Test void createBookWithPageCountMore0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(
        () -> bookDAO.create(materialID, "784", 1, materialCreator, place));
  }

  @Test void createBookWithMaterialCreatorIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> bookDAO.create(materialID, "784", 11, null, place));
  }

  @Test void createBookWithPlaceIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> bookDAO.create(materialID, "784", 11, materialCreator, null));
  }

  @Test void createBookWithISBNAsALetterTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> bookDAO.create(materialID, "aaa", 11, materialCreator, place));
  }

  @Test void createBookWithISBNAsACombinationOfLetterAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> bookDAO.create(materialID, "aa1", 11, materialCreator, place));
  }

  @Test void createBookCopyTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    bookDAO.create(materialID, "234", 11, materialCreator, place);
    assertDoesNotThrow(() -> bookDAO.createBookCopy(materialID, 2));
  }

  @Test void createBookCopyWhereCopyNumberIs1Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    bookDAO.create(materialID, "234", 11, materialCreator, place);
    assertThrows(PSQLException.class,
        () -> bookDAO.createBookCopy(materialID, 1));
  }

  @Test void createBookCopyWhereCopyNumberIs0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    bookDAO.create(materialID, "234", 11, materialCreator, place);
    assertThrows(IllegalArgumentException.class,
        () -> bookDAO.createBookCopy(materialID, 0));
  }

  @Test void createBookCopyWhereCopyNumberIsLessThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    bookDAO.create(materialID, "234", 11, materialCreator, place);
    assertThrows(IllegalArgumentException.class,
        () -> bookDAO.createBookCopy(materialID, -1));
  }

  @Test void createBookCopyForUnExistingMaterialTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(PSQLException.class, () -> bookDAO.createBookCopy(6000, 2));
  }

  @Test void audioBookAlreadyExistsReturnTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    bookDAO.create(materialID, "234", 11, materialCreator, place);
    assertTrue(bookDAO
        .bookAlreadyExists("titleTest", "publisherTest", "2000-02-02",
            "descriptionTest", "Voksen", "Dansk", "234", 11, materialCreator,
            "genreTest"));
  }

  @Test void audioBookAlreadyExistsReturnFalseTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    bookDAO.create(materialID, "234", 11, materialCreator, place);
    assertFalse(bookDAO
        .bookAlreadyExists("titleTest1", "publisherTest1", "2000-02-03",
            "descriptionTest1", "Barn", "Engelsk", "2334", 12, materialCreator,
            "genreTeste"));
  }

  @Test void getBookDetailsByIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    bookDAO.create(materialID, "234", 11, materialCreator, place);
    assertDoesNotThrow(() -> bookDAO.getBookDetailsByID(materialID));
  }

  @Test void getAudioBookDetailsByIDForUnExistingMaterialIDTest()
      throws SQLException
  {
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    bookDAO.create(materialID, "234", 11, materialCreator, place);
    assertThrows(NoSuchElementException.class,
        () -> bookDAO.getBookDetailsByID(60000));
  }

  @Test void findBookTest() throws SQLException
  {
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    bookDAO.create(materialID, "234", 11, materialCreator, place);
    assertDoesNotThrow(() -> bookDAO
        .findMaterial("titleTest", "Dansk", "keywordsTest", "genreTest",
            "Voksen"));
  }

  @Test void deleteBookCopyTest() throws SQLException
  {
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    bookDAO.create(materialID, "234", 11, materialCreator, place);
    bookDAO.createBookCopy(materialID, 2);
    assertDoesNotThrow(()-> bookDAO.deleteBookCopy(materialID, 2));
  }
}