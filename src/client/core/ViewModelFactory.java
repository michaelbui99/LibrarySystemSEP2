package client.core;

import client.view.addlibrarian.AddLibrarianVM;
import client.view.adduser.AddUserVM;
import client.view.borrowreserve.BorrowReserveVM;
import client.view.copies.CopiesVM;
import client.view.main.MainVM;
import client.view.mymaterial.MyMaterialVM;
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
  private BorrowReserveVM borrowReserveVM;
  private CopiesVM copiesVM;
  private MainVM mainVM;
  private MyMaterialVM myMaterialVM;
  private RegisterMaterialVM registerMaterialVM;
  private SearchVM searchVM;
  private StaffLogInVM staffLogInVM;

  public AddUserVM getAddUserVM()
  {
    if (addUserVM == null)
    {
      addUserVM = new AddUserVM();
    }
    return addUserVM;
  }

  public AddLibrarianVM getAddLibrarianVM()
  {
    if (addLibrarianVM == null)
    {
      addLibrarianVM = new AddLibrarianVM();
    }
    return addLibrarianVM;
  }

  public BorrowReserveVM getBorrowReserveVM()
  {
    if (borrowReserveVM == null)
    {
      borrowReserveVM = new BorrowReserveVM();
    }
    return borrowReserveVM;
  }

  public CopiesVM getCopiesVM()
  {
    if (copiesVM == null)
    {
      copiesVM = new CopiesVM();
    }
    return copiesVM;
  }

  public MainVM getMainVM()
  {
    if (mainVM == null)
    {
      mainVM = new MainVM();
    }
    return mainVM;
  }

  public MyMaterialVM getMyMaterialVM()
  {
    if (myMaterialVM == null)
    {
      myMaterialVM = new MyMaterialVM(
          ModelFactoryClient.getInstance().getLoanModelClient());
    }
    return myMaterialVM;
  }

  public RegisterMaterialVM getRegisterMaterialVM()
  {
    if (registerMaterialVM == null)
    {
      registerMaterialVM = new RegisterMaterialVM(
          ModelFactoryClient.getInstance().getMaterialModelClient()
      );
    }
    return registerMaterialVM;
  }

  public SearchVM getSearchVM()
  {
    if (searchVM == null)
    {
      searchVM = new SearchVM(
          ModelFactoryClient.getInstance().getMaterialModelClient()
      );
    }
    return searchVM;
  }

  public StaffLogInVM getStaffLogInVM()
  {
    if (staffLogInVM == null)
    {
      staffLogInVM = new StaffLogInVM();
    }
    return staffLogInVM;
  }

}
