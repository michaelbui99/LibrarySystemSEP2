package client.model.material.reading;

public class EBook extends ReadingMaterial
{
  private String licenseNr;
  private String author, genre;

  public EBook(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, String licenseNr, String author, String genre)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, isbn, pageCount);
    this.licenseNr = licenseNr;
    this.author = author;
    this.genre = genre;
  }

  public String getLicenseNr()
  {
    return licenseNr;
  }

  public String getAuthor()
  {
    return author;
  }

  public String getGenre()
  {
    return genre;
  }

  @Override public String getMaterialType()
  {
    return "E-Book";
  }
}
