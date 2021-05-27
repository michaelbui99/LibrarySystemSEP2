package client.view.borrowercontactinfo;

import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import shared.person.borrower.Borrower;

import java.io.IOException;

public class BorrowerContactInfoController
{

  @FXML
  private TextField cprField;

  @FXML
  private TextField fNameField;

  @FXML
  private TextField lNameField;

  @FXML
  private TextField phoneField;

  @FXML
  private TextField emailField;

  @FXML
  private TextField addressField;

  @FXML
  private Label warningLabel;

  private BorrowerContactInfoVM viewModel;

  public void init(BorrowerContactInfoVM viewModel)
  {
    this.viewModel = viewModel;
    cprField.textProperty().bindBidirectional(viewModel.cprProperty());
    fNameField.textProperty().bind(viewModel.firstNameProperty());
    lNameField.textProperty().bind(viewModel.lastNameProperty());
    phoneField.textProperty().bind(viewModel.phoneNumberProperty());
    emailField.textProperty().bind(viewModel.emailProperty());
    addressField.textProperty().bind(viewModel.addressProperty());
    warningLabel.textProperty().bind(viewModel.warningProperty());
  }

  @FXML
  void onBackButton(ActionEvent event) throws IOException
  {
    viewModel.clearAllProperties();
    ViewHandler.getInstance().openView("Administration");
  }

  @FXML
  void onSearchButton(ActionEvent event) {
    viewModel.getBorrowerInfo();
  }
}
