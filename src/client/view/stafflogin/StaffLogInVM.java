package client.view.stafflogin;

import client.core.ModelFactory;

public class StaffLogInVM
{
  public StaffLogInVM()
  {
  }

  public void login(int employee_no, String password)
  {
    ModelFactory.getInstance().getUserModelClient().librarianLogin(employee_no, password);
  }
}
