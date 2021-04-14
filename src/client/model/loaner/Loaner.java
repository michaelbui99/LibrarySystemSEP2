package client.model.loaner;

import client.model.material.Material;

public interface Loaner
{
  void loanMaterial(Material material, String cpr);
}
