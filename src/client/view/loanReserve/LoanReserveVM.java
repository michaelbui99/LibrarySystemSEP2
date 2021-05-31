package client.view.loanReserve;

import client.core.ModelFactoryClient;
import client.model.loan.LoanModelClient;
import client.model.material.MaterialModelClient;
import client.model.reservation.ReservationModelClient;
import javafx.beans.property.*;
import shared.materials.Material;
import shared.util.EventTypes;

import java.util.NoSuchElementException;

//Kasper-Michael-Lilian
public class LoanReserveVM
{
  private StringProperty materialInfoProp;
  private IntegerProperty availNumberProp;
  private ObjectProperty<Material> materialProperty;
  private StringProperty warningProperty;
  private StringProperty reservationError;
  private ReservationModelClient reservationModel;
  private LoanModelClient loanModel;
  private MaterialModelClient materialModel;

  public LoanReserveVM(ReservationModelClient reservationModel,
      LoanModelClient loanModel, MaterialModelClient materialModel)
  {
    this.reservationModel = reservationModel;
    this.materialProperty = new SimpleObjectProperty<>();
    this.loanModel = loanModel;
    this.materialModel = materialModel;
    materialProperty.set(materialModel.getSelectMaterial());
    availNumberProp = new SimpleIntegerProperty(
        materialModel.numberOfAvailableCopies());
    materialInfoProp = new SimpleStringProperty(
        materialProperty.get().getMaterialDetails());
    warningProperty = new SimpleStringProperty();
    reservationError = new SimpleStringProperty();

    //Checks number of copies everytime a loan is made and end in the system.
    loanModel.addPropertyChangeListener(EventTypes.LOANREGISTERED,
        (evt) -> availNumberProp.set(materialModel.numberOfAvailableCopies()));

    loanModel.addPropertyChangeListener(EventTypes.LOANENDED,
        (evt) -> availNumberProp.set(materialModel.numberOfAvailableCopies()));
    //Listens for when a new material has been Selected and updates the information displayed.
    materialModel.addPropertyChangeListener("materialSelected", evt -> {
      materialProperty.set((Material) evt.getNewValue());
      materialInfoProp.set(materialProperty.get().getMaterialDetails());
      availNumberProp.set(materialModel.numberOfAvailableCopies());
    });
  }

  public IntegerProperty getAvailNumberProp()
  {
    return availNumberProp;
  }

  public StringProperty getMaterialInfoProp()
  {
    return materialInfoProp;
  }

  public ObjectProperty<Material> materialProperty()
  {
    return materialProperty;
  }

  public void loanMaterial()
  {

    try
    {
      loanModel.registerLoan(materialModel.getSelectMaterial(),
          ModelFactoryClient.getInstance().getUserModelClient().getLoginUser());
      warningProperty.set("");
    }
    catch (IllegalStateException | NoSuchElementException e)
    {
      warningProperty.set(e.getMessage());
    }

  }

  public void reserveMaterial()
  {
    try
    {
      reservationModel.registerReservation(materialModel.getSelectMaterial(),
          ModelFactoryClient.getInstance().getUserModelClient().getLoginUser());
      warningProperty.set("");

    }
    catch (IllegalStateException | NoSuchElementException e)
    {
      warningProperty.set(e.getMessage());
    }
  }

  public String getMaterialImageURL()
  {
    return materialProperty.get().getImageURL();
  }

  public StringProperty warningPropertyProperty()
  {
    return warningProperty;
  }

  public StringProperty reservationErrorProperty()
  {
    return reservationError;
  }

}
