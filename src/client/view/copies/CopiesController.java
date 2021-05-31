package client.view.copies;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import shared.materials.Material;

import java.io.IOException;

//Kutaiba
public class CopiesController
{
  @FXML private TextField title;
  @FXML private TextField genre;
  @FXML private TextField keywords;

  @FXML private ComboBox<String> typeComboBox;
  @FXML private ComboBox<String> targetAudienceComboBox;
  @FXML private ComboBox<String> languageComboBox;

  @FXML private Label errorLabel;

  @FXML private TableView<Material> searchTableView;
  @FXML private TableColumn<String, Material> titleColumn;
  @FXML private TableColumn<String, Material> publisherColumn;
  @FXML private TableColumn<String, Material> releaseDateColumn;
  @FXML private TableColumn<Integer, Material> copyNo;

  private CopiesVM copiesVM;

  public void init(CopiesVM copiesVM)
  {
    this.copiesVM = copiesVM;

    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    publisherColumn
        .setCellValueFactory(new PropertyValueFactory<>("publisher"));
    releaseDateColumn
        .setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    copyNo.setCellValueFactory(new PropertyValueFactory<>("copyNumber"));

    typeComboBox.setItems(copiesVM.getMaterialTypeList());
    targetAudienceComboBox.setItems(copiesVM.getTargetAudienceList());
    languageComboBox.setItems(copiesVM.getLanguageList());

    title.textProperty().bindBidirectional(copiesVM.titleProperty());
    genre.textProperty().bindBidirectional(copiesVM.genreProperty());
    keywords.textProperty().bindBidirectional(copiesVM.keywordsProperty());
    typeComboBox.valueProperty().bindBidirectional(copiesVM.typeProperty());
    targetAudienceComboBox.valueProperty()
        .bindBidirectional(copiesVM.targetAudienceProperty());
    languageComboBox.valueProperty()
        .bindBidirectional(copiesVM.languageProperty());
  }

  @FXML public void onButtonRemoveCopy(ActionEvent actionEvent)
  {
    copiesVM.selectedMaterialProperty()
        .set(searchTableView.getSelectionModel().getSelectedItem());

    if (searchTableView.getSelectionModel().getSelectedItem() == null)
    {
      errorLabel.setText("Vælg først et materiale");
      errorLabel.setTextFill(Paint.valueOf("red"));
      errorLabel.setVisible(true);
    }
    else if (ViewModelFactory.getInstance().getCopiesVM().totalCopyNumber()
        == 1)
    {
      errorLabel.setText("Kun én kopi tilbage, kan ikke fjernes!");
      errorLabel.setTextFill(Paint.valueOf("red"));
      errorLabel.setVisible(true);
    }
    else
    {
      copiesVM.deleteCopy();
      onButtonSearch(actionEvent);
      errorLabel.setVisible(false);
    }
  }

  @FXML public void onButtonAddCopy(ActionEvent actionEvent)
  {
    copiesVM.selectedMaterialProperty()
        .set(searchTableView.getSelectionModel().getSelectedItem());
    if (searchTableView.getSelectionModel().getSelectedItem() == null)
    {
      errorLabel.setText("Vælg først et materiale");
      errorLabel.setTextFill(Paint.valueOf("red"));
      errorLabel.setVisible(true);
    }
    else
    {
      copiesVM.addCopy();
      onButtonSearch(actionEvent);
      errorLabel.setVisible(false);
    }
  }

  @FXML public void onButtonRemoveMaterial(ActionEvent actionEvent)
  {
    copiesVM.selectedMaterialProperty()
        .set(searchTableView.getSelectionModel().getSelectedItem());
    if (searchTableView.getSelectionModel().getSelectedItem() == null)
    {
      errorLabel.setText("Vælg først et materiale");
      errorLabel.setTextFill(Paint.valueOf("red"));
      errorLabel.setVisible(true);
    }
    else
    {
      copiesVM.deleteMaterial();
      onButtonSearch(actionEvent);
    }
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  @FXML public void onButtonBack(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Administration");
  }

  @FXML public void onButtonSearch(ActionEvent actionEvent)
  {
    ObservableList<Material> materialList = ViewModelFactory.getInstance()
        .getCopiesVM().search();
    searchTableView.setItems(materialList);
    if (materialList.size() > 0)
    {
      errorLabel.setText("");
      errorLabel.setVisible(false);
    }
    else
    {
      errorLabel.setText("Materialet findes ikke i systemet!");
      errorLabel.setTextFill(Paint.valueOf("red"));
      errorLabel.setVisible(true);
    }
    searchTableView.refresh();
  }
}
