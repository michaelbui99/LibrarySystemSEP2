package client.view.search;

import client.core.ViewModelFactory;
import shared.materials.Material;
import client.view.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class SearchController
{

  @FXML private TextField title;
  @FXML private  TextField genre;
  @FXML private  TextField keywords;
  @FXML private ComboBox<String> chooseType;
  @FXML private  ComboBox<String> chooseLanguage;
  @FXML private  ComboBox<String> targetAudience;
  @FXML private Label errorLabel;
  @FXML private TableView< Material> searchTableView;
  @FXML private TableColumn<String, Material> titleColumn;
  @FXML private TableColumn<String, Material> publisherColumn;
  @FXML private TableColumn<String, Material> releaseDateColumn;
  @FXML private TableColumn<String, Material> statusColumn;

  private ViewHandler viewHandler;
  private SearchVM viewModel;
  private ObservableList<String> materialTy = FXCollections.observableArrayList();
  private ObservableList<String> materialLanguage = FXCollections.observableArrayList();
  private ObservableList<String>   materialAudience = FXCollections.observableArrayList();


  public void init()
  {
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
    releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    statusColumn.setCellValueFactory(new PropertyValueFactory<>("materialStatus"));
    title.textProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM()
        .titleProperty());
    genre.textProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM()
        .genreProperty());
    keywords.textProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM()
        .keywordProperty());
    chooseType.valueProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM().chooseTypeProperty());
    chooseLanguage.valueProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM()
        .languageProperty());
    targetAudience.valueProperty().bindBidirectional(ViewModelFactory.getInstance().getSearchVM()
        .targetAudienceProperty());

    chooseType.setItems(ViewModelFactory.getInstance().getSearchVM().getMaterialType());
    chooseLanguage.setItems(ViewModelFactory.getInstance().getSearchVM()
        .getMaterialLanguage());
   targetAudience.setItems(ViewModelFactory.getInstance().getSearchVM()
       .getMaterialAudience());

  }

  @FXML public void onButtonSearch(ActionEvent actionEvent)
  {

    ObservableList<Material> materials = null;

    {
      materials = ViewModelFactory.getInstance().getSearchVM().searchMaterial();
      searchTableView.setItems(materials);
      if (materials.size() > 0 ){
        errorLabel.setVisible(false);
      }
      else {
        errorLabel.setText("Der er ingen materialer, der matcher din søgning!!!");
        errorLabel.setVisible(true);
      }
    }

    searchTableView.refresh();

  }

  @FXML public void onButtonCancel(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("BorrowerWindow");
  }

  @FXML public void onButtonContinue(ActionEvent actionEvent) throws IOException
  {
    if (searchTableView.getSelectionModel().getSelectedItem() != null)
    {
      ViewModelFactory.getInstance().getSearchVM().setSelectMaterial(searchTableView.getSelectionModel().getSelectedItem());
      ViewHandler.getInstance().openView("LoanReserve");
      errorLabel.setVisible(false);
    }
    else
    {
      errorLabel.setText("Vælg et material!!");
      errorLabel.setVisible(true);
    }

  }


}
