package client.model.material.reading;

public class Book extends ReadingMaterial
{

  public Book(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, isbn, pageCount);
  }


  @Override public String getMaterialType()
  {
    return "Book";
  }
}
