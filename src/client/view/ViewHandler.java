package client.view;

import client.core.ViewModelFactory;
import client.view.addlibrarian.AddLibrarianController;
import client.view.adduser.AddUserController;
import client.view.administration.AdministrationController;
import client.view.loanReserve.LoanReserveController;
import client.view.copies.CopiesController;
import client.view.main.MainController;
import client.view.myLoans.MyLoansController;
import client.view.myreservations.MyReservationsController;
import client.view.registermaterial.RegisterMaterialController;
import client.view.search.SearchController;
import client.view.stafflogin.StaffLogInController;
import client.view.borrower.BorrowerWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler extends Application
{
  private Stage stage = new Stage();
  private Stage stage2 = new Stage();
  private static ViewHandler viewHandler;

  //singelton
  private ViewHandler()
  {

  }

  public static ViewHandler getInstance()
  {
    if (viewHandler == null)
    {
      viewHandler = new ViewHandler();
    }
    return viewHandler;
  }

  public void openView(String id) throws IOException
  {
    Scene scene;
    FXMLLoader loader = new FXMLLoader();
    Parent root = null;
    switch (id)
    {
      case "AddUser":
        loader
            .setLocation(getClass().getResource("adduser/" + id + "View.fxml"));
        root = loader.load();
        AddUserController addUserController = loader.getController();
        addUserController.init();
        stage.setTitle("AddUser");
        break;
      case "AddLibrarian":
        loader.setLocation(
            getClass().getResource("addlibrarian/" + id + "View.fxml"));
        root = loader.load();
        AddLibrarianController addLibrarianController = loader.getController();
        addLibrarianController.init();
        stage.setTitle("AddLibrarian");
        break;
      case "BorrowReserve":
        loader.setLocation(
            getClass().getResource("borrowreserve/" + id + "View.fxml"));
        root = loader.load();
        LoanReserveController loanReserveController = loader
            .getController();
        loanReserveController.init();
        stage.setTitle("BorrowReserve");
        break;
      case "Copies":
        loader
            .setLocation(getClass().getResource("copies/" + id + "View.fxml"));
        root = loader.load();
        CopiesController copiesController = loader.getController();
        copiesController.init();
        stage.setTitle("Copies");
        break;
      case "Main":
        loader.setLocation(getClass().getResource("main/" + id + "View.fxml"));
        root = loader.load();
        MainController mainController = loader.getController();
        mainController.init();
        stage.setTitle("Main");
        break;
      case "MyMaterial":
        loader.setLocation(
            getClass().getResource("mymaterial/" + id + "View.fxml"));
        root = loader.load();
        MyLoansController myLoansController = loader.getController();
        myLoansController.init(ViewModelFactory.getInstance().getMyMaterialVM());
        stage.setTitle("MyMaterial");
        break;
      case "MyReservations":
        loader.setLocation(
            getClass().getResource("myreservations/" + id + "View.fxml"));
        root = loader.load();
        MyReservationsController myReservationsController = loader.getController();
        myReservationsController.init();
        stage.setTitle("MyReservations");
        break;
      case "RegisterMaterial":
        loader.setLocation(
            getClass().getResource("registermaterial/" + id + "View.fxml"));
        root = loader.load();
        RegisterMaterialController registerMaterialController = loader
            .getController();
        registerMaterialController.init();
        stage.setTitle("RegisterMaterial");
        break;
      case "Search":
        loader
            .setLocation(getClass().getResource("search/" + id + "View.fxml"));
        root = loader.load();
        SearchController searchController = loader.getController();
        searchController.init();
        stage.setTitle("Search");
        break;
      case "StaffLogin":
        loader.setLocation(
            getClass().getResource("stafflogin/" + id + "View.fxml"));
        root = loader.load();
        StaffLogInController staffLogInController = loader.getController();
        staffLogInController.init();
        stage.setTitle("StaffLogin");
        break;
      case "UserWindow":
        loader.setLocation(getClass().getResource("user/" + id + "View.fxml"));
        root = loader.load();
        BorrowerWindowController borrowerWindowController = loader.getController();
        borrowerWindowController.init();
        stage.setTitle("User");
        break;
      case "Administration":
        loader.setLocation(
            getClass().getResource("administration/" + id + "View.fxml"));
        root = loader.load();
        AdministrationController administrationController = loader
            .getController();
        administrationController.init();
        stage.setTitle("Administration");
        break;
    }
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @Override public void start(Stage stage) throws Exception
  {
    openView("Main");
  }
}
