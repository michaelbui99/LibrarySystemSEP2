package client.view.borrowreserve;

import client.core.ModelFactoryClient;
import client.model.material.Material;
import client.model.user.borrower.Borrower;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class BorrowReserveVM
{
    private StringProperty materialInfoProp;

    public BorrowReserveVM()
    {
        materialInfoProp = new SimpleStringProperty();
    }

    public StringProperty getMaterialInfoProp() {
        return materialInfoProp;
    }


    public void loanMaterial()
    {

        ModelFactoryClient.getInstance().getLoanModelClient().registerLoan(
            ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial(),
            ModelFactoryClient.getInstance().getUserModelClient().getLoginUser());

    }

    public void reserveMaterial(Material material, Borrower borrower)
    {

       // ModelFactory.getInstance().getLoanModelClient().reserve(material,borrower);
    }


    public String getMaterialImageURL()
    {
        return ModelFactoryClient.getInstance().getMaterialModelClient().getSelectMaterial().getImageURL();
    }







}
