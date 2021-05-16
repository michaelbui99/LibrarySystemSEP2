//package server_model_kode;
//
//import shared.loan.Loan;
//import shared.materials.Material;
//import shared.materials.reading.Book;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LibraryModelManagerTest
//{
//   LibraryModelManager libraryModelManager;
//   Material material;
//
//   @BeforeEach
//   void setUp(){
//
//     libraryModelManager = new LibraryModelManager();
//     material = new Book(2,2,"HELLO","Kocarian","2012-10-10","best book",
//         "science","Voksen","Engelsk","123",200, 1,"catman");
//   }
//
//   @Test
//    void registerLoanTest()
//   {
//    Loan loan =  libraryModelManager.registerLoan(new Book(1,2,"Beer","John","2020-02-03",
//        "drinks", "corona", "Voksen", "Dansk", "sjlfhg", 100, 5,"" ),"111111-10","2021-05-10");
//    assertTrue(loan.getLoanID() > 0);
//    System.out.println("Registered loan with ID " + loan.getLoanID());
//    }
//
//   @Test
//  void registerBookTest() throws SQLException
//    {
//     libraryModelManager.registerBook("aa", "bb", "2020-01-01", "cc", "dd", "Voksen", "Dansk", "123456", 500,
//         1,1,"hh", null);
//
//     assertEquals(1,this.libraryModelManager.getBook(1).getMaterialID());
//    }
//
//    @Test
//  void createBookCopyTest(){
//    libraryModelManager.createBookCopy(8);
//
//    }
//    @Test
//  void registerCdTest() throws SQLException
//    {
//     libraryModelManager.registerCD("aa","me", "2020-01-01", "lala", "bla", "Voksen", "Dansk", 22.5, 1,"sd", null);
//    }
//
//    @Test
//  void createCdCopyTest(){
//    libraryModelManager.createBookCopy(8);
//
//    }
//    @Test
//  void registerDvdTest() throws SQLException
//    {
//     libraryModelManager.registerDVD("aa","me", "2020-01-01", "lala", "bla", "Voksen", "Dansk", "Dansk", "11:10:15", 1, "rrr", null);
//    }
//
//    @Test
//  void createCdDvdTest(){
//    libraryModelManager.createBookCopy(8);
//
//    }
//
//
//    @Test
//  void searchMaterialTest() throws SQLException
//    {
//
//      List<Material> ml = libraryModelManager.findMaterial("space", "", "", "", "", "audiobook");
//      System.out.println("material count: " + ml.size());
//      for (int i = 0; i < ml.size(); i++)
//      {
//        System.out.println(" material type: " + ml.get(i).getMaterialType());
//        System.out.println("title: " + ml.get(i).getTitle());
//        System.out.println("copies: " + ml.get(i).getCopyNumber());
//      }
//     assertEquals("space",ml.get(0).getTitle());
//    }
//
//    @Test
//  void deliverMaterialTest(){
//     assertEquals(true,libraryModelManager.deliverMaterial(9,"111111-10",23));
//     assertEquals(false,libraryModelManager.deliverMaterial(9,"111111-10",22));
//    }
//
//
//
//
//
//}
