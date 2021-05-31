package database.user.librarian;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.person.Address;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

//Kutaiba
class LibrarianImplTest
{
  private Address address;
  private LibrarianDAO librarianDAO;
  private DatabaseBuilder databaseBuilder;

  @BeforeEach void setup()
  {
    address = new Address(1, "StreetTest", "1S", 1254, "cityTest");
    librarianDAO = LibrarianImpl.getInstance();
    databaseBuilder = new DatabaseBuilder();
  }

  @Test void createLibrarianTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertDoesNotThrow(() -> librarianDAO
        .create(1234, "fNameTest", "lNameTest", "444444-3333", "+4512345678",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWhereEmployeeNumberEquals0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(0, "fNameTest", "lNameTest", "444444-3333", "+4512345678",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWhereEmployeeNumberIsLessThan0Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(-1, "fNameTest", "lNameTest", "444444-3333", "+4512345678",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWhereFirstNameIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, null, "lNameTest", "444444-3333", "+4512345678", "e@test",
            address, "1234"));
  }

  @Test void createLibrarianWhereFirstNameConsistsOfNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "123", "lNameTest", "444444-3333", "+4512345678", "e@test",
            address, "1234"));
  }

  @Test void createLibrarianWhereFirstNameConsistsOfACombinationOfNumbersAndLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "1a3", "lNameTest", "444444-3333", "+4512345678", "e@test",
            address, "1234"));
  }

  @Test void createLibrarianWhereLastNameIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", null, "444444-3333", "+4512345678", "e@test",
            address, "1234"));
  }

  @Test void createLibrarianWhereLastNameConsistsOfNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "123", "444444-3333", "+4512345678", "e@test",
            address, "1234"));
  }

  @Test void createLibrarianWhereLastNameConsistsOfACombinationOfNumbersAndLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "1a2", "444444-3333", "+4512345678", "e@test",
            address, "1234"));
  }

  @Test void createLibrarianWhereCprNumberIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(NullPointerException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", null, "+4512345678", "e@test",
            address, "1234"));
  }

  @Test void createLibrarianWhereCprNumberConsistsOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "aaaaaa-aaaa", "+4512345678",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWhereCprNumberConsistsOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "1a1a1a-1a1a", "+4512345678",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWhereCprNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-12345", "+4512345678",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWhereCprNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-123", "+4512345678",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWhereCprNumberDoesNotIncludeTheDashSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "1234561234", "+4512345678",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWherePhoneNumberIsNulTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", null, "e@test",
            address, "1234"));
  }

  @Test void createLibrarianWherePhoneNumberConsistsOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "aaaaaaaaaaa",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWherePhoneNumberConsistsOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "1a1a1a1a1a1",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWherePhoneNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+45123456781",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWherePhoneNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+451234567",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWherePhoneNumberDoesNotContainPlus45Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "11112345678",
            "e@test", address, "1234"));
  }

  @Test void createLibrarianWhereEmailIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            null, address, "1234"));
  }

  @Test void createLibrarianWhereEmailDoesNotIncludeTheAtSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "eTest", address, "1234"));
  }

  @Test void createLibrarianWhereAddressIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", null, "1234"));
  }

  @Test void createLibrarianWherePasswordIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, null));
  }

  @Test void librarianLogInReturnsTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertTrue(librarianDAO.librarianLogin(123, "123"));
  }

  @Test void librarianLogInReturnsFalseOnEmployeeNumberAndPasswordTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO.librarianLogin(1223, "1223"));
  }

  @Test void librarianLogInReturnsFalseOnEmployeeNumberTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO.librarianLogin(1223, "123"));
  }

  @Test void librarianLogInReturnsFalseOnPasswordTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO.librarianLogin(123, "1223"));
  }

  @Test void librarianLogInWhereEmployeeNumberEquals0Test() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianLogin(0, "123"));
  }

  @Test void librarianLogInWhereEmployeeNumberLessThan0Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianLogin(-1, "123"));

  }

  @Test void librarianLogInWherePasswordIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianLogin(123, null));
  }

  @Test void employeeNumberAlreadyExistsReturnsTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertTrue(librarianDAO.employeeNumberAlreadyExists(123));
  }

  @Test void employeeNumberAlreadyExistsReturnsFalseTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO.employeeNumberAlreadyExists(1223));
  }

  @Test void employeeNumberAlreadyExistsWhereEmployeeNumberEquals0Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.employeeNumberAlreadyExists(0));
  }

  @Test void employeeNumberAlreadyExistsWhereEmployeeNumberLessThan0Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.employeeNumberAlreadyExists(-1));
  }

  @Test void librarianCprNumberAlreadyExistsReturnsTrueTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertTrue(librarianDAO.librarianCprNumberAlreadyExists("123456-1234"));
  }

  @Test void librarianCprNumberAlreadyExistsReturnsFalseTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO.librarianCprNumberAlreadyExists("123456-1244"));
  }

  @Test void librarianCprNumberAlreadyExistsWhereCprNumberIsNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(NullPointerException.class,
        () -> librarianDAO.librarianCprNumberAlreadyExists(null));
  }

  @Test void librarianCprNumberAlreadyExistsWhereCprNumberConsistsLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianCprNumberAlreadyExists("aaaaaa-aaaa"));
  }

  @Test void librarianCprNumberAlreadyExistsWhereCprNumberConsistsOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianCprNumberAlreadyExists("1a1a1a-1a1a"));
  }

  @Test void librarianCprNumberAlreadyExistsWhereCprNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianCprNumberAlreadyExists("123456-12345"));
  }

  @Test void librarianCprNumberAlreadyExistsWhereCprNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianCprNumberAlreadyExists("123456-123"));
  }

  @Test void librarianCprNumberAlreadyExistsWhereCprNumberDoesNotIncludeTheDashSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianCprNumberAlreadyExists("1234561234"));
  }

  @Test void librarianEmailAlreadyExistsReturnsTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertTrue(librarianDAO.librarianEmailAlreadyExists("e@Test"));
  }

  @Test void librarianEmailAlreadyExistsReturnsFalseTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO.librarianEmailAlreadyExists("ae@Test"));
  }

  @Test void librarianEmailAlreadyExistsWhereEmailAddressIsNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianEmailAlreadyExists(null));
  }

  @Test void librarianEmailAlreadyExistsWhereEmailAddressDoesNotIncludeTheAtSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianEmailAlreadyExists("eTest"));
  }

  @Test void librarianPhoneNumberAlreadyExistsReturnsTrueTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertTrue(librarianDAO.librarianPhoneNumberAlreadyExists("+4512345678"));
  }

  @Test void librarianPhoneNumberAlreadyExistsReturnsFlaseTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO.librarianPhoneNumberAlreadyExists("+4512345618"));
  }

  @Test void librarianPhoneNumberAlreadyExistsWherePhoneNumberIsNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianPhoneNumberAlreadyExists(null));
  }

  @Test void librarianPhoneNumberAlreadyExistsWherePhoneNumberConsistsOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianPhoneNumberAlreadyExists("aaaaaaaaaaa"));
  }

  @Test void librarianPhoneNumberAlreadyExistsWherePhoneNumberConsistsOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianPhoneNumberAlreadyExists("1a1a1a1a1a1"));
  }

  @Test void librarianPhoneNumberAlreadyExistsWherePhoneNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianPhoneNumberAlreadyExists("+45123456781"));
  }

  @Test void librarianPhoneNumberAlreadyExistsWherePhoneNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianPhoneNumberAlreadyExists("+451234567"));
  }

  @Test void librarianPhoneNumberAlreadyExistsWherePhoneNumberDoesNotContainPlus45Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> librarianDAO.librarianPhoneNumberAlreadyExists("1111234567"));
  }

  @Test void librarianAlreadyExistsReturnsTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertTrue(librarianDAO
        .librarianAlreadyExists(123, "123456-1234", "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsReturnsFalseOnEmployeeNumberCprNumberEmailAddressAndPhoneNumberTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO
        .librarianAlreadyExists(1223, "123456-1231", "de@Test", "+4512345672"));
  }

  @Test void librarianAlreadyExistsReturnsFalseOnEmployeeNumberTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO
        .librarianAlreadyExists(1223, "123456-1234", "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsReturnsFalseOnCprNumberTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO
        .librarianAlreadyExists(123, "123456-1231", "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsReturnsFalseOnEmailAddressTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO
        .librarianAlreadyExists(123, "123456-1234", "Ae@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsReturnsFalseOnPhoneNumberTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertFalse(librarianDAO
        .librarianAlreadyExists(123, "123456-1234", "e@Test", "+4512345675"));
  }

  @Test void librarianAlreadyExistsWhereEmployeeNumberEquals0Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(0, "123456-1234", "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsWhereEmployeeNumberIsLessThan0Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(-1, "123456-1234", "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsWhereCprNumberIsNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(NullPointerException.class, () -> librarianDAO
        .librarianAlreadyExists(123, null, "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsWhereCprNumberConsistsOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "aaaaaa-aaaa", "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsWhereCprNumberConsistsOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "1a1a1a-1a1a", "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsWhereCprNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "123456-12345", "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsWhereCprNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "123456-123", "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsWhereCprNumberDoesNotContainTheDashSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "1234561234", "e@Test", "+4512345678"));
  }

  @Test void librarianAlreadyExistsWhereEmailAddressIsNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "123456-1234", null, "+4512345678"));
  }

  @Test void librarianAlreadyExistsWhereEmailAddressDoesNotContainTheAtSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "123456-1234", "eTest", "+4512345678"));
  }

  @Test void librarianAlreadyExistsWherePhoneNumberIsNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "123456-1234", "e@Test", null));
  }

  @Test void librarianAlreadyExistsWherePhoneNumberConsistsOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "123456-1234", "e@Test", "aaaaaaaaaaa"));
  }

  @Test void librarianAlreadyExistsWherePhoneNumberConsistsOfACombinationLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "123456-1234", "e@Test", "1a1a1a1a1a1"));
  }

  @Test void librarianAlreadyExistsWherePhoneNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "123456-1234", "e@Test", "+45123456781"));
  }

  @Test void librarianAlreadyExistsWherePhoneNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "123456-1234", "e@Test", "+451234567"));
  }

  @Test void librarianAlreadyExistsWherePhoneNumberDoesNotContainPlus45Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    librarianDAO
        .create(123, "fNameTest", "lNameTest", "123456-1234", "+4512345678",
            "e@Test", address, "123");
    assertThrows(IllegalArgumentException.class, () -> librarianDAO
        .librarianAlreadyExists(123, "123456-1234", "e@Test", "11112345678"));
  }
}