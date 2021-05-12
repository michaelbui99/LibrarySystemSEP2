package client.view;

import client.core.ViewModelFactory;
import client.view.addlibrarian.AddLibrarianController;
import client.view.adduser.AddUserController;
import client.view.administration.AdministrationController;
import client.view.borrowreserve.BorrowReserveController;
import client.view.copies.CopiesController;
import client.view.main.MainController;
import client.view.mymaterial.MyMaterialController;
import client.view.registermaterial.RegisterMaterialController;
import client.view.search.SearchController;
import client.view.stafflogin.StaffLogInController;
import client.view.user.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.naming.directory.SearchControls;
import java.io.IOException;

public class ViewHandler extends Application
{
  private Stage stage = new Stage();
  private static ViewHandler viewHandler;
//singelton
 private ViewHandler()
  {

  }

  public static ViewHandler getInstance(){
    if (viewHandler == null){
      viewHandler = new ViewHandler();
    }
    return viewHandler;
  }

  public void openView(String id) throws IOException
  {
    Scene scene;
    FXMLLoader loader = new FXMLLoader();
    Parent root= null;
    if (id.equals("AddUser"))
    {
      loader.setLocation(getClass().getResource("../view/adduser/" + id +"View.fxml"));
      root = loader.load();
      AddUserController addUserController = loader.getController();
      addUserController.init();
      stage.setTitle("AddUser");
    }
    else if (id.equals("AddLibrarian"))
    {
      loader.setLocation(getClass().getResource("../view/addlibrarian/" + id +"View.fxml"));
      root = loader.load();
      AddLibrarianController addLibrarianController = loader.getController();
      addLibrarianController.init();
      stage.setTitle("AddLibrarian");
    }

    else if (id.equals("BorrowReserve"))
    {
      loader.setLocation(getClass().getResource("../view/borrowreserve/" + id +"View.fxml"));
      root = loader.load();
      BorrowReserveController borrowReserveController = loader.getController();
      borrowReserveController.init();
      stage.setTitle("BorrowReserve");
    }
    else if (id.equals("Copies"))
    {
      loader.setLocation(getClass().getResource("../view/copies/" + id +"View.fxml"));
      root = loader.load();
      CopiesController copiesController = loader.getController();
      copiesController.init();
      stage.setTitle("Copies");
    }
    else if (id.equals("Main"))
    {
      loader.setLocation(getClass().getResource("../view/main/" + id +"View.fxml"));
      root = loader.load();
      MainController mainController = loader.getController();
      mainController.init();
      stage.setTitle("Main");
    }
    else if (id.equals("MyMaterial"))
    {
      loader.setLocation(getClass().getResource("../view/mymaterial/" + id +"View.fxml"));
      root = loader.load();
      MyMaterialController myMaterialController = loader.getController();
      myMaterialController.init();
      stage.setTitle("MyMaterial");
    }
    else if (id.equals("RegisterMaterial"))
    {
      loader.setLocation(getClass().getResource("../view/registermaterial/" + id +"View.fxml"));
      root = loader.load();
      RegisterMaterialController registerMaterialController = loader.getController();
      registerMaterialController.init(this, ViewModelFactory.getInstance().getRegisterMaterialVM());
      stage.setTitle("RegisterMaterial");
    }
    else if (id.equals("Search"))
    {
      loader.setLocation(getClass().getResource("../view/search/" + id +"View.fxml"));
      root = loader.load();
      SearchController searchController = loader.getController();
      searchController.init();
      stage.setTitle("Search");
    }
    else if (id.equals("StaffLogin"))
    {
      loader.setLocation(getClass().getResource("../view/stafflogin/" + id +"View.fxml"));
      root = loader.load();
      StaffLogInController staffLogInController = loader.getController();
      staffLogInController.init();
      stage.setTitle("StaffLogin");
    }
    else if (id.equals("User"))
    {
      loader.setLocation(getClass().getResource("../view/user/" + id +"View.fxml"));
      root = loader.load();
      UserController userController = loader.getController();
      userController.init();
      stage.setTitle("User");
    }
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @Override public void start(Stage stage) throws Exception
  {
    openView("mainView");
  }
}
