package client.view.borrowreserve;

import client.core.ModelFactoryClient;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class BorrowReserveVM {
    private StringProperty materialInfoProp;
    private IntegerProperty availNumberProp;

    public BorrowReserveVM() {
        materialInfoProp = new SimpleStringProperty();
        availNumberProp = new SimpleIntegerProperty();
    }


    public IntegerProperty getAvailNumberProp() {
        return availNumberProp;
    }

    public StringProperty getMaterialInfoProp() {
        return materialInfoProp;
    }


    public void loanMaterial() {

        ModelFactoryClient.getInstance().getLoanModelClient().registerLoan(
                ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial(),
                ModelFactoryClient.getInstance().getUserModelClient().getLoginUser());

    }

    public void reserveMaterial() {

        ModelFactoryClient.getInstance().getLoanModelClient().registerReservation(
                ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial(),
                ModelFactoryClient.getInstance().getUserModelClient().getLoginUser());
    }


    public String getMaterialImageURL() {
        return ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial().getImageURL();
    }


}
