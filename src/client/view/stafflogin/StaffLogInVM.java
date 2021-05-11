package client.view.stafflogin;

import client.core.ModelFactory;

public class StaffLogInVM
{
  public StaffLogInVM()
  {
  }

  public boolean login(int employee_no, String password)
  {
    return ModelFactory.getInstance().getUserModelClient().librarianLogin(employee_no, password);
  }
}
