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
    try
    {
      ModelFactory.getInstance().getUserModelClient().login(cprNo, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
