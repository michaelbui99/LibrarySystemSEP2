package database.user.borrower;

import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.person.Address;
import shared.person.borrower.Borrower;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BorrowerImplTest
{
  private Address address;
  private BorrowerDAO borrowerDAO;
  private DatabaseBuilder databaseBuilder;

  @BeforeEach void setup()
  {
    address = new Address(1, "StreetTest", "1S", 1254, "cityTest");
    borrowerDAO = BorrowerImpl.getInstance();
    databaseBuilder = new DatabaseBuilder();
  }

  @Test void createBorrowerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertDoesNotThrow(() -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
            address, "123"));
  }

  @Test void createBorrowerWhereCprNumberConsistOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("qqqqqq-wwww", "fTest", "lTest", "e@test", "+4577788812",
            address, "123"));

  }

  @Test void createBorrowerWhereCprNumberConsistOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("qqqq11-w22w", "fTest", "lTest", "e@test", "+4577788812",
            address, "123"));

  }

  @Test void createBorrowerWhereCprNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("123456-12345", "fTest", "lTest", "e@test", "+4577788812",
            address, "123"));
  }

  @Test void createBorrowerWhereCprNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("123456-123", "fTest", "lTest", "e@test", "+4577788812",
            address, "123"));
  }

  @Test void createBorrowerWhereCprNumberDoesNotIncludeDashSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> borrowerDAO
        .create("123456 1234", "fTest", "lTest", "e@test", "+4577788812",
            address, "123"));
  }

  @Test void createBorrowerWhereFirstNameIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", null, "lTest", "e@test", "+4577788812", address,
            "123"));
  }

  @Test void createBorrowerWhereFirstNameConsistOfNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "123", "lTest", "e@test", "+4577788812", address,
            "123"));
  }

  @Test void createBorrowerWhereFirstNameConsistOfACombinationOfNumbersAndLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "1a3", "lTest", "e@test", "+4577788812", address,
            "123"));
  }

  @Test void createBorrowerWhereLastNameIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", null, "e@test", "+4577788812", address,
            "123"));
  }

  @Test void createBorrowerWhereLastNameConsistOfNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "123", "e@test", "+4577788812", address,
            "123"));
  }

  @Test void createBorrowerWhereLastNameConsistOfACombinationOfNumbersAndLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "1a3", "e@test", "+4577788812", address,
            "123"));
  }

  @Test void createBorrowerWhereEmailIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", null, "+4577788812", address,
            "123"));
  }

  @Test void crateBorrowerWhereEmailDoesNotContainTheAtSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", "test", "+4577788812", address,
            "123"));
  }

  @Test void createBorrowerWherePhoneNumberIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", "e@test", null, address,
            "123"));
  }

  @Test void createBorrowerWherePhoneNumberConsistsOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", "e@test", "+45aaaaaaaa",
            address, "123"));
  }

  @Test void createBorrowerWherePhoneNumberConsistsOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", "e@test", "+45a1a1a1a1",
            address, "123"));
  }

  @Test void createBorrowerWherePhoneNumberDoesNotIncludePlus45Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", "e@test", "11111111111",
            address, "123"));
  }

  @Test void createBorrowerWherePhoneNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", "e@test", "+45777888122",
            address, "123"));
  }

  @Test void createBorrowerWherePhoneNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", "e@test", "+457778881",
            address, "123"));
  }

  @Test void createBorrowerWhereAddressIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", "e@test", "+4577788812", null,
            "123"));
  }

  @Test void createBorrowerWherePasswordIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
            address, null));
  }

  @Test void borrowerLoginReturnTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertTrue(borrowerDAO.loginBorrower("111222-4777", "123"));
  }

  @Test void borrowerLoginReturnFalseTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertFalse(borrowerDAO.loginBorrower("333333-3333", "www"));
  }

  @Test void borrowerLoginWithCprNumberConsistentOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.loginBorrower("aaaaaa-bbbb", "123"));
  }

  @Test void borrowerLoginWithCprNumberConsistentOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.loginBorrower("1a1a1a-1a1a", "123"));
  }

  @Test void borrowerLoginWithCprNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.loginBorrower("123456-12345", "1234"));
  }

  @Test void borrowerLoginWithCprNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.loginBorrower("123456-123", "1234"));
  }

  @Test void borrowerLoginWithCprNumberDoesNotIncludeDashSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.loginBorrower("123451234", "1234"));
  }

  @Test void borrowerLoginWherePasswordIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.loginBorrower("123456-1234", null));
  }

  @Test void getBorrowerWhereCprNumberIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(NullPointerException.class,
        () -> borrowerDAO.getBorrower(null));
  }

  @Test void getBorrowerTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertDoesNotThrow(() -> borrowerDAO.getBorrower("111222-4777"));
  }

  @Test void getBorrowerWhereCprNumberConsistentOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.getBorrower("aaaaaa-aaaa"));
  }

  @Test void getBorrowerWhereCprNumberConsistentOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.getBorrower("a1a1a1-a1a1"));
  }

  @Test void getBorrowerWhereCprNumberConsistentOfLessThan11CharacterTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.getBorrower("123456-12345"));
  }

  @Test void getBorrowerWhereCprNumberConsistentOfMoreThan11CharacterTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.getBorrower("123456-123456"));
  }

  @Test void getBorrowerWhereCprNumberDoesNotContainDashSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.getBorrower("1234561234"));
  }

  @Test void getBorrowerWhereCprNumberDoesNotExistsTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(NoSuchElementException.class,
        () -> borrowerDAO.getBorrower("555555-6666"));
  }

  @Test void borrowerCprNumberAlreadyExistsReturnsTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertTrue(borrowerDAO.borrowerCprNumberAlreadyExists("111222-4777"));
  }

  @Test void borrowerCprNumberAlreadyExistsReturnsFalseTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertFalse(borrowerDAO.borrowerCprNumberAlreadyExists("111222-4775"));
  }

  @Test void borrowerCprNumberAlreadyExistsWhereCprNumberIsNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(NullPointerException.class,
        () -> borrowerDAO.borrowerCprNumberAlreadyExists(null));

  }

  @Test void borrowerCprNumberAlreadyExistsWhereCprNumberConsistentOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerCprNumberAlreadyExists("aaaaaa-aaaa"));
  }

  @Test void borrowerCprNumberAlreadyExistsWhereCprNumberConsistentOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerCprNumberAlreadyExists("a1a1a1-a1a1"));
  }

  @Test void borrowerCprNumberAlreadyExistsWhereCprNumberConsistentOfLessThan11CharacterTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerCprNumberAlreadyExists("123456-12345"));
  }

  @Test void borrowerCprNumberAlreadyExistsWhereCprNumberConsistentOfMoreThan11CharacterTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerCprNumberAlreadyExists("123456-123456"));
  }

  @Test void borrowerCprNumberAlreadyExistsWhereCprNumberDoesNotContainDashSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerCprNumberAlreadyExists("1234561234"));
  }

  @Test void borrowerEmailAddressAlreadyExistsReturnsTrueTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertTrue(borrowerDAO.borrowerEmailAlreadyExists("e@test"));
  }

  @Test void borrowerEmailAddressAlreadyExistsReturnsFalseTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertFalse(borrowerDAO.borrowerEmailAlreadyExists("d@test"));
  }

  @Test void borrowerEmailAddressAlreadyExistsWhereEmailAddressIsNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerEmailAlreadyExists(null));
  }

  @Test void borrowerEmailAddressAlreadyExistsWhereEmailDoesNotIncludeTheAtSignNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerEmailAlreadyExists("test"));
  }

  @Test void borrowerPhoneNumberAlreadyExistsReturnsTrueTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertTrue(borrowerDAO.borrowerPhoneNumberAlreadyExists("+4577788812"));
  }

  @Test void borrowerPhoneNumberAlreadyExistsReturnsFalseTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertFalse(borrowerDAO.borrowerPhoneNumberAlreadyExists("+4577788811"));
  }

  @Test void borrowerPhoneNumberAlreadyExistsWherePhoneNumberConsistsOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerPhoneNumberAlreadyExists("aaaaaaaaaaa"));
  }

  @Test void borrowerPhoneNumberAlreadyExistsWherePhoneNumberConsistsOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerPhoneNumberAlreadyExists("1a1a1a1a1a1"));
  }

  @Test void borrowerPhoneNumberAlreadyExistsWherePhoneNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerPhoneNumberAlreadyExists("+45777888122"));
  }

  @Test void borrowerPhoneNumberAlreadyExistsWherePhoneNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerPhoneNumberAlreadyExists("+457778881"));
  }

  @Test void borrowerPhoneNumberAlreadyExistsWherePhoneNumberDoesNotIncludeThePlus45Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerPhoneNumberAlreadyExists("11177788812"));
  }

  @Test void borrowerAlreadyExistsReturnsTrueTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertTrue(borrowerDAO
        .borrowerAlreadyExists("111222-4777", "e@test", "+4577788812"));
  }

  @Test void borrowerAlreadyExistsReturnsFalseOnCprTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertFalse(borrowerDAO
        .borrowerAlreadyExists("111222-4774", "e@test", "+4577788812"));
  }

  @Test void borrowerAlreadyExistsReturnsFalseOnEmailTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertFalse(borrowerDAO
        .borrowerAlreadyExists("111222-4777", "eW@test", "+4577788812"));
  }

  @Test void borrowerAlreadyExistsReturnsFalseOnPhoneNumberTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertFalse(borrowerDAO
        .borrowerAlreadyExists("111222-4777", "e@test", "+4577788813"));
  }

  @Test void borrowerAlreadyExistsWhereCprNumberIsNullTest() throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(NullPointerException.class,
        () -> borrowerDAO.borrowerAlreadyExists(null, "e@test", "+4577788812"));
  }

  @Test void borrowerAlreadyExistsWhereCprNumberConsistsOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .borrowerAlreadyExists("aaaaaaa-aaaa", "e@test", "+4577788812"));
  }

  @Test void borrowerAlreadyExistsWhereCprNumberConsistsOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .borrowerAlreadyExists("1a1a1a-1a1a", "e@test", "+4577788812"));
  }

  @Test void borrowerAlreadyExistsWhereCprNumberDoesNotIncludeTheDashSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .borrowerAlreadyExists("1112224777", "e@test", "+4577788812"));
  }

  @Test void borrowerAlreadyExistsWhereEmailAddressInNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .borrowerAlreadyExists("111222-4777", null, "+4577788812"));
  }

  @Test void borrowerAlreadyExistsWhereEmailAddressDoesNotIncludeTheAtSignTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .borrowerAlreadyExists("111222-4777", "etest", "+4577788812"));
  }

  @Test void borrowerAlreadyExistsWherePhoneNumberIsNullTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class,
        () -> borrowerDAO.borrowerAlreadyExists("111222-4777", "e@test", null));
  }

  @Test void borrowerAlreadyExistsWherePhoneNumberConsistsOfLettersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .borrowerAlreadyExists("111222-4777", "e@test", "aaaaaaaaaaaa"));
  }

  @Test void borrowerAlreadyExistsWherePhoneNumberConsistsOfACombinationOfLettersAndNumbersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .borrowerAlreadyExists("111222-4777", "e@test", "1a1a1a1a1a1"));
  }

  @Test void borrowerAlreadyExistsWherePhoneNumberConsistsOfMoreThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .borrowerAlreadyExists("111222-4777", "e@test", "+45777888121"));
  }

  @Test void borrowerAlreadyExistsWherePhoneNumberConsistsOfLessThan11CharactersTest()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .borrowerAlreadyExists("111222-4777", "e@test", "+457778881"));
  }

  @Test void borrowerAlreadyExistsWherePhoneNumberDoesNotIncludePlus45Test()
      throws SQLException
  {
    databaseBuilder.createDummyDataWithoutInfo();
    borrowerDAO.create("111222-4777", "fTest", "lTest", "e@test", "+4577788812",
        address, "123");
    assertThrows(IllegalArgumentException.class, () -> borrowerDAO
        .borrowerAlreadyExists("111222-4777", "e@test", "11177788812"));
  }
}