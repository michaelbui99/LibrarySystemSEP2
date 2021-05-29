package database.material;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MaterialCopyDAOImplTest
{
  private MaterialCopyDAO materialCopyDAO;
  private DatabaseBuilder databaseBuilder;

  @BeforeEach void setup()
  {
    materialCopyDAO = MaterialCopyDAOImpl.getInstance();
    databaseBuilder = new DatabaseBuilder();
  }

  @Test void createMaterialCopyTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(() -> materialCopyDAO.create(materialID, 2));
  }

  @Test void createMaterialCopyWhereMaterialIdDoesNotExistTest()
      throws SQLException
  {
    assertThrows(PSQLException.class, () -> materialCopyDAO.create(6000, 2));
  }

  @Test void createMaterialCopyWhereCopyNumberEquals1Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(PSQLException.class,
        () -> materialCopyDAO.create(materialID, 1));
  }

  @Test void createMaterialCopyWhereCopyNumberIsLessThan0Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> materialCopyDAO.create(materialID, -1));
  }

  @Test void createMaterialCopyWhereCopyNumberEquals0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertThrows(IllegalArgumentException.class,
        () -> materialCopyDAO.create(materialID, 0));
  }

  @Test void getFirstAvailableCopyNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int materialID = MaterialDAOImpl.getInstance()
        .create("titleTest", "publisherTest", "2000-02-02", "descriptionTest",
            "Voksen", "Dansk", "genreTest", null, "keywordsTest");
    assertDoesNotThrow(
        () -> materialCopyDAO.getFirstAvailableCopyNo(materialID));
  }
}