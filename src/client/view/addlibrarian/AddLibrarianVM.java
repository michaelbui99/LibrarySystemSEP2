package client.view.addlibrarian;

import client.core.ModelFactory;
import client.model.loan.Address;

public class AddLibrarianVM
{
  public AddLibrarianVM()
  {
  }

  public void addLibrarian(int employee_no, String firstName, String lastName,
      String cpr, String tlfNumber, String email, Address address,
      String password)
  {
    ModelFactory.getInstance().getUserModelClient()
        .registerLibrarian(employee_no, firstName, lastName, cpr, tlfNumber,
            email, address, password);
  }
}
