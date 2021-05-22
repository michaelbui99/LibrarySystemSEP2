package client.view.mymaterial;

import client.core.ModelFactoryClient;
import client.model.loan.LoanModelClient;
import client.model.user.UserModelClient;
import shared.loan.Loan;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventTypes;

import java.util.NoSuchElementException;

public class MyMaterialVM
{
  private ObservableList<Loan> activeLoans;
  private ObjectProperty<Loan> loanProperty;
  private StringProperty cprProperty;
  private LoanModelClient loanModel;
  private UserModelClient userModel;
  private StringProperty warningProperty;

  public MyMaterialVM(LoanModelClient loanModel, UserModelClient userModel)
  {
    this.loanModel = loanModel;
    this.userModel = userModel;
    activeLoans = FXCollections.observableArrayList();
    warningProperty = new SimpleStringProperty();
    loanProperty = new SimpleObjectProperty<>();
    cprProperty = new SimpleStringProperty(userModel.getLoginUser().getCpr());

      //Initialises with all active loans for the user.
    try
    {
      activeLoans.addAll(loanModel.getAllLoansByCPR(cprProperty.get()));
      for (Loan activeLoan : activeLoans)
      {
        System.out.println(activeLoan.getMaterial().getMaterialID());
        System.out.println(activeLoan.getLoanID());
      }
    }
    catch (NoSuchElementException e)
    {
      warningProperty.set(e.getMessage());
    }

    /*Listens to for the LOANREGISTERED and LOANENDED event that is specific to the borrowers cpr
     * To ensure that other users Loan events won't affect the specific users window. */
    loanModel.addPropertyChangeListener(EventTypes.LOANREGISTERED, evt -> {

      if (((Loan) evt.getNewValue()).getBorrower().getCpr()
          .equals(cprProperty.get()))
      {
        activeLoans.add((Loan) evt.getNewValue());
        warningProperty.set("");
        System.out.println("LOAN REGISTERED CAUGHT");
      }
    });
    loanModel.addPropertyChangeListener(EventTypes.LOANENDED, evt -> {

      if (((Loan) evt.getNewValue()).getBorrower().getCpr()
          .equals(cprProperty.get()))
      {
        activeLoans.removeIf(
            activeLoan -> activeLoan.getLoanID() == ((Loan) evt.getNewValue())
                .getLoanID());
      if (activeLoans.size() == 0)
      {
        warningProperty.set("Ingen aktive lån");
      }
      }
      System.out.println("LOAN ENDED EVT CAUGHT");
      System.out.println(((Loan) evt.getNewValue()).getBorrower().getCpr());

    });

  }

  public void endLoan()
  {
    loanModel.endLoan(loanProperty.get());
    System.out.println(loanProperty.get().getLoanID());
    System.out.println(loanProperty.get().getMaterial().getMaterialID());
    System.out.println(loanProperty.get().getMaterial().getCopyNumber());
  }



  public ObservableList<Loan> getLoanList()
  {
    return activeLoans;
  }

  public ObjectProperty<Loan> loanProperty()
  {
    return loanProperty;
  }

  public StringProperty warningProperty()
  {
    return warningProperty;
  }

}
