package server_model_kode;

import client.model.loan.Loan;
import client.model.material.DVD;
import client.model.material.Material;
import client.model.material.reading.Book;
import client.model.material.reading.ReadingMaterial;
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
     material = new Book(4,2,"HELLO","Kocarian","2012-10-10","best book",
         "science","Voksen","Engelsk","",200);
   }

   @Test
    void registerLoanTest(){

     libraryModelManager.registerLoan(material,"45454545","15-08-2021");
    //we need to write a function to get the loan by materialId and cpr
    }

    @Test
  void registerBookTest() throws SQLException
    {
     Book book1 =   new Book(11,2,"HELLO","Kocarian","2012-10-10","best book",
         "science","Voksen","Engelsk","",200);

     libraryModelManager.registerBook(book1.getTitle(), book1.getPublisher(), book1.getReleaseDate(), book1.getDescription(),
         "", book1.getTargetAudience(), book1.getLanguage(), book1.getIsbn(),
         book1.getPageCount(), 1);

     assertEquals(5,this.libraryModelManager.getBook(11));
    }

    @Test
  void createBookCopyTest(){
    libraryModelManager.createBookCopy(this.material.getMaterialID());

    }


    @Test
  void searchMaterialTest(){

     assertEquals(4,libraryModelManager.searchMaterial("" + material.getMaterialID()));

    }


}
