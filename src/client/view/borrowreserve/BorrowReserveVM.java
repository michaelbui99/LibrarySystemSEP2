package client.view.borrowreserve;

import client.core.ModelFactory;
import client.model.loan.LoanModelClient;
import client.model.material.Material;
import client.model.material.MaterialModelClient;
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

        ModelFactory.getInstance().getLoanModelClient().registerLoan(ModelFactory.getInstance().getMaterialModelClient().getSelectMaterial(),ModelFactory.getInstance().getUserModelClient().getLoginUser());

    }

    public void reserveMaterial(Material material, Borrower borrower)
    {

       // ModelFactory.getInstance().getLoanModelClient().reserve(material,borrower);
    }


    public String getMaterialImageURL()
    {
        return ModelFactory.getInstance().getMaterialModelClient().getSelectMaterial().getImageURL();
    }







}
