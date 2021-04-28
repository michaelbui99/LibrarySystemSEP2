package client.model.material;

public abstract class Material implements MaterialInterface
{
  private int materialID, copyNumber;
  private String title, targetAudience, description, keywords, publisher, releaseDate, language;
  private MaterialStatus materialStatus;

  public Material(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String keywords,
      String targetAudience, String language)
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
    materialStatus = MaterialStatus.Available;
  }

  public void setMaterialStatus(MaterialStatus materialStatus)
  {
    this.materialStatus = materialStatus;
  }



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

  public abstract String getMaterialType();
}
