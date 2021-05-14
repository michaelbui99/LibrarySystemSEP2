package client.view.mymaterial;

import client.core.ModelFactoryClient;
import client.model.loan.Loan;
import client.model.loan.LoanModelClient;
import client.model.loan.LoanModelManagerClient;
import database.loan.LoanDAOImpl;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventTypes;

public class MyMaterialVM
{
  private ObservableList<Loan> activeLoans;
  private StringProperty loanIDProperty;

  public MyMaterialVM()
  {
    //TODO: Find ud af hvordan vi holder styr på hvem der er logget på.
    //TODO: ændre cpr

    activeLoans = FXCollections.observableArrayList();

    if (ModelFactoryClient.getInstance().getLoanModelClient().getAllLoansByCPR("111111-1111") != null)
    {
      activeLoans.addAll(ModelFactoryClient.getInstance().getLoanModelClient().getAllLoansByCPR("111111-1111"));
    }
    ModelFactoryClient.getInstance().getLoanModelClient().addPropertyChangeListener(EventTypes.LOANREGISTERED,
        evt -> activeLoans.add((Loan) evt.getNewValue()));
    loanIDProperty = new SimpleStringProperty();
  }


  public ObservableList<Loan> getLoanList()
  {
    return activeLoans;
  }


  public StringProperty loanIDProperty()
  {
    return loanIDProperty;
  };
}
