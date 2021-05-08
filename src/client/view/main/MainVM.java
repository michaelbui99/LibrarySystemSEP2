package client.view.main;

import client.model.user.UserModelClient;

import java.sql.SQLException;

public class MainVM
{
  private UserModelClient model;

  public MainVM(UserModelClient model)
  {
    this.model = model;
  }

  public void login(String email, String password)
  {
    try
    {
      model.Login(email, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
