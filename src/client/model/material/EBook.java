package client.model.material;

public class EBook extends ReadingMaterial
{
  private String licenseNr;

  public EBook(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String isbn, int pageCount, String licenseNr)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, isbn, pageCount);
    this.licenseNr = licenseNr;
  }

  public String getLicenseNr()
  {
    return licenseNr;
  }


  @Override public String getMaterialType()
  {
    return "E-Book";
  }
}
