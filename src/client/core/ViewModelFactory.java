package client.core;

import client.view.adduser.AddUserVM;
import client.view.administration.AdministrationVM;
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

  public static ViewModelFactory getInstance(){
    if (viewModelFactory == null){
      viewModelFactory = new ViewModelFactory();
    }
    return viewModelFactory;
  }

  private AddUserVM addUserVM;
  private AdministrationVM administrationVM;
  private BorrowReserveVM borrowReserveVM;
  private CopiesVM copiesVM;
  private MainVM mainVM;
  private MyMaterialVM myMaterialVM;
  private RegisterMaterialVM registerMaterialVM;
  private SearchVM searchVM;
  private StaffLogInVM staffLogInVM;


  public AddUserVM getAddUserVM(){
    if (addUserVM == null){
      addUserVM = new AddUserVM();
    }
    return addUserVM;
  }

  public AdministrationVM getAdministrationVM(){
    if (administrationVM == null){
      administrationVM = new AdministrationVM();
    }
    return administrationVM;
  }

  public BorrowReserveVM getBorrowReseveVM(){
    if (borrowReserveVM == null){
      borrowReserveVM = new BorrowReserveVM();
    }
    return borrowReserveVM;
  }

  public CopiesVM getCopiesVM(){
    if (copiesVM == null){
      copiesVM = new CopiesVM();
    }
    return copiesVM;
  }

  public MainVM getMainVM(){
    if (mainVM == null){
      mainVM = new MainVM();
    }
    return mainVM;
  }

  public MyMaterialVM getMyMaterialVM(){
    if (myMaterialVM == null){
      myMaterialVM = new MyMaterialVM(ModelFactory.getInstance()
          .getLoanModelClient());
    }
    return myMaterialVM;
  }

  public RegisterMaterialVM getRegisterMaterialVM(){
    if (registerMaterialVM == null){
      registerMaterialVM = new RegisterMaterialVM();
    }
    return registerMaterialVM;
  }

  public SearchVM getSearchVM(){
    if (searchVM == null){
      searchVM = new SearchVM();
    }
    return searchVM;
  }

  public StaffLogInVM getStaffLogInVM(){
    if (staffLogInVM == null){
      staffLogInVM = new StaffLogInVM();
    }
    return staffLogInVM;
  }

}
