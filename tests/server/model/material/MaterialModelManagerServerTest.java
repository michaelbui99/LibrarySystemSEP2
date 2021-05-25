package server.model.material;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import shared.materials.Place;
import shared.person.MaterialCreator;
import shared.person.librarian.Librarian;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MaterialModelManagerServerTest
{
  private MaterialModelServer materialModelServer;
  private DatabaseBuilder databaseBuilder;
  private Librarian librarian;
  private MaterialCreator materialCreator;
  private Place place;

  @BeforeEach void setup()
  {
    materialModelServer = new MaterialModelManagerServer();
    databaseBuilder = new DatabaseBuilder();
    librarian = new Librarian(1234, "FirstName", "LastName", "111111-1111",
        "+4511111111", "email@test", null, "1234");
    materialCreator = new MaterialCreator(1, "Bob", "Bobsen", "2020-12-12",
        "Denmark");
    place = new Place(1, "A", "creatorslastName1", "genre1");
  }

  //Register material of type book test section starts
  @Test void registerBookTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertDoesNotThrow(() -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", "Dansk", "1234", 1234, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithEmptyTitleTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook(null, "publisher1", "2004-01-01", "description1", "tage1",
            "Voksen", "Dansk", "1234", 1234, place, materialCreator, "genre1",
            null));
  }

  @Test void registerBookWithEmptyPublisherTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", null, "2004-01-01", "description1", "tage1",
            "Voksen", "Dansk", "1234", 1234, place, materialCreator, "genre1",
            null));
  }

  @Test void registerBookWithEmptyReleaseDateTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", null, "description1", "tage1",
            "Voksen", "Dansk", "1234", 1234, place, materialCreator, "genre1",
            null));
  }

  @Test void registerBookWithEmptyDescriptionTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", null, "tage1",
            "Voksen", "Dansk", "1234", 1234, place, materialCreator, "genre1",
            null));
  }

  @Test void registeBookWithEmptyKeywordsTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            null, "Voksen", "Dansk", "1234", 1234, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithEmptyTargetAudienceTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", null, "Dansk", "1234", 1234, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithTargetAudienceAssAnIntegerTest()
      throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "1", "Dansk", "1234", 1234, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithTargetAudienceOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "test", "Dansk", "1234", 1234, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithEmptyLanguageTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", null, "1234", 1234, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithLanguageAssAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", "1", "1234", 1234, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithLanguageOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", "test", "1234", 1234, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithEmptyISBNTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", "Dansk", null, 1234, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithISBNAsNotAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", "Dansk", "test", 1234, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithEmptyPageCountTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", "Dansk", "1234", 0, place, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithEmptyPlaceTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", "Dansk", "1234", 1234, null, materialCreator,
            "genre1", null));
  }

  @Test void registerBookWithEmptyMaterialCreatorTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", "Dansk", "1234", 1234, place, null,
            "genre1", null));
  }

  @Test void registerBookWithEmptyGenreTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", "Dansk", "1234", 1234, place, materialCreator,
            null, null));
  }

  @Test void registerBookWithGenreAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDunnyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("Title1", "publisher1", "2004-01-01", "description1",
            "tage1", "Voksen", "Dansk", "1234", 1234, place, materialCreator,
            "11", null));
  }
  // Register a material of type book section ends

}