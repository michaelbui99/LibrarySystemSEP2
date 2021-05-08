package client.view.borrowreserve;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class BorrowReserveController
{

    @FXML
    ImageView materialImage;

    private BorrowReseveVM vm;

    @FXML void goBack(ActionEvent actionEvent)
    {
        //TODO call the openView(arg) method from the viewHandler to open the mainView window//
    }

    @FXML void LoanReserve(ActionEvent actionEvent)
    {
        // Material
        // CPR
        // deadline
     //   vm.loanMaterial();
    }


    @FXML void Cancel(ActionEvent actionEvent)
    {
        System.exit(0);
    }
}
