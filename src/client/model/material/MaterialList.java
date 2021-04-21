package client.model.material;

import java.util.ArrayList;
import java.util.List;

public class MaterialList
{
  private List<Material> materials;
  private MaterialFilterStrategy materialFilterStrategy;

  public MaterialList()
  {
   materials = new ArrayList<>();
  }

  public void addMaterial(Material material)
  {
    materials.add(material);
  }


  public void getAllMaterial()
  {
  }

  public Material getMaterialById(int id)
  {
    for (Material material : materials)
    {
      if (material.getMaterialID() == id)
      {
        return material;
      }
    }
    return null;
  }

  public void getAllAvailableBooks(String title)
  {

  }

  public void setMaterialFilterStrategy(
      MaterialFilterStrategy materialFilterStrategy)
  {
    this.materialFilterStrategy = materialFilterStrategy;
  }
}
