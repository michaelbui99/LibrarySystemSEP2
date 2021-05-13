package client.view.registermaterial;

import client.core.ViewModelFactory;
import client.model.material.Material;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class RegisterMaterialController
{
  @FXML private ComboBox<String> materialTypeCompo;
  @FXML private Label  materianInSystemError;
  @FXML private TextField title;
  @FXML private TextField publisher;
  @FXML private TextArea description;
  @FXML private TextArea keywords;
  @FXML private ComboBox<String> audiance;
  @FXML private TextField language;
  @FXML private TextField isbn;
  @FXML private DatePicker releaseDate;
  @FXML private TextField numberOfPages;
  @FXML private TextField licensNumber;
  @FXML private TextField author;
  @FXML private TextField hallNumber;
  @FXML private TextField genre;
  @FXML private TextField creatorLastName;
  @FXML private TextField department;
  @FXML private TextField subtitleLanguage;
  @FXML private TextField length;



  public void init()
  {
    materialTypeCompo.setItems(ViewModelFactory.getInstance().getRegisterMaterialVM().getMaterialType());
    if (materialTypeCompo.getItems().equals("book"))
    {
      isbn.setDisable(false);
      author.setDisable(false);
    }
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }

  @FXML public void onButtonConfirm(ActionEvent actionEvent) throws IOException
  {
    if (materialTypeCompo.getItems().equals("book"))
    {
      //ViewModelFactory.getInstance().getRegisterMaterialVM().addAudioBook(title.getText(), publisher.getText(), releaseDate.get);
    }
  }

  @FXML public void onButtonBack(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Administation");
  }
}
