package client.view.addlibrarian;

import client.core.ModelFactoryClient;
import shared.places.Address;

public class AddLibrarianVM
{
  public AddLibrarianVM()
  {
  }

  public void addLibrarian(int employee_no, String firstName, String lastName,
      String cpr, String tlfNumber, String email, Address address,
      String password)
  {
    ModelFactoryClient.getInstance().getUserModelClient()
        .registerLibrarian(employee_no, firstName, lastName, cpr, tlfNumber,
            email, address, password);
  }
}
