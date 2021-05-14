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

public class ViewModelFactory {
    private static ViewModelFactory viewModelFactory;

    public static ViewModelFactory getInstance() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory();
        }
        return viewModelFactory;
    }

    private AddUserVM addUserVM;


    public AddUserVM getAddUserVM() {
        if (addUserVM == null) {
            addUserVM = new AddUserVM();
        }
        return addUserVM;
    }

    private AddLibrarianVM addLibrarianVM;

    public AddLibrarianVM getAddLibrarianVM() {
        if (addLibrarianVM == null) {
            addLibrarianVM = new AddLibrarianVM();
        }
        return addLibrarianVM;
    }

    private BorrowReserveVM borrowReserveVM;

    public BorrowReserveVM getBorrowReserveVM() {
        if (borrowReserveVM == null) {
            borrowReserveVM = new BorrowReserveVM();
        }
        return borrowReserveVM;
    }

    private CopiesVM copiesVM;


    public CopiesVM getCopiesVM() {
        if (copiesVM == null) {
            copiesVM = new CopiesVM();
        }
        return copiesVM;
    }

    private MainVM mainVM;


    public MainVM getMainVM() {
        if (mainVM == null) {
            mainVM = new MainVM();
        }
        return mainVM;
    }

    private MyMaterialVM myMaterialVM;

    public MyMaterialVM getMyMaterialVM() {
        if (myMaterialVM == null) {
            myMaterialVM = new MyMaterialVM(
                    ModelFactoryClient.getInstance().getLoanModelClient());
        }
        return myMaterialVM;
    }

    private RegisterMaterialVM registerMaterialVM;


    public RegisterMaterialVM getRegisterMaterialVM() {
        if (registerMaterialVM == null) {
            registerMaterialVM = new RegisterMaterialVM(
                    ModelFactoryClient.getInstance().getMaterialModelClient()
            );
        }
        return registerMaterialVM;
    }

    private SearchVM searchVM;

    public SearchVM getSearchVM() {
        if (searchVM == null) {
            searchVM = new SearchVM(
                    ModelFactoryClient.getInstance().getMaterialModelClient()
            );
        }
        return searchVM;
    }

    private StaffLogInVM staffLogInVM;

    public StaffLogInVM getStaffLogInVM() {
        if (staffLogInVM == null) {
            staffLogInVM = new StaffLogInVM();
        }
        return staffLogInVM;
    }

}
