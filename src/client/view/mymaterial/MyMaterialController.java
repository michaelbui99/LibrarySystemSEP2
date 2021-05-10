package client.view.mymaterial;

import client.model.loan.Loan;
import client.model.material.Material;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

  public void init(ViewHandler viewHandler, MyMaterialVM viewModel)
  {
    //TODO: change it so it uses VM.getMyMaterialVM when it has been implemented.
    //TODO: implement toString for Material such that material can be displayed correctly in tableview.
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;
    selectedLoanLabel.textProperty()
        .bindBidirectional(viewModel.loanIDProperty());
    materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
    loanDateColumn.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
    deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
    loanTableView.setItems(this.viewModel.getLoanList());
  }

  @FXML void onReturnButton(ActionEvent event)
  {
    //TODO: implement this
    //Sets the binded textproperty to the loanId of the selected loan.
    selectedLoanLabel.textProperty().setValue(String.valueOf(
        loanTableView.getSelectionModel().getSelectedItem().getLoanID()));
  }
}
