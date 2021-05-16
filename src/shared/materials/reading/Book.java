package shared.materials.reading;

import shared.places.Place;
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
}
