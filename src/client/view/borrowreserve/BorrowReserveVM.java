package client.view.borrowreserve;

import client.core.ModelFactoryClient;
import client.model.material.MaterialModelClient;
import javafx.beans.property.*;
import shared.materials.Material;

public class BorrowReserveVM {
    private StringProperty materialInfoProp;
    private IntegerProperty availNumberProp;
    private ObjectProperty<Material> materialProperty;

    public BorrowReserveVM() {
        //TODO: Lav om til listener, så antallet af kopier opdateres dynamisk.
        this.materialProperty = new SimpleObjectProperty<>();
        materialProperty.set(ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial());
        availNumberProp = new SimpleIntegerProperty(ModelFactoryClient.getInstance().getMaterialModelClient().numberOfAvailableCopies());
        materialInfoProp = new SimpleStringProperty(materialProperty.get().toString());
        System.out.println(materialProperty.get().toString());
    }


    public IntegerProperty getAvailNumberProp() {
        return availNumberProp;
    }

    public StringProperty getMaterialInfoProp() {
        return materialInfoProp;
    }

    public ObjectProperty<Material> materialProperty()
    {
        return materialProperty;
    }

    public void loanMaterial() {

        ModelFactoryClient.getInstance().getLoanModelClient().registerLoan(
                ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial(),
                ModelFactoryClient.getInstance().getUserModelClient().getLoginUser());

    }

    public void reserveMaterial() {

        ModelFactoryClient.getInstance().getReservationModelClient().registerReservation(
                ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial(),
                ModelFactoryClient.getInstance().getUserModelClient().getLoginUser());
    }


    public String getMaterialImageURL() {
        return ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial().getImageURL();
    }


}
