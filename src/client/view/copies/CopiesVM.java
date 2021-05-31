package client.view.copies;

import client.model.material.MaterialModelClient;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.materials.Material;
import shared.materials.strategy.*;
import shared.util.EventTypes;

public class CopiesVM
{
  private ObservableList<Material> materialList;
  private ObservableList<String> materialTypeList;
  private ObservableList<String> targetAudienceList;
  private ObservableList<String> languageList;

  private ObjectProperty<Material> selectedMaterialProperty;
  private StringProperty titleProperty;
  private StringProperty genreProperty;
  private StringProperty keywordsProperty;
  private StringProperty typeProperty;
  private StringProperty targetAudienceProperty;
  private StringProperty languageProperty;
  private MaterialModelClient materialModel;

  //Kutaiba
  public CopiesVM(MaterialModelClient materialModel)
  {
    this.materialModel = materialModel;

    materialList = FXCollections.observableArrayList();
    materialTypeList = FXCollections.observableArrayList();
    targetAudienceList = FXCollections.observableArrayList();
    languageList = FXCollections.observableArrayList();

    materialTypeList.addAll("Bog", "Ebog", "Lydbog", "CD", "DVD");
    targetAudienceList
        .addAll("Voksen", "Barn", "Teenager", "Familie", "Ældre", "Studerende");
    languageList.addAll("Dansk", "Engelsk", "Arabisk");

    selectedMaterialProperty = new SimpleObjectProperty<>();

    titleProperty = new SimpleStringProperty("");
    genreProperty = new SimpleStringProperty("");
    keywordsProperty = new SimpleStringProperty("");
    typeProperty = new SimpleStringProperty("Bog");
    targetAudienceProperty = new SimpleStringProperty("Voksen");
    languageProperty = new SimpleStringProperty("Dansk");
  }

  private ObservableList<Material> getMaterialList()
  {
    return materialList;
  }

  public ObservableList<String> getMaterialTypeList()
  {
    return materialTypeList;
  }

  public ObservableList<String> getTargetAudienceList()
  {
    return targetAudienceList;
  }

  public ObservableList<String> getLanguageList()
  {
    return languageList;
  }

  public StringProperty titleProperty()
  {
    return titleProperty;
  }

  public StringProperty genreProperty()
  {
    return genreProperty;
  }

  public StringProperty keywordsProperty()
  {
    return keywordsProperty;
  }

  public StringProperty typeProperty()
  {
    return typeProperty;
  }

  public StringProperty targetAudienceProperty()
  {
    return targetAudienceProperty;
  }

  public StringProperty languageProperty()
  {
    return languageProperty;
  }

  public ObjectProperty<Material> selectedMaterialProperty()
  {
    return selectedMaterialProperty;
  }

  public ObservableList<Material> search()
  {
    SearchStrategy searchStrategy = null;

    switch (typeProperty.getValue())
    {
      case "Bog":
        searchStrategy = new BookStrategy();
        break;
      case "Ebog":
        searchStrategy = new EBookStrategy();
        break;
      case "Lydbog":
        searchStrategy = new AudioBookStrategy();
        break;
      case "CD":
        searchStrategy = new CDStrategy();
        break;
      case "DVD":
        searchStrategy = new DVDStrategy();
        break;
    }

    materialList.clear();

    if (materialModel
        .findMaterial(titleProperty.get(), languageProperty.get(),
            keywordsProperty.get(), genreProperty.get(),
            targetAudienceProperty.get(), searchStrategy) != null)
    {
      materialList.addAll(
          materialModel
              .findMaterial(titleProperty.get(), languageProperty.get(),
                  keywordsProperty.get(), genreProperty.get(),
                  targetAudienceProperty.get(), searchStrategy));
      materialModel
          .addPropertyChangeListener(EventTypes.MATERIALFOUND,
              evt -> materialList.add((Material) evt.getNewValue()));
    }
    return materialList;
  }

  public void addCopy()
  {
    switch (typeProperty.getValue())
    {
      case "Bog":
        materialModel
            .createBookCopy(selectedMaterialProperty.get().getMaterialID());
        break;
      case "Ebog":
        materialModel
            .createEBookCopy(selectedMaterialProperty.get().getMaterialID());
        break;
      case "Lydbog":
        materialModel
            .createAudioBookCopy(
                selectedMaterialProperty.get().getMaterialID());
        break;
      case "CD":
        materialModel
            .createCDCopy(selectedMaterialProperty.get().getMaterialID());
        break;
      case "DVD":
        materialModel
            .createDVDCopy(selectedMaterialProperty.get().getMaterialID());
        break;
    }
  }

  public void deleteCopy()
  {
    switch (typeProperty.getValue())
    {
      case "Bog":
        materialModel
            .deleteBookCopy(selectedMaterialProperty.get().getMaterialID(),
                selectedMaterialProperty.get().getCopyNumber());
        break;
      case "Ebog":
        materialModel
            .deleteEBookCopy(selectedMaterialProperty.get().getMaterialID(),
                selectedMaterialProperty.get().getCopyNumber());
        break;
      case "Lydbog":
        materialModel
            .deleteAudioBookCopy(selectedMaterialProperty.get().getMaterialID(),
                selectedMaterialProperty.get().getCopyNumber());
        break;
      case "CD":
        materialModel
            .deleteCDCopy(selectedMaterialProperty.get().getMaterialID(),
                selectedMaterialProperty.get().getCopyNumber());
        break;
      case "DVD":
        materialModel
            .deleteDVDCopy(selectedMaterialProperty.get().getMaterialID(),
                selectedMaterialProperty.get().getCopyNumber());
        break;
    }
  }

  public void deleteMaterial()
  {
    materialModel
        .deleteMaterial(selectedMaterialProperty.get().getMaterialID());
  }

  public int totalCopyNumber()
  {
    return materialModel
        .totalNumberOfCopies(selectedMaterialProperty.get().getMaterialID());
  }
}
