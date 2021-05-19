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
    private TextArea materialInfo;

    @FXML
    private ComboBox<String> borrowChoice;

    @FXML
    private Image imageTest;

    @FXML
    private TextArea availNumber;

    @FXML
    ImageView materialImage = new ImageView(imageTest);


    public void init() throws FileNotFoundException {
        ViewModelFactory.getInstance().getBorrowReserveVM();
        availNumber.textProperty().bind(ViewModelFactory.getInstance().getBorrowReserveVM().getAvailNumberProp().asString());
        materialInfo.textProperty().bind(ViewModelFactory.getInstance().getBorrowReserveVM().getMaterialInfoProp());
       // materialIma11ge.imageProperty().bind((ObservableValue<? extends Image>) new Image(new FileInputStream(ViewModelFactory.getInstance().getBorrowReserveVM().getMaterialImageURL())));
        try {
            if (ViewModelFactory.getInstance().getBorrowReserveVM().getMaterialImageURL() != null)
            {
                imageTest = new Image(new FileInputStream(ViewModelFactory.getInstance().getBorrowReserveVM().getMaterialImageURL()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void goBack(ActionEvent actionEvent) throws IOException {
        ViewHandler.getInstance().openView("Search");
    }



    @FXML
    void onLoanButton(ActionEvent event) {
        ViewModelFactory.getInstance().getBorrowReserveVM().loanMaterial();
    }

    @FXML
    void onReserveButton(ActionEvent event)
    {
        ViewModelFactory.getInstance().getBorrowReserveVM().reserveMaterial();
    }
}
