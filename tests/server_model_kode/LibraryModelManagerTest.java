package server_model_kode;

import client.model.material.Material;
import client.model.material.reading.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.LibraryModelManager;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LibraryModelManagerTest
{
   LibraryModelManager libraryModelManager;
   Material material;

   @BeforeEach
   void setUp(){

     libraryModelManager = new LibraryModelManager();
     material = new Book(2,2,"HELLO","Kocarian","2012-10-10","best book",
         "science","Voksen","Engelsk","123",200, 1);
   }

   @Test
    void registerLoanTest() throws SQLException
   {

     libraryModelManager.registerLoan(libraryModelManager.getBook(1),"111111-1111","2021-05-06");
    //we need to write a function to get the loan by materialId and cpr
    }

   @Test
  void registerBookTest() throws SQLException
    {
     libraryModelManager.registerBook("aa", "bb", "2020-01-01", "cc", "dd", "Voksen", "Dansk", "123456", 500,
         1,1,"hh", null);

     assertEquals(1,this.libraryModelManager.getBook(1).getMaterialID());
    }

    @Test
  void createBookCopyTest(){
    libraryModelManager.createBookCopy(8);

    }
    @Test
  void registerCdTest() throws SQLException
    {
     libraryModelManager.registerCD("aa","me", "2020-01-01", "lala", "bla", "Voksen", "Dansk", 22.5, 1,"sd", null);
    }

    @Test
  void createCdCopyTest(){
    libraryModelManager.createBookCopy(8);

    }
    @Test
  void registerDvdTest() throws SQLException
    {
     libraryModelManager.registerDVD("aa","me", "2020-01-01", "lala", "bla", "Voksen", "Dansk", "Dansk", 1.1, 1, "rrr", null);
    }

    @Test
  void createCdDvdTest(){
    libraryModelManager.createBookCopy(8);

    }


    @Test
  void searchMaterialTest(){

     assertEquals(4,libraryModelManager.searchMaterial("" + material.getMaterialID()));

    }


}
