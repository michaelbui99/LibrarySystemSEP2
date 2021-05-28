package server.model.user;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.person.Address;
import shared.person.borrower.Borrower;
import shared.person.librarian.Librarian;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class UserModelManagerServerTest
{
  private UserModelServer userModelServer;
  private DatabaseBuilder databaseBuilder;
  private Address address;

  @BeforeEach void setup()
  {
    userModelServer = new UserModelManagerServer();
    databaseBuilder = new DatabaseBuilder();
    address = new Address("CityTest", "StreetNameTest", 1111, "S1");
  }

  //Register a user of type borrower section starts
  @Test void registerBorrowerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertDoesNotThrow(() -> userModelServer
        .create("111111-1111", "fNameTest", "lNameTest", "email@test1",
            "+4511111111", address, "1234"));
  }

  @Test void rgisterBorrowerWithEmptyCprNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create(null, "fNameTest", "lNameTest", "email@test2", "+4511111112",
            address, "1234"));
  }

  @Test void rgisterBorrowerWithCprNumberLengthLargerThan11Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("111111-11111", "fNameTest", "lNameTest", "email@test3",
            "+4511111113", address, "1234"));
  }

  @Test void rgisterBorrowerWithCprNumberLengthLeserThan11Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("111111-111", "fNameTest", "lNameTest", "email@test4",
            "+4511111114", address, "1234"));
  }

  @Test void rgisterBorrowerWithCprNumberWithLettersTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("aaaaaa-aaaa", "fNameTest", "lNameTest", "email@test5",
            "+4511111115", address, "1234"));
  }

  @Test void rgisterBorrowerWithCprNumberWithoutDashTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("11111111111", "fNameTest", "lNameTest", "email@test6",
            "+4511111116", address, "1234"));
  }

  @Test void registerBorrowerWithEmptyFirstNameTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("111111-1112", null, "lNameTest", "email@test7", "+4511111117",
            address, "1234"));
  }

  @Test void registerBorrowerWithEmptyLastNameTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("111111-1113", "fNameTist", null, "email@test8", "+4511111118",
            address, "1234"));
  }

  @Test void registerBorrowerWithEmptyEmailTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("111111-1114", "fNameTest", "lNameTest", null, "+4511111119",
            address, "1234"));
  }

  @Test void registerBorrowerWithEmailWithoutAtSignTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("111111-1115", "fNameTest", "lNameTest", "email", "+4511111121",
            address, "1234"));
  }

  @Test void registerBorrowerWithEmptyPhoneNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("111111-1116", "fNameTest", "lNameTest", "email@test9", null,
            address, "1234"));
  }

  @Test void registerBorrowerWithPhoneNumberWithoutPlus45Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("111111-1117", "fNameTest", "lNameTest", "email@test10",
            "11111111131", address, "1234"));
  }

  @Test void registerBorrwerWithEmptyPasswordTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .create("111111-1118", "fNameTest", "lNameTest", "email@test11",
            "+4511111141", address, null));
  }
  //Register a user of type borrower section ends

  //Register a user of type librarian section starts
  @Test void registerLibrarianTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertDoesNotThrow(() -> userModelServer
        .registerLibrarian(1, "fNameTest", "lNameTest", "111111-1111",
            "+4511111111", "email@test", address, "1234"));
  }

  @Test void registerLibrarianWithEmptyEmployeeNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(0, "fNameTest", "lNameTest", "111111-1112",
            "+4511111112", "email@test1", address, "1234"));
  }

  @Test void registerLibrarianWithEmployeeNumberLeserThan0Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(-1, "fNameTest", "lNameTest", "111111-1113",
            "+4511111113", "email@test2", address, "1234"));
  }

  @Test void registerLibrarianWithEmptyFirstNameTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(2, null, "lNameTest", "111111-1113", "+4511111113",
            "email@test2", address, "1234"));
  }

  @Test void registerLibrarianWithEmptyLastNameTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(3, "fNameTest", null, "111111-1114", "+4511111114",
            "email@test3", address, "1234"));
  }

  @Test void registerLibrarianWithEmptyEmailTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(4, "fNameTest", "lNameTest", "111111-1115",
            "+4511111115", null, address, "1234"));
  }

  @Test void registerLibrarianWithEmailWithoutAtSignTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(5, "fNameTest", "lNameTest", "111111-1116",
            "+4511111116", "emailtest4", address, "1234"));
  }

  @Test void registerLibrarianWithEmptyPhoneNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(6, "fNameTest", "lNameTest", "111111-1117", null,
            "email@test5", address, "1234"));
  }

  @Test void registerLibrarianWithPhoneNumberWithoutPlus45Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(7, "fNameTest", "lNameTest", "111111-1117",
            "11111111117", "email@test6", address, "1234"));
  }

  @Test void registerLibrarianWithEmptyPasswordTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(8, "fNameTest", "lNameTest", "111111-1118",
            "+4511111118", "email@test7", address, null));
  }

  @Test void rgisterLibrarianWithEmptyCprNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(9, "fNameTest", "lNameTest", null, "+4511111119",
            "email@test8", address, "1234"));
  }

  @Test void rgisterLibrarianWithCprNumberLengthLargerThan11Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(10, "fNameTest", "lNameTest", "111111-11181",
            "+4511111119", "email@test8", address, "1234"));
  }

  @Test void rgisterLibrarianWithCprNumberLengthLeserThan11Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(11, "fNameTest", "lNameTest", "111111-111",
            "+4511111121", "email@test9", address, "1234"));
  }

  @Test void rgisterLibrarianWithCprNumberWithLettersTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(12, "fNameTest", "lNameTest", "aaaaaa-aaaa",
            "+4511111131", "email@test10", address, "1234"));
  }

  @Test void rgisterLibrarianWithCprNumberWithoutDashTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> userModelServer
        .registerLibrarian(13, "fNameTest", "lNameTest", "11111111119",
            "+4511111141", "email@test11", address, "1234"));
  }
  //Register a user of type librarian section ends

  //Login test starts
  @Test void borrowerLogInReturnsTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Borrower borrower = userModelServer
        .create("111111-1119", "fNameTest", "lNameTest", "email@test12",
            "+4511111151", address, "1234");
    assertTrue(userModelServer
        .logInBorrower(borrower.getCpr(), borrower.getPassword()));
  }

  @Test void borrowerLogInReturnsFalseOnWrongCprNumberTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Borrower borrower = userModelServer
        .create("111111-1121", "fNameTest", "lNameTest", "email@test13",
            "+4511111161", address, "1234");
    assertFalse(
        userModelServer.logInBorrower("111111-1120", borrower.getPassword()));
  }

  @Test void borrowerLogInReturnsFalseOnWrongPasswordTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Borrower borrower = userModelServer
        .create("111111-1131", "fNameTest", "lNameTest", "email@test14",
            "+4511111171", address, "1234");
    assertFalse(userModelServer.logInBorrower(borrower.getCpr(), "test"));
  }

  @Test void borrowerLogInReturnsFalseOnWrongPasswordAndCprNumberTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Borrower borrower = userModelServer
        .create("111111-1141", "fNameTest", "lNameTest", "email@test15",
            "+4511111181", address, "1234");
    assertFalse(userModelServer.logInBorrower("111111-1120", "test"));
  }

  @Test void librarianLoginReturnsTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Librarian librarian = userModelServer
        .registerLibrarian(14, "fNameTest", "lNameTest", "111111-1121",
            "+4511111151", "email@test12", address, "1234");
    assertTrue(userModelServer
        .librarianLogin(librarian.getEmployee_no(), librarian.getPassword()));
  }

  @Test void librarianLoginReturnsFalseOnWrongEmployeeNumberTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Librarian librarian = userModelServer
        .registerLibrarian(15, "fNameTest", "lNameTest", "111111-1131",
            "+4511111161", "email@test13", address, "1234");
    assertFalse(
        userModelServer.librarianLogin(1000000, librarian.getPassword()));
  }

  @Test void librarianLoginReturnsFalseOnWrongPasswordTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Librarian librarian = userModelServer
        .registerLibrarian(16, "fNameTest", "lNameTest", "111111-1141",
            "+4511111171", "email@test14", address, "1234");
    assertFalse(
        userModelServer.librarianLogin(librarian.getEmployee_no(), "test"));
  }

  @Test void librarianLoginReturnsFalseOnWrongEmployeeNumberAndPasswordTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Librarian librarian = userModelServer
        .registerLibrarian(17, "fNameTest", "lNameTest", "111111-1151",
            "+4511111181", "email@test15", address, "1234");
    assertFalse(userModelServer.librarianLogin(1000000, "test"));
  }
  //Login test ends

  //User already in system test starts
  @Test void borrowerCprNumperAlreadyExistInSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Borrower borrower = userModelServer
        .create("111111-1151", "fNameTest", "lNameTest", "email@test16",
            "+4511111191", address, "1234");
    assertTrue(
        userModelServer.borrowerCprNumberAlreadyExists(borrower.getCpr()));
  }

  @Test void borrowerEmailAlreadyExistsInTheSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Borrower borrower = userModelServer
        .create("111111-1161", "fNameTest", "lNameTest", "email@test17",
            "+4511111211", address, "1234");
    assertTrue(userModelServer.borrowerEmailAlreadyExists(borrower.getEmail()));
  }

  @Test void borrowerPhoneNumberAlreadyExistsInTheSystemTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Borrower borrower = userModelServer
        .create("111111-1171", "fNameTest", "lNameTest", "email@test18",
            "+4511111311", address, "1234");
    assertTrue(userModelServer
        .borrowerPhoneNumberAlreadyExists(borrower.getTlfNumber()));
  }

  @Test void borrowerAlreadyExistsInTheSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Borrower borrower = userModelServer
        .create("111111-1181", "fNameTest", "lNameTest", "email@test19",
            "+4511111411", address, "1234");
    assertTrue(userModelServer
        .borrowerAlreadyExists(borrower.getCpr(), borrower.getEmail(),
            borrower.getTlfNumber()));
  }

  @Test void librarianCprNumberAlreadyExistsInTheSystemTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Librarian librarian = userModelServer
        .registerLibrarian(18, "fNameTest", "lNameTest", "111111-1161",
            "+4511111191", "email@test16", address, "1234");
    assertTrue(
        userModelServer.librarianCprNumberAlreadyExists(librarian.getCpr()));
  }

  @Test void librarianEmailAlreadyExistsInTheSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Librarian librarian = userModelServer
        .registerLibrarian(19, "fNameTest", "lNameTest", "111111-1171",
            "+4511111211", "email@test17", address, "1234");
    assertTrue(
        userModelServer.librarianEmailAlreadyExists(librarian.getEmail()));
  }

  @Test void librarianPhoneNumberAlreadyExistsInTheSystemTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Librarian librarian = userModelServer
        .registerLibrarian(20, "fNameTest", "lNameTest", "111111-1181",
            "+4511111311", "email@test18", address, "1234");
    assertTrue(userModelServer
        .librarianPhoneNumberAlreadyExists(librarian.getTlfNumber()));
  }

  @Test void librarianAlreadyExistsInTheSystemTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    Librarian librarian = userModelServer
        .registerLibrarian(21, "fNameTest", "lNameTest", "111111-1191",
            "+4511111411", "email@test19", address, "1234");
    assertTrue(userModelServer
        .librarianAlreadyExists(librarian.getEmployee_no(), librarian.getCpr(),
            librarian.getEmail(), librarian.getTlfNumber()));
  }
  //User already in system test ends

  @Test void getBorrowerByCPRBorrowerExists() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    Borrower borrower = userModelServer.getBorrowerByCPR("111111-1111");
    assertEquals("111111-1111", borrower.getCpr());
    assertEquals("Michael", borrower.getFirstName());
    assertEquals("Bui", borrower.getLastName());
    assertEquals("Axelborg", borrower.getAddress().getStreetName());
    assertEquals("8", borrower.getAddress().getStreetNr());
    assertEquals(8700, borrower.getAddress().getZipCode());
    assertEquals("Horsens", borrower.getAddress().getCity());
    assertEquals("+4512345678", borrower.getTlfNumber());
    assertEquals("michael@gmail.com", borrower.getEmail());
  }

  @Test void getBorrowerByCPRThrowsNoSuchElementException() throws SQLException
  {
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
    assertThrows(NoSuchElementException.class, ()->userModelServer.getBorrowerByCPR("111111-1122"));
  }
}