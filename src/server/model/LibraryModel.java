package server.model;

import client.model.material.Material;
import shared.PropertyChangeSubject;

public interface LibraryModel extends PropertyChangeSubject
{
  /**
   * Registers a new Loan for the given material and loaner.
   *
   * @param material  material is the Material the loaner wants to loan.
   * @param loanerCPR loanerCPR is the CPR which the material will be bound to in the system for the given copy of material.
   * @param deadline  deadline is the deadline for when the material must be returned to the library.
   * @throws IllegalStateException if the material is is not available for loan.
   */
  void registerLoan(Material material, String loanerCPR, String deadline)
      throws IllegalStateException;

  /**
   * Registers a new BookCopy in the Database and creates a new Book object which is stored in MaterialList.
   *
   * @param materialID materialID is the ID for the material the book is bound to in DB.
   *                   If there does not exist an material with the ID yet
   *                   the system will auto generate a new Material in the Database with given params.
   * @param placeID
   */
  void registerBook(int materialID, String title, String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      int placeID);


  void createBookCopy(int materialID, int copyNo);


  Material searchMaterial(String arg);
}
