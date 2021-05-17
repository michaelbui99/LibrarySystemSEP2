package shared.materials;

import java.io.Serializable;

public class Place implements Serializable
{
  private String creatorLName, genre;
  private int placeId, hallNo;
  private String department;

  public Place(int placeId, int hallNo, String department, String creatorLName,
      String genre){
    this.creatorLName = creatorLName;
    this.genre = genre;
    this.placeId = placeId;
    this.hallNo = hallNo;
    this.department = department;
  }


  public Place(int hallNo, String department, String creatorLName,
      String genre)
  { this.creatorLName = creatorLName;
    this.genre = genre;
    this.hallNo = hallNo;
    this.department = department;
  }

  public String getCreatorLName()
  {
    return creatorLName;
  }

  public String getGenre()
  {
    return genre;
  }

  public int getPlaceId()
  {
    return placeId;
  }

  public int getHallNo()
  {
    return hallNo;
  }

  public String getDepartment()
  {
    return department;
  }
}
