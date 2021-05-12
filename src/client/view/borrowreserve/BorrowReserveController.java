package client.view.borrowreserve;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BorrowReserveController
{
    private BorrowReserveVM vm;

    @FXML
    private TextArea materialeInfo;

    @FXML
    private ComboBox<String> borrowChoice;

    @FXML
    private Image imageTest;

    @FXML
    ImageView materialImage = new ImageView(imageTest);


    public void init()
    {
        materialeInfo.textProperty().bind(ViewModelFactory.getInstance().getBorrowReserveVM().getMaterialInfoProp());

        try {
            imageTest = new Image(new FileInputStream(vm.getMaterialImageURL()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML void goBack(ActionEvent actionEvent) throws IOException {
        ViewHandler.getInstance().openView("Search");
    }

    @FXML void loanReserve(ActionEvent actionEvent)
    {

       if (borrowChoice.getSelectionModel().getSelectedItem().equals("Lån")) {
         //  vm.loanMaterial();
       }
       else if (borrowChoice.getSelectionModel().getSelectedItem().equals("Reserve"))
       {
          // vm.reserveMaterail;
       }
    }


}
