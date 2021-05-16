package client.view.mymaterial;

import client.core.ViewModelFactory;
import shared.loan.Loan;
import shared.materials.Material;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class MyMaterialController
{

  @FXML private Label selectedLoanLabel;
  @FXML private TableView<Loan> loanTableView;

  @FXML private TableColumn<String, Material> materialColumn;

  @FXML private TableColumn<String, Material> typeColumn;

  @FXML private TableColumn<String, Loan> loanDateColumn;

  @FXML private TableColumn<String, Loan> deadlineColumn;
  private ViewHandler viewHandler;
  private MyMaterialVM viewModel;

  public void init()
  {
    //TODO: implement toString for Material such that material can be displayed correctly in tableview.
    materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
    loanDateColumn.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
    deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
    loanTableView.setItems(
        ViewModelFactory.getInstance().getMyMaterialVM().getLoanList());
  }

  @FXML void onReturnButton(ActionEvent event)
  {
    //TODO: implement this
    //Sets the binded textproperty to the loanId of the selected loan.
    //    selectedLoanLabel.textProperty().setValue(String.valueOf(
    //        loanTableView.getSelectionModel().getSelectedItem().getLoanID()));
    ViewModelFactory.getInstance().getMyMaterialVM().loanProperty()
        .set(loanTableView.getSelectionModel().getSelectedItem());
    ViewModelFactory.getInstance().getMyMaterialVM().endLoan();
    loanTableView.refresh();
  }


  @FXML
  void onBackButton(ActionEvent event) {
    try
    {
      ViewHandler.getInstance().openView("UserWindow");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
