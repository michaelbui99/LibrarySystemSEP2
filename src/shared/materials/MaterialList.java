package shared.materials;

import shared.materials.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.List;

public class MaterialList
{
  private List<Material> materials;
  private SearchStrategy searchStrategy;

  public MaterialList()
  {
   materials = new ArrayList<>();
  }

  public void addMaterial(Material material)
  {
    materials.add(material);
  }
  

  public List getAllMaterial()
  {
    return this.materials;
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

//  public void getAllAvailableBooks(String title)
//  {
//
//  }

  public int size(){
    return this.materials.size();
  }

  //public void setMaterialFilterStrategy(
    //  MaterialFilterStrategy materialFilterStrategy)
  //{
    //this.materialFilterStrategy = materialFilterStrategy;
  //}
}
