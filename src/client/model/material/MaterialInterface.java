package client.model.material;

public interface MaterialInterface
{
  public void setMaterialStatus(MaterialStatus materialStatus);
  public int getMaterialID();
  public int getCopyNumber();
  public String getTitle();
  public String getTargetAudience();
  public String getDescription();
  public String getTags();
  public String getPublisher();
  public String getReleaseDate();
  public String getLanguage();
  public MaterialStatus getMaterialStatus();
  public String getMaterialType();
}
