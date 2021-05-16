package shared.person.librarian;

import java.util.ArrayList;
import java.util.List;

public class LibrarianList
{
  private List<Librarian> librarians;

  public LibrarianList()
  {
    librarians = new ArrayList<>();
  }

  public void addLibrarian(Librarian librarian)
  {
    librarians.add(librarian);
  }

  public Librarian getLibrarianByEmployeeNo(int employeeNo)
  {
    for (Librarian librarian : librarians)
    {
      if (librarian.getEmployee_no() == employeeNo)
      {
        return librarian;
      }
    }
    return null;
  }
}
