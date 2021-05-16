package client.view.search;

import client.core.ViewModelFactory;
import shared.materials.Material;
import client.view.ViewHandler;
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

  @FXML private  ComboBox chooseType;

  @FXML private  ComboBox chooseLanguage;

  @FXML private  ComboBox targetAudience;

  @FXML private  Label errorLabel;

  @FXML private  TableView< Material> searchTableView;

  @FXML private TableColumn<String, Material> titleColumn;

  @FXML private TableColumn<String, Material> publisherColumn;

  @FXML private TableColumn<String, Material> releaseDateColumn;

  @FXML private TableColumn<String, Material> typeColumn;

  @FXML private TableColumn<String, Material> statusColumn;

  private ViewHandler viewHandler;
  private SearchVM viewModel;

  public void init()
  {

   titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
   publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
   releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
//   typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
//   statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
  }

  @FXML public void onButtonSearch(ActionEvent actionEvent)
  {

    searchTableView.setItems(this.viewModel.searchMaterial(
        this.title.getText(),
        (String) this.chooseLanguage.getValue(),
        this.keywords.getText(),
        this.genre.getText(),
        this.targetAudience.getValue().toString(),
        this.chooseType.getValue().toString()
    ));
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {

    System.exit(0);
  }

  @FXML public void onButtonContinue(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("BorrowReserve");
    ViewModelFactory.getInstance().getSearchVM().setSelectMaterial(searchTableView.getSelectionModel().getSelectedItem());
  }
}
