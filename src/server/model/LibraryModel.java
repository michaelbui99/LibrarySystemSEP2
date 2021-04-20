package server.model;

import client.model.loan.Loaner;
import client.model.material.Material;
import shared.PropertyChangeSubject;

public interface LibraryModel extends PropertyChangeSubject
{
  /**
   * Registers a new Loan for the given material and loaner.
   * @param material material is the Material the loaner wants to loan.
   * @param loanerCPR loanerCPR is the CPR which the material will be bound to in the system for the given copy of material.
   * @param deadline deadline is the deadline for when the material must be returned to the library.
   * @exception IllegalStateException if the material is is not available for loan.
   * */
  void registerLoan(Material material, String loanerCPR, String deadline) throws IllegalStateException;

  /**
  * Registers a new BookCopy in the Database and creates a new Book object which is stored in MaterialList.
  *
  * */
  void registerBook(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount);
  void searchMaterial(String arg);
}
