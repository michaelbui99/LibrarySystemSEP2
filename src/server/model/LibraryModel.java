package server.model;

import client.model.loan.Loaner;
import client.model.material.Material;
import shared.PropertyChangeSubject;

public interface LibraryModel extends PropertyChangeSubject
{
  void registerLoan(Material material, String loanerCPR, String deadline);
  void registerBook(Loaner loaner, Material material);
  void searchMaterial(String arg);
}
