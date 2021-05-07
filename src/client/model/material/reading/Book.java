package client.model.material.reading;

public class Book extends ReadingMaterial
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

  @Override public String getMaterialType()
  {
    return "Book";
  }


}
