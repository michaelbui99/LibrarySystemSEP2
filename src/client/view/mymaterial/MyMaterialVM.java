package client.view.mymaterial;

import client.model.loan.Loan;
import client.model.loan.LoanModelClient;
import client.model.loan.LoanModelManagerClient;
import database.loan.LoanDAOImpl;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventTypes;

public class MyMaterialVM
{
  private LoanModelClient loanModel;
  private ObservableList<Loan> activeLoans;
  private ObjectProperty<Loan> loanProperty;

  public MyMaterialVM(LoanModelClient loanModel)
  {
    //TODO: Find ud af hvordan vi holder styr på hvem der er logget på.
    //TODO: ændre cpr
    this.loanModel = loanModel;
//    activeLoans.addAll(loanModel.getAllLoansByCPR());
    activeLoans = FXCollections.observableArrayList();
    activeLoans.addAll(loanModel.getAllLoansByCPR("111111-1111"));
    loanModel.addPropertyChangeListener(EventTypes.LOANREGISTERED,
        evt -> activeLoans.add((Loan) evt.getNewValue()));
    loanProperty = new SimpleObjectProperty<>();
  }

  public ObservableList<Loan> getLoanList()
  {
    return activeLoans;
  }

  public void returnMaterial()
  {
    loanModel.deliverMaterial(loanProperty.get());
  }
  public ObjectProperty<Loan> loanObjectProperty()
  {
    return loanProperty;
  }
}
