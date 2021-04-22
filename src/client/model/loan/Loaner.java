package client.model.loan;

import client.model.material.Material;

public interface Loaner
{
  void loanMaterial(Material material, String cpr);
}
