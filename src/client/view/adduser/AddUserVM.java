package client.view.adduser;

import client.model.loan.Address;
import client.model.user.UserModelClient;

import java.sql.SQLException;

public class AddUserVM
{
  private UserModelClient model;

  public AddUserVM(UserModelClient model)
  {
    this.model = model;
  }

  public void addUser(String cpr_no, String f_name, String l_name, String email,
      String tel_no, Address address_id, String password)
  {
    try
    {
      model.registerBorrower(cpr_no, f_name, l_name, email, tel_no, address_id,
          password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
