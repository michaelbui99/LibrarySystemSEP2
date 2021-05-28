package shared.materials.reading;

import shared.materials.Place;
import shared.person.MaterialCreator;

import java.io.Serializable;

public class Book extends ReadingMaterial implements Serializable
{
  private String isbn;
  private Place place;
  private MaterialCreator author;

  public Book(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      Place placeId, MaterialCreator author)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, pageCount);
    this.isbn = isbn;
    this.place = placeId;
    this.author = author;
  }

  public String getIsbn()
  {
    return isbn;
  }

  public Place getPlace()
  {
    return place;
  }

  @Override public String getMaterialType()
  {
    return "Book";
  }

  @Override public String getMaterialDetails()
  {
    String a;
      if (author.getfName() != null && author.getlName() != null)
      {
       a = author.getfName() + " " + author.getlName();
      }
      else
        a = "";

    String placeString = " ";
    if (place != null)
    {
      placeString = "Placering: " + place.getHallNo() + ">" +place.getDepartment()  + ">" + place.getGenre() + ">" + place.getCreatorLName();
    }


    return "Titlel: " + getTitle() + "\n" + "Forfatter: " + a + " \n" + "Forlag: " + getPublisher() + "\n"
        + "Udgivelsesdato: " + getReleaseDate() + "\n" + "ISBN: " + isbn + "\n"
        + "Sprog: " + getLanguage() + "\n" + "Målgruppe: " + getTargetAudience()
        + "\n" + "Sidetal: " + getPageCount() + "\n" + "Beskrivelse: "
        + getDescription() + "\n" + "Emneord: " + getKeywords() + "\n" + placeString;
  }
}
