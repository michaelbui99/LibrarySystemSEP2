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

public class CopiesController
{
  @FXML private TextField title;
  @FXML private TextField genre;
  @FXML private TextField keywards;

  @FXML private ComboBox<String> typeComboBox;
  @FXML private ComboBox<String> targetAudienceComboBox;
  @FXML private ComboBox<String> languageComboBox;

  @FXML private Label errorLable;

  @FXML private TableView<Material> searchTableView;
  @FXML private TableColumn<String, Material> titleColumn;
  @FXML private TableColumn<String, Material> publisherColumn;
  @FXML private TableColumn<String, Material> releaseDateColumn;
  @FXML private TableColumn<Integer, Material> copyNo;

  public void init()
  {
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    publisherColumn
        .setCellValueFactory(new PropertyValueFactory<>("publisher"));
    releaseDateColumn
        .setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    copyNo.setCellValueFactory(new PropertyValueFactory<>("copyNumber"));

    typeComboBox.setItems(
        ViewModelFactory.getInstance().getCopiesVM().getMaterialTypeList());
    targetAudienceComboBox.setItems(
        ViewModelFactory.getInstance().getCopiesVM().getTargetAudianceList());
    languageComboBox.setItems(
        ViewModelFactory.getInstance().getCopiesVM().getLanguageList());

    title.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getCopiesVM().titleProperty());
    genre.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getCopiesVM().genreProperty());
    keywards.textProperty().bindBidirectional(
        ViewModelFactory.getInstance().getCopiesVM().keywordsProperty());
    typeComboBox.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getCopiesVM().typeProperty());
    targetAudienceComboBox.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getCopiesVM().targetAudianceProperty());
    languageComboBox.valueProperty().bindBidirectional(
        ViewModelFactory.getInstance().getCopiesVM().languageProperty());
  }

  @FXML public void onButtonRemoveCopy(ActionEvent actionEvent)
  {
    ViewModelFactory.getInstance().getCopiesVM().selectedMaterialProperty()
        .set(searchTableView.getSelectionModel().getSelectedItem());

    if (ViewModelFactory.getInstance().getCopiesVM().totalCopyNumber() == 1)
    {
      errorLable.setText("Kun én kopi tilbage, kan ikke fjernes!");
      errorLable.setTextFill(Paint.valueOf("red"));
      errorLable.setVisible(true);
    }
    else if (searchTableView.getSelectionModel().getSelectedItem() == null)
    {
      errorLable.setText("Vælg først et materiale");
      errorLable.setTextFill(Paint.valueOf("red"));
      errorLable.setVisible(true);
    }
    else
    {
      ViewModelFactory.getInstance().getCopiesVM().deleteCopy();
      onButtonSearch(actionEvent);
      errorLable.setVisible(false);
    }
  }

  @FXML public void onButtonAddCopy(ActionEvent actionEvent)
  {
    ViewModelFactory.getInstance().getCopiesVM().selectedMaterialProperty()
        .set(searchTableView.getSelectionModel().getSelectedItem());
    if (searchTableView.getSelectionModel().getSelectedItem() == null)
    {
      errorLable.setText("Vælg først et materiale");
      errorLable.setTextFill(Paint.valueOf("red"));
      errorLable.setVisible(true);
    }
    else
    {
      ViewModelFactory.getInstance().getCopiesVM().addCopy();
      onButtonSearch(actionEvent);
      errorLable.setVisible(false);
    }
  }

  @FXML public void onButtonRemoveMaterial(ActionEvent actionEvent)
  {
    ViewModelFactory.getInstance().getCopiesVM().selectedMaterialProperty()
        .set(searchTableView.getSelectionModel().getSelectedItem());
    if (searchTableView.getSelectionModel().getSelectedItem() == null)
    {
      errorLable.setText("Vælg først et materiale");
      errorLable.setTextFill(Paint.valueOf("red"));
      errorLable.setVisible(true);
    }
    else
    {
      ViewModelFactory.getInstance().getCopiesVM().deletMaterial();
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
      errorLable.setText("");
      errorLable.setVisible(false);
    }
    else
    {
      errorLable.setText("Materialet findes ikke i systemet!");
      errorLable.setTextFill(Paint.valueOf("red"));
      errorLable.setVisible(true);
    }
    searchTableView.refresh();
  }
}
