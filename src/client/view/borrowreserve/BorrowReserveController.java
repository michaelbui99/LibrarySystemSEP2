package client.view.borrowreserve;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BorrowReserveController
{

    @FXML
    private Image imageTest = new Image(new FileInputStream("https://i.redd.it/gytjbeh6ftg11.jpg"));
    ImageView materialImage = new ImageView(imageTest);
    private BorrowReserveVM vm;

    public BorrowReserveController() throws FileNotFoundException {
    }


    @FXML void goBack(ActionEvent actionEvent)
    {
        //TODO call the openView(arg) method from the viewHandler to open the mainView window//
    }

    @FXML void LoanReserve(ActionEvent actionEvent)
    {
        // Material
        // CPR
        // deadline
     //vm.loanMaterial();
    }


    @FXML void Cancel(ActionEvent actionEvent)
    {
        System.exit(0);
    }
}
