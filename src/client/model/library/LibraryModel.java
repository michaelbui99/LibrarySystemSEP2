package client.model.library;

import client.model.loaner.Loaner;
import client.model.material.Material;

public interface LibraryModel
{
  void registerLoan(Material material, String loanerCPR, String deadline);
  void registerBook(Loaner loaner, Material material);
  void searchMaterial(String arg);
}
