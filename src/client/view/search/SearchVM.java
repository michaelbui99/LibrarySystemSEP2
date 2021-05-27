package client.view.search;

import client.core.ModelFactoryClient;
import client.model.material.strategy.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.materials.Material;
import client.model.material.MaterialModelClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventTypes;

public class SearchVM
{
  private ObservableList<Material> foundMaterials;
  private ObservableList<String> materialType;
  private ObservableList<String> materialLanguage;
  private ObservableList<String> materialAudience;


  private StringProperty titleProperty;
  private StringProperty genreProperty;
  private StringProperty keywordProperty;
  private StringProperty typeProperty;
  private StringProperty languageProperty;
  private StringProperty targetAudienceProperty;
  private StringProperty chooseTypeProperty;
  private MaterialModelClient materialModelClient;



  public SearchVM(MaterialModelClient materialModelClient)
  {
    this.materialModelClient = materialModelClient;
    foundMaterials = FXCollections.observableArrayList();
    materialType = FXCollections.observableArrayList();
    materialType.addAll("bog",
        "lydbog",
        "cd",
        "dvd",
        "ebog");
    materialLanguage = FXCollections.observableArrayList();
    materialLanguage.addAll(
        "Engelsk",
        "Dansk",
        "Arabisk"
    );
    materialAudience = FXCollections.observableArrayList();
    materialAudience.addAll("Voksen",
        "Barn",
        "Teenager",
        "Familie",
        "Ældre",
        "Studerende");

    titleProperty = new SimpleStringProperty("");
    genreProperty = new SimpleStringProperty("");
    keywordProperty = new SimpleStringProperty("");
    typeProperty = new SimpleStringProperty("");
    languageProperty = new SimpleStringProperty("Dansk");
    targetAudienceProperty = new SimpleStringProperty("Voksen");
    chooseTypeProperty = new SimpleStringProperty("bog");
  }


  public ObservableList<Material> getFoundMaterials()
  {
    return foundMaterials;
  }

  public ObservableList<String> getMaterialType()
  {
    return materialType;
  }

  public ObservableList<String> getMaterialLanguage()
  {
    return materialLanguage;
  }

  public ObservableList<String> getMaterialAudience()
  {
    return materialAudience;
  }


  public StringProperty titleProperty()
  {
    return titleProperty;
  }


  public StringProperty genreProperty()
  {
    return genreProperty;
  }


  public StringProperty keywordProperty()
  {
    return keywordProperty;
  }


  public StringProperty typeProperty()
  {
    return typeProperty;
  }

  public StringProperty languageProperty()
  {
    return languageProperty;
  }


  public StringProperty targetAudienceProperty()
  {
    return targetAudienceProperty;
  }


  public StringProperty chooseTypeProperty()
  {
    return chooseTypeProperty;
  }

  public ObservableList<Material> searchMaterial(){
    SearchStrategy searchStrategy = null;
    if (chooseTypeProperty.getValue().equals("bog")){
      searchStrategy = new BookStrategy();
    }
    else if (chooseTypeProperty.getValue().equals("lydbog")){
      searchStrategy = new AudioBookStrategy();
    }
    else if (chooseTypeProperty.getValue().equals("cd")){
      searchStrategy = new CDStrategy();
    }
    else if (chooseTypeProperty.getValue().equals("dvd")){
      searchStrategy = new DVDStrategy();
    }
    else if (chooseTypeProperty.getValue().equals("ebog")){
      searchStrategy = new EBookStrategy();
    }
    foundMaterials.clear();
    if (ModelFactoryClient.getInstance().getMaterialModelClient().findMaterial(titleProperty.get(),  languageProperty.get(),
        keywordProperty.get(),  genreProperty.get(),  targetAudienceProperty.get(),searchStrategy) != null){
      foundMaterials.addAll(ModelFactoryClient.getInstance().getMaterialModelClient().findMaterial(titleProperty.get(),  languageProperty.get(),
          keywordProperty.get(),  genreProperty.get(),  targetAudienceProperty.get(),searchStrategy));
      ModelFactoryClient.getInstance().getMaterialModelClient().addPropertyChangeListener(EventTypes.MATERIALFOUND,
          evt -> foundMaterials.add((Material) evt.getNewValue()));
    }
    return foundMaterials;
  }


  public Material getSelectMaterial()
  {
    return ModelFactoryClient.getInstance().getMaterialModelClient()
        .getSelectMaterial();
  }

  public void setSelectMaterial(Material selectMaterial)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient().setSelectMaterial(selectMaterial);

  }


}
