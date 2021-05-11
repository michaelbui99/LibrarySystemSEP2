package client.view.main;

import client.core.ModelFactory;

import java.sql.SQLException;

public class MainVM
{

  public MainVM()
  {
  }

  public void login(String cprNo, String password)
  {
    ModelFactory.getInstance().getUserModelClient()
        .borrowerLogin(cprNo, password);
  }
}
