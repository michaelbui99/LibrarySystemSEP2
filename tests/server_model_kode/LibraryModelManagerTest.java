package server_model_kode;

import client.model.material.Material;
import client.model.material.reading.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.LibraryModelManager;

import static org.junit.jupiter.api.Assertions.*;

class LibraryModelManagerTest
{
   LibraryModelManager libraryModelManager;
   Material material;

   @BeforeEach
   void setUp(){

     libraryModelManager = new LibraryModelManager();
     material = new Book(4,2,"HEllo","Kocarian","12-10-2010","best book",
         "science","","","",200);
   }

   @Test
    void registerLoanTest(){
     // i can't test it because i need a method to get the registered loan from database, by materialId and cpr
      libraryModelManager.registerLoan(material,"45454545","15-08-2021");

    }

    @Test
    //i need to get it from database right after it was added
  void registerBookTest(){
    libraryModelManager.registerBook(material.getMaterialID(),material.getCopyNumber(),
        material.getTitle(), material.getPublisher(), material.getReleaseDate(), material.getDescription(),material.getTags(),material.getTargetAudience(),
        material.getLanguage(),material.getIsbn(),material.getPageCount());

    }

    @Test
  void createBookCopyTest(){
     libraryModelManager.registerBook(material.getMaterialID(),material.getCopyNumber(),
         material.getTitle(), material.getPublisher(), material.getReleaseDate(), material.getDescription(),material.getTags(),material.getTargetAudience(),
         material.getLanguage(),material.getIsbn(),material.getPageCount());

     //i need a method to find the book i just register so i can test it


    }

    @Test
  void searchMaterialTest(){
     libraryModelManager.searchMaterial("4");
     assertEquals(4,material.getMaterialID());
    }

}
