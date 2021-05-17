package client.view.borrowreserve;

import client.core.ViewModelFactory;
import client.view.ViewHandler;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BorrowReserveController {

    @FXML
    private TextArea materialeInfo;

    @FXML
    private ComboBox<String> borrowChoice;

    @FXML
    private Image imageTest;

    @FXML
    private TextArea availNumber;

    @FXML
    ImageView materialImage = new ImageView(imageTest);


    public void init() throws FileNotFoundException {
        materialeInfo.textProperty().bind(ViewModelFactory.getInstance().getBorrowReserveVM().getMaterialInfoProp());
        availNumber.textProperty().bind(ViewModelFactory.getInstance().getBorrowReserveVM().getAvailNumberProp().asString());
       // materialImage.imageProperty().bind((ObservableValue<? extends Image>) new Image(new FileInputStream(ViewModelFactory.getInstance().getBorrowReserveVM().getMaterialImageURL())));
        try {
            imageTest = new Image(new FileInputStream(ViewModelFactory.getInstance().getBorrowReserveVM().getMaterialImageURL()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void goBack(ActionEvent actionEvent) throws IOException {
        ViewHandler.getInstance().openView("Search");
    }

    @FXML
    void loanReserve(ActionEvent actionEvent) {

        if (borrowChoice.getSelectionModel().getSelectedItem().equals("Lån")) {
            ViewModelFactory.getInstance().getBorrowReserveVM().loanMaterial();
        } else if (borrowChoice.getSelectionModel().getSelectedItem().equals("Reserve")) {
            ViewModelFactory.getInstance().getBorrowReserveVM().reserveMaterial();
        }
    }


}
