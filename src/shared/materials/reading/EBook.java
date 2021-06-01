package shared.materials.reading;

import shared.person.MaterialCreator;

import java.io.Serializable;

/**
 * E-book object class
 *
 * @author Lilian
 * @version 1.0
 */
public class EBook extends ReadingMaterial implements Serializable
{
  private String licenseNr;
  private String genre;
  private MaterialCreator author;

  public EBook(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount, String licenseNr,
      String genre, MaterialCreator author)
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

  public MaterialCreator getAuthor()
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

  @Override public String getMaterialDetails()
  {
    return "Titlel: " + getTitle() + "\n" + "Forfatter: " + author.getfName()
        + " " + author.getlName() + " \n" + "Forlag: " + getPublisher() + "\n"
        + "Udgivelsesdato: " + getReleaseDate() + "\n" + "Sprog: "
        + getLanguage() + "\n" + "Målgruppe: " + getTargetAudience() + "\n"
        + "Sidetal: " + getPageCount() + "\n" + "Beskrivelse: "
        + getDescription() + "\n" + "Emneord: " + getKeywords() + "\n";
  }
}
