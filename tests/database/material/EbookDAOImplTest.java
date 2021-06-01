package database.material;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import shared.person.MaterialCreator;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * E-book test
 *
 * @author Kutaiba
 * @version 1.0
 */
class EbookDAOImplTest
{
  private EbookDAO ebookDAO;
  private DatabaseBuilder databaseBuilder;
  private MaterialCreator materialCreator;

  @BeforeEach void setup()
  {
    ebookDAO = EbookDAOImpl.getInstance();
    databaseBuilder = new DatabaseBuilder();
    materialCreator = new MaterialCreator(1, "fNameTest", "lNameTest",
        "1990-01-01", "countryTest");
  }

  @Test void createEbookTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(
        () -> ebookDAO.create(materialID, 600, materialCreator, 1233));
  }

  @Test void createEbookWithAUnExistedMaterialIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(SQLException.class,
        () -> ebookDAO.create(6000, 600, materialCreator, 1233));
  }

  @Test void createEbookWherePageNumberLessThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> ebookDAO.create(materialID, -1, materialCreator, 1233));
  }

  @Test void createEbookWherePageNumberEquals0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> ebookDAO.create(materialID, 0, materialCreator, 1233));
  }

  @Test void CreateEbookWhereLicenseNumberIsLessThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> ebookDAO.create(materialID, 15, materialCreator, -1));
  }

  @Test void CreateEbookWhereLicenseNumberEquals0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> ebookDAO.create(materialID, 15, materialCreator, 0));
  }

  @Test void createEbookWhereMaterialCreatorIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> ebookDAO.create(materialID, 15, null, 546));
  }

  @Test void createEbookCopyTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    assertDoesNotThrow(() -> ebookDAO.createEBookCopy(materialID, 2));
  }

  @Test void createEbookCopyWhereCopyNumberEquals1Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    assertThrows(SQLException.class,
        () -> ebookDAO.createEBookCopy(materialID, 1));
  }

  @Test void createEbookCopyWhereCopyNumberEquals0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    assertThrows(IllegalArgumentException.class,
        () -> ebookDAO.createEBookCopy(materialID, 0));
  }

  @Test void createEbookCopyWhereCopyNumberLessThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    assertThrows(IllegalArgumentException.class,
        () -> ebookDAO.createEBookCopy(materialID, -1));
  }

  @Test void createEbookCopyForUnExistingMaterialTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    assertThrows(PSQLException.class, () -> ebookDAO.createEBookCopy(6000, 6));
  }

  @Test void eBookAlreadyExistsReturnTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    assertTrue(ebookDAO
        .eBookAlreadyExists("titleTest", "publisherTest", "2000-02-02",
            "descriptionTest", "Voksen", "Dansk", 15, 546, "genreTest",
            materialCreator));
  }

  @Test void eBookAlreadyExistsReturnFalseTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    assertFalse(ebookDAO
        .eBookAlreadyExists("titleTest1", "publisherTest1", "2000-02-07",
            "descriptionTest1", "Barn", "Engelsk", 154, 5461, "genreTestd",
            materialCreator));
  }

  @Test void getEbookDetailsByIDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    assertDoesNotThrow(() -> ebookDAO.getEBookDetailsByID(materialID));
  }

  @Test void getEbookDetailsByIDForUnExistingMaterialIDTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    assertThrows(NoSuchElementException.class,
        () -> ebookDAO.getEBookDetailsByID(6000));
  }

  @Test void findEbookTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    assertDoesNotThrow(() -> ebookDAO
        .findMaterial("titleTest", "Dansk", "keywordsTest", "genreTest",
            "Voksen"));
  }

  @Test void deleteEbookCopyTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    ebookDAO.create(materialID, 15, materialCreator, 546);
    ebookDAO.createEBookCopy(materialID, 2);
    assertDoesNotThrow(() -> ebookDAO.deleteEBookCopy(materialID, 2));
  }
}