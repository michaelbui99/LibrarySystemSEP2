package shared.materials;

import java.io.Serializable;

/**
 * Material object abstract class
 *
 * @author Lilian
 * @author Michael
 * @version 1.0
 */
public abstract class Material implements MaterialInterface, Serializable
{
  private int materialID, copyNumber;
  private String title, targetAudience, description, keywords, publisher, releaseDate, language, imageURL, materialDetails;
  private MaterialStatus materialStatus;
  private final static long serialVersionUID = -8460811401673477634L;

  public Material(int materialID, String title, String publisher,
      String releaseDate, String description, String targetAudience,
      String language, String imageURL)
  {
    this.materialID = materialID;
    this.title = title;
    this.targetAudience = targetAudience;
    this.description = description;
    this.publisher = publisher;
    this.releaseDate = releaseDate;
    this.language = language;
    this.imageURL = imageURL;
    materialStatus = MaterialStatus.Available;
  }

  public Material(int materialID, int copyNumber, String title,
      String targetAudience, String description, String keywords,
      String publisher, String releaseDate, String language, String imageURL,
      MaterialStatus materialStatus)
  {
    this.materialID = materialID;
    this.copyNumber = copyNumber;
    this.title = title;
    this.targetAudience = targetAudience;
    this.description = description;
    this.keywords = keywords;
    this.publisher = publisher;
    this.releaseDate = releaseDate;
    this.language = language;
    this.imageURL = imageURL;
    this.materialStatus = materialStatus;
  }

  public void setMaterialStatus(MaterialStatus materialStatus)
  {
    this.materialStatus = materialStatus;
  }

  public abstract String getMaterialType();
  public abstract String getMaterialDetails();

  public int getMaterialID()
  {
    return materialID;
  }

  public int getCopyNumber()
  {
    return copyNumber;
  }

  public String getTitle()
  {
    return title;
  }

  public String getTargetAudience()
  {
    return targetAudience;
  }

  public String getDescription()
  {
    return description;
  }

  public String getKeywords()
  {
    return keywords;
  }

  public String getPublisher()
  {
    return publisher;
  }

  public String getReleaseDate()
  {
    return releaseDate;
  }

  public String getLanguage()
  {
    return language;
  }

  public MaterialStatus getMaterialStatus()
  {
    return materialStatus;
  }

  public String getImageURL()
  {
    return imageURL;
  }

  public void setKeywords(String keywords)
  {
    this.keywords = keywords;
  }

  @Override public String toString()
  {
    return "Titel: " + title + "\n" + "Type: " + getMaterialType() + "\n"
        + "Beskrivelse: " + description + "\n" + "Udgivelsesdato: "
        + releaseDate;
  }
}
