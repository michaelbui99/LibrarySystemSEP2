package client.view.registermaterial;

import client.core.ViewModelFactory;
import shared.materials.Place;
import shared.person.MaterialCreator;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class RegisterMaterialController
{
  @FXML private ComboBox<String> materialTypeCompo;
  @FXML private Label materianInSystemError;
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
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField country;
  @FXML private DatePicker dateOfBirth;
  @FXML private TextField hallNumber;
  @FXML private TextField genre;
  @FXML private TextField creatorLastName;
  @FXML private TextField department;
  @FXML private TextField subtitleLanguage;
  @FXML private TextField length;

  public void init()
  {
    materialTypeCompo.setItems(
        ViewModelFactory.getInstance().getRegisterMaterialVM()
            .getMaterialType());
    audiance.setItems(ViewModelFactory.getInstance().getRegisterMaterialVM()
        .getTargetAudiance());
  }

  @FXML public void onButtonCancel(ActionEvent actionEvent)
  {
    System.exit(0);
  }


  @FXML public void onButtonBack(ActionEvent actionEvent) throws IOException
  {
    ViewHandler.getInstance().openView("Administation");
  }

  @FXML public void onButtonConfirmWindow(ActionEvent actionEvent)
      throws IOException
  {
   // ViewHandler.getInstance().openView("ConfirmationRegisterMaterial");
    int page = Integer.parseInt(numberOfPages.getText());
    int hall = Integer.parseInt(hallNumber.getText());
   // int lg = Integer.parseInt(length.getText());

    String rd = String.valueOf(releaseDate.getValue());
    String dob = String.valueOf(dateOfBirth.getValue());

    String type = materialTypeCompo.getValue();
    String aud = audiance.getValue().toString();

    if (type.equals("Book"))
    {
      ViewModelFactory.getInstance().getRegisterMaterialVM()
          .addBook(title.getText(), publisher.getText(), rd,
              description.getText(), keywords.getText(), aud,
              language.getText(), isbn.getText(), page,
              new Place(hall, department.getText(), creatorLastName.getText(),
                  genre.getText()),
              new MaterialCreator(firstName.getText(), lastName.getText(), dob,
                  country.getText()), genre.getText(), null);
    }
    /*else if (type.equals("EBook"))
    {
      ViewModelFactory.getInstance().getRegisterMaterialVM()
          .addEBook(title.getText(), publisher.getText(), rd,
              description.getText(), keywords.getText(), aud,
              language.getText(), isbn.getText(), page, licensNumber.getText(),
              new MaterialCreator(firstName.getText(), lastName.getText(), dob,
                  country.getText()), genre.getText(), null);
    }
    else if (type.equals("AudioBook"))
    {
      ViewModelFactory.getInstance().getRegisterMaterialVM()
          .addAudioBook(title.getText(), publisher.getText(), rd,
              description.getText(), keywords.getText(), aud,
              language.getText(), lg, genre.getText(),
              new MaterialCreator(firstName.getText(), lastName.getText(), dob,
                  country.getText()), null);
    }
    else if (type.equals("CD"))
    {
      ViewModelFactory.getInstance().getRegisterMaterialVM()
          .addCD(title.getText(), publisher.getText(), rd,
              description.getText(), keywords.getText(), aud,
              language.getText(), lg,
              new Place(hall, department.getText(), creatorLastName.getText(),
                  genre.getText()), genre.getText(), null);
    }
    else if (type.equals("DVD"))
    {
      ViewModelFactory.getInstance().getRegisterMaterialVM()
          .addDVD(title.getText(), publisher.getText(), rd,
              description.getText(), keywords.getText(), aud,
              language.getText(), subtitleLanguage.getText(), lg,
              new Place(hall, department.getText(), creatorLastName.getText(),
                  genre.getText()), genre.getText(), null);
    }*/
  }

  @FXML public void onButtonConfirm(ActionEvent actionEvent)
  {

  }
}
