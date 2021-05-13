package client.model.material.reading;

import java.io.Serializable;

public class EBook extends ReadingMaterial implements Serializable
{
  private String licenseNr;
  private String genre, author;



  public EBook(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language,  int pageCount, String licenseNr, String genre, String author)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, pageCount);
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
