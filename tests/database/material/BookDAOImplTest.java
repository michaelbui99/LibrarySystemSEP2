package database.material;

import org.junit.jupiter.api.Test;
import shared.materials.Place;
import shared.person.MaterialCreator;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOImplTest
{

  @Test void bookAlreadyExistTest()
  {
    try
    {
      assertTrue(BookDAOImpl.getInstance().bookAlreadyExists("Title1", "Publisher1", "2020-12-12", "HELLO DESC", "Voksen", "Dansk", "321432432", 200, new MaterialCreator(1, "Bob", "Bobsen", "2020-12-12", "Denmark"), "Fantasy"));
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

  }

}