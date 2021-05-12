package client.view.search;

import client.core.ModelFactory;
import client.model.material.Material;
import client.model.material.MaterialModelClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventTypes;

import java.util.List;

public class SearchVM
{
  private MaterialModelClient materialModelClient;
  private ObservableList<Material> foundMaterials;

  public SearchVM(MaterialModelClient materialModelClient)
  {
    this.materialModelClient = materialModelClient;
    foundMaterials = FXCollections.observableArrayList();

  }
  public ObservableList<Material> searchMaterial(String title, String language,
      String keywords, String genre, String targetAudience, String type){

   foundMaterials.addAll(materialModelClient.findMaterial(title,  language,
      keywords,  genre,  targetAudience,  type));
   materialModelClient.addPropertyChangeListener(EventTypes.MATERIALFOUND,
       evt -> foundMaterials.add((Material) evt.getNewValue()));
   return foundMaterials;
  }


  public Material getSelectMaterial()
  {
    return ModelFactory.getInstance().getMaterialModelClient()
        .getSelectMaterial();
  }

  public void setSelectMaterial(Material selectMaterial)
  {
    ModelFactory.getInstance().getMaterialModelClient().setSelectMaterial(selectMaterial);

  }


}
