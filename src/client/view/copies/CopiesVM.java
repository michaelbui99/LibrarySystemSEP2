package client.view.copies;

import client.core.ModelFactoryClient;
import client.model.material.strategy.*;
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

  public int getSelectedMaterial()
  {
    return ModelFactoryClient.getInstance().getMaterialModelClient()
        .getSelectMaterial().getMaterialID();
  }

  public void setSelectMaterial(Material selectMaterial)
  {
    ModelFactoryClient.getInstance().getMaterialModelClient()
        .setSelectMaterial(selectMaterial);
  }

  public void addCopy()
  {
    if (typeProperty.getValue().equals("Bog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient()
          .createBookCopy(getSelectedMaterial());
    }
    else if (typeProperty.getValue().equals("Ebog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient().createEBookCopy(getSelectedMaterial());
    }
    else if (typeProperty.getValue().equals("Lydbog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient().createAudioBookCopy(getSelectedMaterial());
    }
    else if (typeProperty.getValue().equals("CD"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient().createCDCopy(getSelectedMaterial());
    }
    else if (typeProperty.getValue().equals("DVD"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient().createDVDCopy(getSelectedMaterial());
    }
  }

  public void deleteCopy()
  {
    if (typeProperty.getValue().equals("Bog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient().deleteBookCopy(getSelectedMaterial());
    }
    else if (typeProperty.getValue().equals("Ebog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient().deleteEBookCopy(getSelectedMaterial());
    }
    else if (typeProperty.getValue().equals("Lydbog"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient().deleteAudioBookCopy(getSelectedMaterial());
    }
    else if (typeProperty.getValue().equals("CD"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient().deleteCDCopy(getSelectedMaterial());
    }
    else if (typeProperty.getValue().equals("DVD"))
    {
      ModelFactoryClient.getInstance().getMaterialModelClient().deleteDVDCopy(getSelectedMaterial());
    }
  }

  public void deletMaterial()
  {
    ModelFactoryClient.getInstance().getMaterialModelClient().deleteMaterial(getSelectedMaterial());
  }

  public int totalCopyNumber()
  {
    return ModelFactoryClient.getInstance().getMaterialModelClient().totalNumberOfCopies(getSelectedMaterial());
  }
}
