package server.model.material;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    databaseBuilder.createDummyDataWithoutInfo();
    assertDoesNotThrow(() -> materialModelServer
        .registerBook("titleTest", "publisherTest", "2001-12-12",
            "descriptionTest", "tagsTest", "Voksen", "Dansk", "1234", 11, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithEmptyTitleTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook(null, "publisherTest", "2004-01-01", "descriptionTest",
            "tageTest", "Voksen", "Dansk", "1234", 1234, place, materialCreator,
            "genreTest", null));
  }

  @Test void registerBookWithEmptyPublisherTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", null, "2004-01-01", "descriptionTest",
            "tageTest", "Voksen", "Dansk", "1234", 1234, place, materialCreator,
            "genreTest", null));
  }

  @Test void registerBookWithEmptyReleaseDateTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", null, "descriptionTest",
            "tageTest", "Voksen", "Dansk", "1234", 1234, place, materialCreator,
            "genreTest", null));
  }

  @Test void registerBookWithEmptyDescriptionTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01", null,
            "tageTest", "Voksen", "Dansk", "1234", 1234, place, materialCreator,
            "genreTest", null));
  }

  @Test void registeBookWithEmptyKeywordsTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", null, "Voksen", "Dansk", "1234", 1234, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithKeywordsAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "11", "Voksen", "Dansk", "1234", 1234, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithEmptyTargetAudienceTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", null, "Dansk", "1234", 1234, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithTargetAudienceAsAnIntegerTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "1", "Dansk", "1234", 1234, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithTargetAudienceOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "test", "Dansk", "1234", 1234, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithEmptyLanguageTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", null, "1234", 1234, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithLanguageAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", "1", "1234", 1234, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithLanguageOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", "test", "1234", 1234,
            place, materialCreator, "genreTest", null));
  }

  @Test void registerBookWithEmptyISBNTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", "Dansk", null, 1234, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithISBNAsNotAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", "Dansk", "test", 1234,
            place, materialCreator, "genreTest", null));
  }

  @Test void registerBookWithEmptyPageCountTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", "Dansk", "1234", 0, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithPageCountLeserThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", "Dansk", "1234", -1, place,
            materialCreator, "genreTest", null));
  }

  @Test void registerBookWithEmptyPlaceTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", "Dansk", "1234", 1234,
            null, materialCreator, "genreTest", null));
  }

  @Test void registerBookWithEmptyMaterialCreatorTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", "Dansk", "1234", 1234,
            place, null, "genreTest", null));
  }

  @Test void registerBookWithEmptyGenreTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", "Dansk", "1234", 1234,
            place, materialCreator, null, null));
  }

  @Test void registerBookWithGenreAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerBook("TitleTest", "publisherTest", "2004-01-01",
            "descriptionTest", "tageTest", "Voksen", "Dansk", "1234", 1234,
            place, materialCreator, "11", null));
  }
  // Register a material of type book section ends

  // Register a material of type ebook section starts

  @Test void registerEBookTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertDoesNotThrow(() -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", "Dansk", 900, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithEmptyTitleTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook(null, "EbookPublisher", "2002-01-01", "descriptionEbook",
            "tagesEbook", "Voksen", "Dansk", 900, 122, materialCreator,
            "genreEbook", null));
  }

  @Test void registerEBookWithEmptyPublisherTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", null, "2002-01-01", "descriptionEbook",
            "tagesEbook", "Voksen", "Dansk", 900, 122, materialCreator,
            "genreEbook", null));
  }

  @Test void registerEBookWithEmptyReleaseDateTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", null, "descriptionEbook",
            "tagesEbook", "Voksen", "Dansk", 900, 122, materialCreator,
            "genreEbook", null));
  }

  @Test void registerEBookWithEmptyDescriptionTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01", null,
            "tagesEbook", "Voksen", "Dansk", 900, 122, materialCreator,
            "genreEbook", null));
  }

  @Test void registeEBookWithEmptyKeywordsTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", null, "Voksen", "Dansk", 900, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithKeywordsAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "11", "Voksen", "Dansk", 900, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithEmptyTargetAudienceTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", null, "Dansk", 900, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithTargetAudienceAsAnIntegerTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "11", "Dansk", 900, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithTargetAudienceOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "test", "Dansk", 900, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithEmptyLanguageTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", null, 900, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithLanguageAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", "11", 900, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithLanguageOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", "test", 900, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithEmptyLicenseNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", "Dansk", 900, 0,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithLicenseNumberLeserThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", "Dansk", 900, -1,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithEmptyPageCountTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", "Dansk", 0, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithPageCountLeserThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", "Dansk", -1, 122,
            materialCreator, "genreEbook", null));
  }

  @Test void registerEBookWithEmptyMaterialCreatorTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", "Dansk", 900, 122, null,
            "genreEbook", null));
  }

  @Test void registerEBookWithEmptyGenreTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", "Dansk", 900, 122,
            materialCreator, null, null));
  }

  @Test void registerEBookWithGenreAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerEBook("EbookTitle", "EbookPublisher", "2002-01-01",
            "descriptionEbook", "tagesEbook", "Voksen", "Dansk", 900, 122,
            materialCreator, "11", null));
  }
  // Register a material of type ebook section ends

  // Register a material of type audio book section starts
  @Test void registerAudioBookTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertDoesNotThrow(() -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", "Dansk", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithEmptyTitleTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook(null, "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", "Dansk", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithEmptyPublisherTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", null, "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", "Dansk", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithEmptyReleaseDateTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", null,
            "audioBookDescription", "audioBookTages", "Voksen", "Dansk", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithEmptyDescriptionTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            null, "audioBookTages", "Voksen", "Dansk", 123, "audioBookGenre",
            materialCreator, null));
  }

  @Test void registeAudioBookWithEmptyKeywordsTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", null, "Voksen", "Dansk", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithKeywordsAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "11", "Voksen", "Dansk", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithEmptyTargetAudienceTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", null, "Dansk", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithTargetAudienceAsAnIntegerTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "11", "Dansk", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithTargetAudienceOutOfCheckTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "test", "Dansk", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithEmptyPlayDurationTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", "Dansk", 0,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithPlayDurationLeserThan0Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", "Dansk", -1,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithEmptyLanguageTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", null, 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithLanguageAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", "11", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithLanguageOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", "test", 123,
            "audioBookGenre", materialCreator, null));
  }

  @Test void registerAudioBookWithEmptyMaterialCreatorTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", "Dansk", 123,
            "audioBookGenre", null, null));
  }

  @Test void registerAudioBookWithEmptyGenreTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", "Dansk", 123,
            null, materialCreator, null));
  }

  @Test void registerAudioBookWithGenreAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerAudioBook("audioBookTitle", "audioBookPublisher", "2000-01-01",
            "audioBookDescription", "audioBookTages", "Voksen", "Dansk", 123,
            "11", materialCreator, null));
  }
  // Register a material of type audio book section ends

  // Register a material of type CD section starts
  @Test void registerCDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertDoesNotThrow(() -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", "Dansk", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithEmptyTitleTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD(null, "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", "Dansk", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithEmptyPublisherTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", null, "2006-02-02", "cdDescription", "cdTages",
            "Voksen", "Dansk", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithEmptyReleaseDateTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", null, "cdDescription", "cdTages",
            "Voksen", "Dansk", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithEmptyDescriptionTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", null, "cdTages",
            "Voksen", "Dansk", 111, place, "cdGenre", null));
  }

  @Test void registeCDWithEmptyKeywordsTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            null, "Voksen", "Dansk", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithKeywordsAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "11", "Voksen", "Dansk", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithEmptyTargetAudienceTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", null, "Dansk", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithTargetAudienceAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "11", "Dansk", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithTargetAudienceOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "test", "Dansk", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithEmptyPlayDurationTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", "Dansk", 0, place, "cdGenre", null));
  }

  @Test void registerCDWithPlayDurationLeserThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", "Dansk", -1, place, "cdGenre", null));
  }

  @Test void registerCDWithEmptyLanguageTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", null, 111, place, "cdGenre", null));
  }

  @Test void registerCDWithLanguageAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", "11", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithLanguageOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", "test", 111, place, "cdGenre", null));
  }

  @Test void registerCDWithEmptyPlaceTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", "Dansk", 111, null, "cdGenre", null));
  }

  @Test void registerCDWithEmptyGenreTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", "Dansk", 111, place, null, null));
  }

  @Test void registerCDWithGenreAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", "Dansk", 111, place, "11", null));
  }
  // Register a material of type CD section ends

  // Register a material of type DVD section starts
  @Test void registerDVDTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertDoesNotThrow(() -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "Dansk", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithEmptyTitleTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD(null, "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "Dansk", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithEmptyPublisherTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", null, "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "Dansk", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithEmptyReleaseDateTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", null, "dvdDescription",
            "dvdTages", "Voksen", "Dansk", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithEmptyDescriptionTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", null,
            "dvdTages", "Voksen", "Dansk", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registeDVDWithEmptyKeywordsTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            null, "Voksen", "Dansk", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithKeywordsAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "11", "Voksen", "Dansk", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithEmptyTargetAudienceTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", null, "Dansk", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithTargetAudienceAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "11", "Dansk", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithTargetAudienceOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "test", "Dansk", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithEmptyPlayDurationTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "Dansk", "Dansk", 0, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithPlayDurationLeserThan0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerCD("cdTitle", "cdpublisher", "2006-02-02", "cdDescription",
            "cdTages", "Voksen", "Dansk", -1, place, "cdGenre", null));
  }

  @Test void registerDVDWithEmptyLanguageTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", null, "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithLanguageAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "11", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithLanguageOutOfCheckTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "test", "Dansk", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithEmptySubtitleLanguageTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "Dansk", null, 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithSubtitleLanguageAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "Dansk", "11", 111, place, "dvdGenre",
            null));
  }

  @Test void registerDVDWithEmptyPlaceTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "Dansk", "Dansk", 111, null, "dvdGenre",
            null));
  }

  @Test void registerDVDWithEmptyGenreTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "Dansk", "Dansk", 111, place, null,
            null));
  }

  @Test void registerDVDWithGenreAsAnIntegerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> materialModelServer
        .registerDVD("dvdTitle", "dvdpublisher", "2000-01-01", "dvdDescription",
            "dvdTages", "Voksen", "Dansk", "Dansk", 111, place, "11",
            null));
  }
  // Register a material of type DVD section ends
}