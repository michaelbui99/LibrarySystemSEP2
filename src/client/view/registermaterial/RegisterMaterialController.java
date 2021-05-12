package client.view.registermaterial;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterMaterialController
{
//
//  @FXML private TextField bookTitle;
//  @FXML private TextField bookPublisher;
//  @FXML private TextArea bookDescription;
//  @FXML private TextArea keywords;
//  @FXML private Label bookKeywords;
//  @FXML private DatePicker releaseDate;
//  @FXML private TextField ebookTitle;
//  @FXML private TextField ebookPublisher;
//  @FXML private TextArea ebookDescription;
//  @FXML private TextArea ebookKeywords;
//  @FXML private DatePicker releasedDateEbook;
//  @FXML private TextField audiobookTitle;
//  @FXML private TextField audiobookPublisher;
//  @FXML private TextArea audiobookDescription;
//  @FXML private TextArea audiobookKeywords;
//  @FXML private DatePicker releasedDateAudiobook;
//  @FXML private TextField cdTitle;
//  @FXML private TextField cdPublisher;
//  @FXML private TextArea cdDescription;
//  @FXML private TextArea cdKeywords;
//  @FXML private DatePicker releasedDateCd;
//  @FXML private TextField dvdTitle;
//  @FXML private TextField dvdPublisher;
//  @FXML private TextArea dvdDescription;
//  @FXML private TextArea dvdKeywords;
//  @FXML private ComboBox chooseTargetAudience;
//  @FXML private TextField dvdLanguage;
//  @FXML private DatePicker releasedDateDvd;


  @FXML private ComboBox materialType;
  @FXML private TextField materialTitle;
  @FXML private TextField materialPublisher;
  @FXML private TextField materialLength;
  @FXML private TextField materialPageNumber;
  @FXML private TextArea materialDescription;
  @FXML private TextArea materialKeywords;
  @FXML private DatePicker materialReleaseDate;
  @FXML private ComboBox materialTargetAudience;
  @FXML private ComboBox materialLanguage;

  RegisterMaterialVM registerMaterialVM;
  ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, RegisterMaterialVM registerMaterialVM)
  {
    viewHandler = ViewHandler.getInstance();
    registerMaterialVM = ViewModelFactory.getInstance().getRegisterMaterialVM();
  }


  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }
  @FXML public void onButtonConfirm(ActionEvent actionEvent)
  {
      registerMaterialVM.
  }
  @FXML public void onButtonBack(ActionEvent actionEvent)
  {
    System.exit(0);
  }

}
