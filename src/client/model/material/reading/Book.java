package client.model.material.reading;

import java.io.Serializable;

public class Book extends ReadingMaterial implements Serializable
{
 private String isbn;
 private int placeId;

  public Book(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount,
      int placeId, String author)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, pageCount);
    this.isbn = isbn;
    this.placeId = placeId;
  }

  public String getIsbn()
  {
    return isbn;
  }

  public int getPlaceId()
  {
    return placeId;
  }

  @Override public String getMaterialType()
  {
    return "Book";
  }


}
