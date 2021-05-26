package client.view.copies;

import client.core.ModelFactoryClient;
import client.model.material.strategy.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.materials.Material;
import shared.util.EventTypes;

public class CopiesVM
{
  private ObservableList<Material> materialList;
  private ObservableList<String> materialTypeList;
  private ObservableList<String> targetAudianceList;
  private ObservableList<String> languageList;

  private ObjectProperty<Material> selectedMaterialProperty;
  private StringProperty titleProperty;
  private StringProperty genreProperty;
  private StringProperty keywordsProperty;
  private StringProperty typeProperty;
  private StringProperty targetAudianceProperty;
  private StringProperty languageProperty;

  public CopiesVM()
  {
    materialList = FXCollections.observableArrayList();
    materialTypeList = FXCollections.observableArrayList();
    targetAudianceList = FXCollections.observableArrayList();
    languageList = FXCollections.observableArrayList();

    materialTypeList.addAll("Bog", "Ebog", "Lydbog", "CD", "DVD");
    targetAudianceList
        .addAll("Voksen", "Barn", "Teenager", "Familie", "Ældre", "Studerende");
    languageList.addAll("Dansk", "Engelsk", "Arabisk");

    selectedMaterialProperty = new SimpleObjectProperty<>();

    titleProperty = new SimpleStringProperty("");
    genreProperty = new SimpleStringProperty("");
    keywordsProperty = new SimpleStringProperty("");
    typeProperty = new SimpleStringProperty("Bog");
    targetAudianceProperty = new SimpleStringProperty("Voksen");
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

  public ObservableList<String> getTargetAudianceList()
  {
    return targetAudianceList;
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

  public StringProperty targetAudianceProperty()
  {
    return targetAudianceProperty;
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

    if (typeProperty.getValue().equals("Bog"))
    {
      searchStrategy = new BookStrategy();
    }
    else if (typeProperty.getValue().equals("Ebog"))
    {
      searchStrategy = new EBookStrategy();
    }
    else if (typeProperty.getValue().equals("Lydbog"))
    {
      searchStrategy = new AudioBookStrategy();
    }
    else if (typeProperty.getValue().equals("CD"))
    {
      searchStrategy = new CDStrategy();
    }
    else if (typeProperty.getValue().equals("DVD"))
    {
      searchStrategy = new DVDStrategy();
    }

    materialList.clear();

    if (ModelFactoryClient.getInstance().getMaterialModelClient()
        .findMaterial(titleProperty.get(), languageProperty.get(),
            keywordsProperty.get(), genreProperty.get(),
            targetAudianceProperty.get(), searchStrategy) != null)
    {
      materialList.addAll(
          ModelFactoryClient.getInstance().getMaterialModelClient()
              .findMaterial(titleProperty.get(), languageProperty.get(),
                  keywordsProperty.get(), genreProperty.get(),
                  targetAudianceProperty.get(), searchStrategy));
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .addPropertyChangeListener(EventTypes.MATERIALFOUND,
              evt -> materialList.add((Material) evt.getNewValue()));
    }
    return materialList;
  }

  public void addCopy()
  {
    if (typeProperty.getValue().equals("Bog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .createBookCopy(selectedMaterialProperty.get().getMaterialID());
    }
    else if (typeProperty.getValue().equals("Ebog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .createEBookCopy(selectedMaterialProperty.get().getMaterialID());
    }
    else if (typeProperty.getValue().equals("Lydbog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .createAudioBookCopy(selectedMaterialProperty.get().getMaterialID());
    }
    else if (typeProperty.getValue().equals("CD"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .createCDCopy(selectedMaterialProperty.get().getMaterialID());
    }
    else if (typeProperty.getValue().equals("DVD"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .createDVDCopy(selectedMaterialProperty.get().getMaterialID());
    }
  }

  public void deleteCopy()
  {
    if (typeProperty.getValue().equals("Bog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .deleteBookCopy(selectedMaterialProperty.get().getMaterialID(),
              selectedMaterialProperty.get().getCopyNumber());
    }
    else if (typeProperty.getValue().equals("Ebog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .deleteEBookCopy(selectedMaterialProperty.get().getMaterialID(),
              selectedMaterialProperty.get().getCopyNumber());
    }
    else if (typeProperty.getValue().equals("Lydbog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .deleteAudioBookCopy(selectedMaterialProperty.get().getMaterialID(),
              selectedMaterialProperty.get().getCopyNumber());
    }
    else if (typeProperty.getValue().equals("CD"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .deleteCDCopy(selectedMaterialProperty.get().getMaterialID(),
              selectedMaterialProperty.get().getCopyNumber());
    }
    else if (typeProperty.getValue().equals("DVD"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .deleteDVDCopy(selectedMaterialProperty.get().getMaterialID(),
              selectedMaterialProperty.get().getCopyNumber());
    }
  }

  public void deletMaterial()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .deleteMaterial(selectedMaterialProperty.get().getMaterialID());
  }

  public int totalCopyNumber()
  {
    return ModelFactoryClient.getInstance().getMaterialModelClient()
        .totalNumberOfCopies(selectedMaterialProperty.get().getMaterialID());
  }
}
