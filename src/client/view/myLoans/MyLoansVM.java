package client.view.myLoans;

import client.core.ModelFactoryClient;
import client.model.loan.LoanModelClient;
import client.model.user.UserModelClient;
import javafx.application.Platform;
import shared.loan.Loan;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventTypes;

import java.util.NoSuchElementException;

public class MyLoansVM
{
  private ObservableList<Loan> activeLoans;
  private ObjectProperty<Loan> loanProperty;
  private StringProperty cprProperty;
  private LoanModelClient loanModel;
  private UserModelClient userModel;
  private StringProperty warningProperty;

  public MyLoansVM(LoanModelClient loanModel, UserModelClient userModel)
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
    }
    catch (NoSuchElementException e)
    {
      //TODO: instead of throwing the exception through all layers, add a event type instead send the message through obeserver instead.
      warningProperty.set(e.getMessage());
    }

    /*Listens to for the LOANREGISTERED and LOANENDED event that is specific to the borrowers cpr
     * To ensure that other users Loan events won't affect the specific users window. */
    loanModel.addPropertyChangeListener(EventTypes.LOANREGISTERED, evt -> {
      //Checks if the loan is relevant for the specific borrower.
      if (((Loan) evt.getNewValue()).getBorrower().getCpr()
          .equals(cprProperty.get()))
      {
        activeLoans.add((Loan) evt.getNewValue());
        //Resets warning label.
        warningProperty.set("");
        System.out.println("LOAN REGISTERED CAUGHT");
      }
    });

    loanModel.addPropertyChangeListener(EventTypes.LOANENDED, evt -> {
      //Checks if the loan is relevant for the specific borrower
      if (((Loan) evt.getNewValue()).getBorrower().getCpr()
          .equals(cprProperty.get()))
      {
        //Remove loan from the observableList if the loanID matches.
        activeLoans.removeIf(
            activeLoan -> activeLoan.getLoanID() == ((Loan) evt.getNewValue())
                .getLoanID());
      if (activeLoans.size() == 0)
      {
        //Update warning label if no loans.
        warningProperty.set("Ingen aktive lån");
      }
      }
    });

    loanModel.addPropertyChangeListener(EventTypes.LOANEXTENDED, evt->{
      if (((Loan)evt.getNewValue()).getBorrower().getCpr().equals(cprProperty.get()))
      {
        int index = 0;
        for (Loan activeLoan : activeLoans)
        {
          //Looks thorugh the list of Loans and updates the Loan to match the extended loan.
          if (activeLoan.getLoanID() == ((Loan) evt.getNewValue()).getLoanID())
          {
            index = activeLoans.indexOf(activeLoan);
            break;
          }
        }
        activeLoans.set(index, ((Loan)evt.getNewValue()));
        //Resets warning label
        Platform.runLater(()->warningProperty.set(""));

      }
    });

    loanModel.addPropertyChangeListener(EventTypes.LOANEXTENDERROR, evt -> {
      //Evt caught has the Loan as oldvalue and error message as new value.
      if (((Loan)evt.getOldValue()).getBorrower().getCpr().equals(cprProperty.get()))
      {
        Platform.runLater(()->warningProperty.set((String)evt.getNewValue()));
      }
    });


  }

  public void endLoan()
  {
    loanModel.endLoan(loanProperty.get());
    System.out.println(loanProperty.get().getLoanID());
    System.out.println(loanProperty.get().getMaterial().getMaterialID());
    System.out.println(loanProperty.get().getMaterial().getCopyNumber());
  }


  public void extendLoan()
  {
    loanModel.extendLoan(loanProperty.get());
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
