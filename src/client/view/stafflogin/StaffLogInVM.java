package client.view.stafflogin;

import client.core.ModelFactoryClient;

public class StaffLogInVM
{
  public StaffLogInVM()
  {
  }

  public boolean login(int employee_no, String password)
  {
    return ModelFactoryClient
        .getInstance().getUserModelClient().librarianLogin(employee_no, password);
  }
}
