package client.view.myLoans;

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

public class MyLoansController
{
  @FXML private Label warningLabel;
  @FXML private Label selectedLoanLabel;

  @FXML private TableView<Loan> loanTableView;
  @FXML private TableColumn<String, Material> materialColumn;
  @FXML private TableColumn<String, Material> typeColumn;
  @FXML private TableColumn<String, Loan> loanDateColumn;
  @FXML private TableColumn<String, Loan> deadlineColumn;


  private MyLoansVM viewModel;

  public void init(MyLoansVM viewModel)
  {
    this.viewModel = viewModel;
    //TODO: implement toString for Material such that material can be displayed correctly in tableview.
    materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
    loanDateColumn.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
    deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
    loanTableView.setItems(
       viewModel.getLoanList());
    warningLabel.textProperty().bind(viewModel.warningProperty());
  }

  @FXML void onReturnButton(ActionEvent event)
  {
    //TODO: implement this
    //Sets the binded textproperty to the loanId of the selected loan.
    //    selectedLoanLabel.textProperty().setValue(String.valueOf(
    //        loanTableView.getSelectionModel().getSelectedItem().getLoanID()));
    viewModel.loanProperty()
        .set(loanTableView.getSelectionModel().getSelectedItem());
    viewModel.endLoan();


    loanTableView.refresh();
  }


  @FXML
  void onBackButton(ActionEvent event) {
    try
    {
      ViewHandler.getInstance().openView("BorrowerWindow");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @FXML
  void onExtendButton(ActionEvent event) {
    viewModel.loanProperty().set(loanTableView.getSelectionModel().getSelectedItem());
    viewModel.extendLoan();
    loanTableView.refresh();
  }


}
