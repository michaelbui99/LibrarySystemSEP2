package client.view.borrowercontactinfo;

import client.core.ModelFactoryClient;
import database.DatabaseBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.network.ServerImpl;
import shared.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BorrowerContactInfoVMTest
{
  private BorrowerContactInfoVM viewModel;
  private DatabaseBuilder databaseBuilder;

  @BeforeAll static void serverSetup() throws AlreadyBoundException, RemoteException
  {
    Server server = new ServerImpl();
    server.start();
  }

  @BeforeEach void setup() throws SQLException
  {
     viewModel = new BorrowerContactInfoVM(
        ModelFactoryClient.getInstance().getUserModelClient());
     databaseBuilder = new DatabaseBuilder();
    databaseBuilder.createDummyDatabaseDataWithoutLoan();
  }

  @Test void PropertiesAreSetIfBorrowerExist() throws SQLException
  {
    viewModel.cprProperty().set("111111-1111");
    viewModel.getBorrowerInfo();
    assertEquals("Michael", viewModel.firstNameProperty().get());
    assertEquals("Bui", viewModel.lastNameProperty().get());
    assertEquals("+4512345678", viewModel.phoneNumberProperty().get());
    assertEquals("Axelborg 8 8700 Horsens", viewModel.addressProperty().get());
    assertEquals("michael@gmail.com", viewModel.emailProperty().get());
    assertEquals("", viewModel.warningProperty().get());
  }

  @Test void WarningPropertyIsSetWhenCPRPropertyIsEmpty()
  {
    viewModel.cprProperty().set("");
    viewModel.getBorrowerInfo();
    assertEquals("Indtast et CPR nummer.", viewModel.warningProperty().get());
  }

  @Test void WarningPropertyIsSetWhenCPRDoesNotContainDash()
  {
    viewModel.cprProperty().set("111111 1111");
    viewModel.getBorrowerInfo();
    assertEquals("Ugyldigt CPR", viewModel.warningProperty().get());
  }

  @Test void WarningPropertyIsSetWhenCPRLengthIs12()
  {
    viewModel.cprProperty().set("111111-11111");
    viewModel.getBorrowerInfo();
    assertEquals("Ugyldigt CPR", viewModel.warningProperty().get());
  }

  @Test void WarningPropertyIsSetWhenCPRLengthIs10()
  {
    viewModel.cprProperty().set("111111-111");
    viewModel.getBorrowerInfo();
    assertEquals("Ugyldigt CPR", viewModel.warningProperty().get());
  }

  @Test void WarningPropertyIsSetWhenCPRContainsAChar()
  {
    viewModel.cprProperty().set("111111-111a");
    viewModel.getBorrowerInfo();
    assertEquals("Ugyldigt CPR", viewModel.warningProperty().get());
  }

  @Test void WarningPropertyIsSetWhenBorrowerDoesNotExist()
  {
    viewModel.cprProperty().set("111111-1122");
    viewModel.getBorrowerInfo();
    assertEquals("Ingen låner med CPR: 111111-1122 er registreret i systemet",
        viewModel.warningProperty().get());
  }

  @Test void NonWarningPropertiesAreClearedOnWarning()
  {
    viewModel.cprProperty().set(" ");
    viewModel.firstNameProperty().set("TEST");
    viewModel.lastNameProperty().set("TEST");
    viewModel.addressProperty().set("TEST ");
    viewModel.emailProperty().set("TEST ");
    viewModel.phoneNumberProperty().set("TEST ");

    viewModel.getBorrowerInfo();
    assertEquals("",    viewModel.firstNameProperty().get());
    assertEquals("",    viewModel.lastNameProperty().get());
    assertEquals("",    viewModel.addressProperty().get());
    assertEquals("",    viewModel.emailProperty().get());
    assertEquals("",    viewModel.phoneNumberProperty().get());
    assertEquals("", viewModel.cprProperty().get());
  }
}