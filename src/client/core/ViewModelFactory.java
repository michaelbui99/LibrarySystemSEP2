package client.core;

import client.view.addlibrarian.AddLibrarianVM;
import client.view.adduser.AddUserVM;
import client.view.borrower.BorrowerWindowVM;
import client.view.borrowercontactinfo.BorrowerContactInfoVM;
import client.view.copies.CopiesVM;
import client.view.loanReserve.LoanReserveVM;
import client.view.main.MainVM;
import client.view.myLoans.MyLoansVM;
import client.view.myreservations.MyReservationsVM;
import client.view.registermaterial.RegisterMaterialVM;
import client.view.search.SearchVM;
import client.view.stafflogin.StaffLogInVM;

public class ViewModelFactory
{
  private static ViewModelFactory viewModelFactory;

  public static ViewModelFactory getInstance()
  {
    if (viewModelFactory == null)
    {
      viewModelFactory = new ViewModelFactory();
    }
    return viewModelFactory;
  }

  private AddUserVM addUserVM;
  private AddLibrarianVM addLibrarianVM;
  private LoanReserveVM loanReserveVM;
  private CopiesVM copiesVM;
  private MainVM mainVM;
  private MyLoansVM myLoansVM;
  private RegisterMaterialVM registerMaterialVM;
  private SearchVM searchVM;
  private StaffLogInVM staffLogInVM;
  private MyReservationsVM myReservationsVM;
  private BorrowerContactInfoVM borrowerContactInfoVM;
  private BorrowerWindowVM borrowerWindowVM;

  public AddUserVM getAddUserVM()
  {
    if (addUserVM == null)
    {
      addUserVM = new AddUserVM(
          ModelFactoryClient.getInstance().getUserModelClient());
    }
    return addUserVM;
  }

  public AddLibrarianVM getAddLibrarianVM()
  {
    if (addLibrarianVM == null)
    {
      addLibrarianVM = new AddLibrarianVM(
          ModelFactoryClient.getInstance().getUserModelClient());
    }
    return addLibrarianVM;
  }

  public LoanReserveVM getLoanReserveVM()
  {
    if (loanReserveVM == null)
    {
      loanReserveVM = new LoanReserveVM(
          ModelFactoryClient.getInstance().getReservationModelClient(),
          ModelFactoryClient.getInstance().getLoanModelClient(),
          ModelFactoryClient.getInstance().getMaterialModelClient());
    }
    return loanReserveVM;
  }

  public CopiesVM getCopiesVM()
  {
    if (copiesVM == null)
    {
      copiesVM = new CopiesVM(
          ModelFactoryClient.getInstance().getMaterialModelClient());
    }
    return copiesVM;
  }

  public MainVM getMainVM()
  {
    if (mainVM == null)
    {
      mainVM = new MainVM(
          ModelFactoryClient.getInstance().getUserModelClient());
    }
    return mainVM;
  }

  public MyLoansVM getMyMaterialVM()
  {
    if (myLoansVM == null)
    {
      myLoansVM = new MyLoansVM(
          ModelFactoryClient.getInstance().getLoanModelClient(),
          ModelFactoryClient.getInstance().getUserModelClient());
    }
    return myLoansVM;
  }

  public RegisterMaterialVM getRegisterMaterialVM()
  {
    if (registerMaterialVM == null)
    {
      registerMaterialVM = new RegisterMaterialVM(
          ModelFactoryClient.getInstance().getMaterialModelClient());
    }
    return registerMaterialVM;
  }

  public SearchVM getSearchVM()
  {
    if (searchVM == null)
    {
      searchVM = new SearchVM(
          ModelFactoryClient.getInstance().getMaterialModelClient());
    }
    return searchVM;
  }

  public StaffLogInVM getStaffLogInVM()
  {
    if (staffLogInVM == null)
    {
      staffLogInVM = new StaffLogInVM(
          ModelFactoryClient.getInstance().getUserModelClient());
    }
    return staffLogInVM;
  }

  public MyReservationsVM getMyReservationsVM()
  {
    if (myReservationsVM == null)
    {
      myReservationsVM = new MyReservationsVM(
          ModelFactoryClient.getInstance().getReservationModelClient(),
          ModelFactoryClient.getInstance().getUserModelClient());
    }
    return myReservationsVM;
  }

  public BorrowerContactInfoVM getBorrowerContactInfoVM()
  {
    if (borrowerContactInfoVM == null)
    {
      borrowerContactInfoVM = new BorrowerContactInfoVM(
          ModelFactoryClient.getInstance().getUserModelClient());
    }
    return borrowerContactInfoVM;
  }

  public BorrowerWindowVM getBorrowerWindowVM()
  {
    if (borrowerWindowVM == null)
    {
      borrowerWindowVM = new BorrowerWindowVM(
          ModelFactoryClient.getInstance().getUserModelClient());
    }
    return borrowerWindowVM;
  }
}
