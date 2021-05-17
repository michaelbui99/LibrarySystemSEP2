package client.view.adduser;

import client.core.ModelFactoryClient;
import shared.person.Address;

public class AddUserVM
{
  public AddUserVM()
  {

  }

  public void addUser(String cpr_no, String f_name, String l_name, String email,
      String tel_no, Address address_id, String password)
  {
    ModelFactoryClient.getInstance().getUserModelClient()
        .registerBorrower(cpr_no, f_name, l_name, email, tel_no, address_id,
            password);
  }
}
