package client.view.mymaterial;

import client.core.ViewModelFactory;
import client.model.loan.Loan;
import client.model.material.Material;
import client.view.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyMaterialController
{

  @FXML private TableView<Loan> loanTableView;

  @FXML private TableColumn<String, Material> materialColumn;

  @FXML private TableColumn<String, Material> typeColumn;

  @FXML private TableColumn<String, Loan> loanDateColumn;

  @FXML private TableColumn<String, Loan> deadlineColumn;
  private ViewHandler viewHandler;
  private MyMaterialVM vm;

  public void init(ViewHandler viewHandler, ViewModelFactory vm)
  {
    //TODO: change it so it uses VM.getMyMaterialVM when it has been implemented.
    //TODO: implement toString for Material such that material can be displayed correctly in tableview.
    vm = ViewModelFactory.getinstance.getMyMaterialVM;
    vm.loanObjectProperty
        .bind(loanTableView.getSelectionModel().getSelectedItem());
    this.viewHandler = viewHandler;

    materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
    loanDateColumn.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
    deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
  }
}
