package client.view.main;

import client.core.ModelFactoryClient;

public class MainVM
{

  public MainVM()
  {
  }

  public boolean login(String cprNo, String password)
  {
   return ModelFactoryClient.getInstance().getUserModelClient()
        .borrowerLogin(cprNo, password);
  }
}
