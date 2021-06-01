package database.material;

import database.DatabaseBuilder;
import database.loan.LoanDAOImpl;
import database.material.BookDAO;
import database.material.MaterialDAO;
import database.material.MaterialDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import shared.materials.Place;
import shared.materials.reading.Book;
import shared.person.Address;
import shared.person.MaterialCreator;
import shared.person.borrower.Borrower;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Material test
 *
 * @author Kutaiba
 * @version 1.0
 */
class MaterialDAOTest
{
  private MaterialDAO materialDAO;
  private DatabaseBuilder databaseBuilder;
  private MaterialCreator materialCreator;
  private Place place;

  @BeforeEach void setUp()
  {
    materialDAO = MaterialDAOImpl.getInstance();
    databaseBuilder = new DatabaseBuilder();
    materialCreator = new MaterialCreator(1, "fNameTest", "lNameTest",
        "1990-01-01", "countryTest");
    place = new Place(1, 1, "A", "lastNameTest", "genreTest");
  }

  @Test void createMaterialTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertDoesNotThrow(() -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialReturns1Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertEquals(1, materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereTitleIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create(null, "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWherePublisherIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", null, "2000-02-02", "descriptionTest", "Voksen",
            "Dansk", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereReleaseDateIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", null, "descriptionTest", "Voksen",
            "Dansk", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereDescriptionIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", null, "Voksen",
            "Dansk", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereKeywordsIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, null));
  }

  @Test void createMaterialWhereKeywordsConsistsOfNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "123"));
  }

  @Test void createMaterialWhereKeywordsConsistsOfACombinationOfNumbersAndLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "1a3"));
  }

  @Test void createMaterialWhereLanguageIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", null, "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereLanguageConsistsOfNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "123", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereLanguageConsistsOfACombinationOfNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "12a3", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereLanguageIsOutOfScopeTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "test", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereGenreIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", null, null, "keywordsTest"));
  }

  @Test void createMaterialWhereGenreConsistsOfNumbersTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "123", null, "keywordsTest"));
  }

  @Test void createMaterialWhereGenreConsistsOfACombinationOfNumbersAndLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "1a3", null, "keywordsTest"));
  }

  @Test void createMaterialWhereTargetAudienceIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            null, "Dansk", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereTargetAudienceConsistsOfNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "12", "Dansk", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereTargetAudienceConsistsOfACombinationOfNumbersAndLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "12a", "Dansk", "genreTest", null, "keywordsTest"));
  }

  @Test void createMaterialWhereTargetAudienceIsOutOfScopeTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Test", "Dansk", "genreTest", null, "keywordsTest"));
  }

  @Test void materialExistsTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(() -> materialDAO.materialExistInDB(1));
  }

  @Test void getCopyNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    MaterialCopyDAOImpl.getInstance().create(id, 2);
    assertEquals(2, materialDAO.getCopyNumberForMaterial(id));
  }

  @Test void getCopyNumberDoesNotThrowTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    MaterialCopyDAOImpl.getInstance().create(id, 2);
    assertDoesNotThrow(() -> materialDAO.getCopyNumberForMaterial(id));
  }

  @Test void getLatestCopyNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    MaterialCopyDAOImpl.getInstance().create(id, 2);
    MaterialCopyDAOImpl.getInstance().create(id, 3);
    assertEquals(3, materialDAO.getLatestCopyNo(id));
  }

  @Test void getLatestCopyNumberDoesNotEqualsTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    MaterialCopyDAOImpl.getInstance().create(id, 2);
    MaterialCopyDAOImpl.getInstance().create(id, 3);
    assertNotEquals(2, materialDAO.getLatestCopyNo(id));
  }

  @Test void getLatestCopyNumberDoesNotThrowTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    MaterialCopyDAOImpl.getInstance().create(id, 2);
    MaterialCopyDAOImpl.getInstance().create(id, 3);
    assertDoesNotThrow(() -> materialDAO.getLatestCopyNo(id));
  }

  @Test void getNumberOfAvailableCopiesTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    MaterialCopyDAOImpl.getInstance().create(id, 2);
    MaterialCopyDAOImpl.getInstance().create(id, 3);
    MaterialCopyDAOImpl.getInstance().create(id, 4);
    MaterialCopyDAOImpl.getInstance().create(id, 5);
    assertEquals(5, materialDAO.getNumberOfAvailableCopies(id));
  }

  @Test void getNumberOfAvailableCopiesDoesNotThrowTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    MaterialCopyDAOImpl.getInstance().create(id, 2);
    MaterialCopyDAOImpl.getInstance().create(id, 3);
    MaterialCopyDAOImpl.getInstance().create(id, 4);
    MaterialCopyDAOImpl.getInstance().create(id, 5);
    assertDoesNotThrow(() -> materialDAO.getNumberOfAvailableCopies(id));
  }

  @Test void getKeyWordsForMaterialTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(() -> materialDAO.getKeywordsForMaterial(id));
  }

  @Test void totalNumbersOfCopiesTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    MaterialCopyDAOImpl.getInstance().create(id, 2);
    MaterialCopyDAOImpl.getInstance().create(id, 3);
    MaterialCopyDAOImpl.getInstance().create(id, 4);
    assertEquals(4, materialDAO.getNumberOfAvailableCopies(id));
  }

  @Test void totalNumbersOfCopiesDoesNotThrowTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    MaterialCopyDAOImpl.getInstance().create(id, 2);
    MaterialCopyDAOImpl.getInstance().create(id, 3);
    MaterialCopyDAOImpl.getInstance().create(id, 4);
    assertDoesNotThrow(() -> materialDAO.getNumberOfAvailableCopies(id));
  }

  @Test void deleteMaterialTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(() -> materialDAO.deleteMaterial(id));
  }

  @Test void deleteMaterialWithAlLCopiesTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    MaterialCopyDAOImpl.getInstance().create(id, 2);
    MaterialCopyDAOImpl.getInstance().create(id, 3);
    MaterialCopyDAOImpl.getInstance().create(id, 4);
    materialDAO.deleteMaterial(id);
    assertEquals(0, materialDAO.totalNumberOfCopies(id));
  }

  @Test void findMaterialTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int id = materialDAO
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    BookDAOImpl.getInstance().create(id, "154", 98, materialCreator, place);
    assertDoesNotThrow(() -> materialDAO
        .findMaterial("titleTest", "Dansk", "keywordsTest", "genreTest",
            "Voksen", "Book"));
  }
}