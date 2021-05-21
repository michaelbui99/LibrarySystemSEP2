package client.view.mymaterial;

import client.core.ModelFactoryClient;
import shared.loan.Loan;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventTypes;

public class MyMaterialVM
{
  private ObservableList<Loan> activeLoans;
  private ObjectProperty<Loan> loanProperty;
  private StringProperty cprProperty;

  public MyMaterialVM()
  {
    //TODO: Find ud af hvordan vi holder styr på hvem der er logget på.


    activeLoans = FXCollections.observableArrayList();
    cprProperty = new SimpleStringProperty(ModelFactoryClient.getInstance().getUserModelClient().getLoginUser().getCpr());
    if (ModelFactoryClient.getInstance().getLoanModelClient()
        .getAllLoansByCPR(cprProperty.get()) != null)
    {
      activeLoans.addAll(ModelFactoryClient.getInstance().getLoanModelClient()
          .getAllLoansByCPR(cprProperty.get()));
    }
    ModelFactoryClient.getInstance().getLoanModelClient()
        /*Listens to for the LOANREGISTERED and LOANENDED event that is specific to the borrowers cpr
        * To ensure that other users Loan events won't affect the specific users window. */
        .addPropertyChangeListener(EventTypes.LOANREGISTERED,
            evt -> {

              if (((Loan) evt.getNewValue()).getBorrower().getCpr().equals(cprProperty.get()))
              {
                activeLoans.add((Loan) evt.getNewValue());
                System.out.println("LOAN REGISTERED CAUGHT");
              }
            });
    ModelFactoryClient.getInstance().getLoanModelClient()
        .addPropertyChangeListener(EventTypes.LOANENDED + cprProperty.get(),
            evt -> {
              if (((Loan) evt.getNewValue()).getBorrower().getCpr().equals(cprProperty.get()))
              {
                activeLoans.removeIf(
                    activeLoan -> activeLoan.getLoanID() == ((Loan) evt
                        .getNewValue()).getLoanID());
                System.out.println("LOAN ENDED EVT CAUGHT");
                System.out.println(((Loan) evt.getNewValue()).getBorrower().getCpr());
              }
            });
    loanProperty = new SimpleObjectProperty<>();
  }


  public void endLoan()
  {
    ModelFactoryClient.getInstance().getLoanModelClient().endLoan(loanProperty.get());
  };

  public ObservableList<Loan> getLoanList()
  {
    return activeLoans;
  }

public ObjectProperty<Loan> loanProperty()
{
  return loanProperty;
}

}
