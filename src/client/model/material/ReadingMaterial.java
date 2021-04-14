package client.model.material;

public abstract class ReadingMaterial extends Material
{
  private String isbn;
  private int pageCount;

  public ReadingMaterial(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language);
    this.isbn = isbn;
    this.pageCount = pageCount;
  }

  public String getIsbn()
  {
    return isbn;
  }

  public int getPageCount()
  {
    return pageCount;
  }
}
