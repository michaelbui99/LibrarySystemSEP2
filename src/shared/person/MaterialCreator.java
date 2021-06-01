package shared.person;

import java.io.Serializable;
import java.sql.Date;

/**
 * Material creator object class
 *
 * @author Kaper
 * @version 1.0
 */
public class MaterialCreator implements Serializable
{
  private String fName, lName, country, dob;
  private int personId;

  public MaterialCreator(int personId, String fName, String lName, String dob,
      String country)
  {
    this.personId = personId;
    this.fName = fName;
    this.lName = lName;
    this.dob = dob;
    this.country = country;
  }

  public MaterialCreator(String fName, String lName, String dob, String country)
  {
    this.fName = fName;
    this.lName = lName;
    this.dob = dob;
    this.country = country;
  }

  public String getfName()
  {
    return fName;
  }

  public String getlName()
  {
    return lName;
  }

  public String getCountry()
  {
    return country;
  }

  public String getDob()
  {
    return dob;
  }

  public int getPersonId()
  {
    return personId;
  }
}
