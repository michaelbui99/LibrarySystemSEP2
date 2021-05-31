package server.model.material;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.core.ModelFactoryServer;
import shared.materials.Place;
import shared.person.MaterialCreator;
import shared.person.librarian.Librarian;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

//Kutaiba
public class AddRemoveCopies
{
  private MaterialModelServer materialModelServer;
  private DatabaseBuilder databaseBuilder;
  private Librarian librarian;
  private MaterialCreator materialCreator;
  private Place place;

  @BeforeEach void setup()
  {
    materialModelServer = ModelFactoryServer.getInstance().getMaterialModel();
    databaseBuilder = new DatabaseBuilder();
    librarian = new Librarian(1234, "FirstName", "LastName", "111111-1111",
        "+4511111111", "email@test", null, "1234");
    materialCreator = new MaterialCreator(1, "Bob", "Bobsen", "2020-12-12",
        "Denmark");
    place = new Place(1, "A", "creatorslastName1", "genre1");
  }

  // add copies section starts
  @Test void addBookCopyToSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int bookID = materialModelServer
        .registerBook("titleC", "publisherC", "1999-01-08", "descriptionC",
            "tagesC", "Voksen", "Dansk", "1122334", 4531, place,
            materialCreator, "genreC", null);
    assertDoesNotThrow(() -> materialModelServer.createBookCopy(bookID));
    assertEquals(2, materialModelServer.totalNumberOfCopies(bookID));
  }

  @Test void addEBookCopyToSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int eBookID = materialModelServer
        .registerEBook("titleCE", "publisherCE", "1980-09-08", "descriptionCE",
            "tagesCE", "Voksen", "Dansk", 78, 22, materialCreator, "genreCE",
            null);
    assertDoesNotThrow(() -> materialModelServer.createEBookCopy(eBookID));
    assertEquals(2, materialModelServer.totalNumberOfCopies(eBookID));
  }

  @Test void addAudioBookCopyToSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int audioBookCopy = materialModelServer
        .registerAudioBook("titleCA", "publisheCA", "1970-02-04",
            "deacriptuinCA", "tagesCA", "Voksen", "Dansk", 666, "genreCA",
            materialCreator, null);
    assertDoesNotThrow(
        () -> materialModelServer.createAudioBookCopy(audioBookCopy));
    assertEquals(2, materialModelServer.totalNumberOfCopies(audioBookCopy));
  }

  @Test void addCDCopyToSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int cdID = materialModelServer
        .registerCD("titleCC", "publisherCC", "1999-08-09", "descriptionCC",
            "tagesCC", "Voksen", "Dansk", 777, place, "genreCC", null);
    assertDoesNotThrow(() -> materialModelServer.createCDCopy(cdID));
    assertEquals(2, materialModelServer.totalNumberOfCopies(cdID));
  }

  @Test void addDVDCopyToSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int dvdID = materialModelServer
        .registerDVD("titleCD", "publisherCD", "1877-01-01", "descriptionCD",
            "tagesCD", "Voksen", "Dansk", "Dansk", 900, place, "genreCD", null);
    assertDoesNotThrow(() -> materialModelServer.createDVDCopy(dvdID));
    assertEquals(2, materialModelServer.totalNumberOfCopies(dvdID));
  }
  // add copies section ends

  // remove copies section starts
  @Test void removeBookCopyFromSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int bookID = materialModelServer
        .registerBook("titleRB", "publisherRB", "1989-01-01", "descriptionRB",
            "tagesRB", "Voksen", "Dansk", "999", 98, place, materialCreator,
            "genreRB", null);
    materialModelServer.createBookCopy(bookID);
    assertDoesNotThrow(() -> materialModelServer.deleteBookCopy(bookID, 2));
    assertEquals(1, materialModelServer.totalNumberOfCopies(bookID));
  }

  @Test void removeEBookCopyFromSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int eBookID = materialModelServer
        .registerEBook("titleRE", "publisherRE", "1989-05-09", "descriptionRE",
            "tagesRE", "Voksen", "Dansk", 100, 1111, materialCreator, "genreRE",
            null);
    materialModelServer.createEBookCopy(eBookID);
    assertDoesNotThrow(() -> materialModelServer.deleteEBookCopy(eBookID, 2));
    assertEquals(1, materialModelServer.totalNumberOfCopies(eBookID));
  }

  @Test void removeAudioBookCoopyFromSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int audioBookID = materialModelServer
        .registerAudioBook("titleRA", "publisherRA", "1993-01-09",
            "descriptionRA", "tagesRA", "Voksen", "Dansk", 890, "genreRA",
            materialCreator, null);
    materialModelServer.createAudioBookCopy(audioBookID);
    assertDoesNotThrow(
        () -> materialModelServer.deleteAudiotBookCopy(audioBookID, 2));
    assertEquals(1, materialModelServer.totalNumberOfCopies(audioBookID));
  }

  @Test void removeCDCopyFormSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int cdID = materialModelServer
        .registerCD("titleRC", "publisherRC", "1976-01-01", "descriptionRC",
            "tagesRC", "Voksen", "Dansk", 89, place, "genreRC", null);
    materialModelServer.createCDCopy(cdID);
    assertDoesNotThrow(() -> materialModelServer.deleteCDCopy(cdID, 2));
    assertEquals(1, materialModelServer.totalNumberOfCopies(cdID));
  }

  @Test void removeDVDDeleteFromSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    int dvdID = materialModelServer
        .registerDVD("titleRD", "publisherRD", "1960-01-01", "descriptionRD",
            "tagesRD", "Voksen", "Dansk", "dansk", 600, place, "genreRD", null);
    materialModelServer.createDVDCopy(dvdID);
    assertDoesNotThrow(() -> materialModelServer.deleteDVDCopy(dvdID, 2));
    assertEquals(1, materialModelServer.totalNumberOfCopies(1));
  }
  // remove copies section ends
}
