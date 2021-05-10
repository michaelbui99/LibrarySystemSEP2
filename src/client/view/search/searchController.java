package client.view.search;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class searchController
{

  @FXML private TextField title;
  @FXML private  TextField genre;
  @FXML private  TextField keywords;
  @FXML private  ComboBox chooseType;
  @FXML private  ComboBox chooseLanguage;
  @FXML private  ComboBox targetAudience;
  @FXML private  Label errorLabel;
  @FXML private  TableView searchTableView;



  public void init(ViewHandler viewHandler, SearchVM searchVM)
  {
   viewHandler = ViewHandler.getInstance();
   searchVM = ViewModelFactory.getInstance().getSearchVM();
  }

  @FXML public void onBottonSearch(ActionEvent actionEvent)
  {

  }

  @FXML public void onBottonCancle(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  @FXML public void onBottonContinue(ActionEvent actionEvent)
  {
  }
}
