package shared.materials;

//Lilian
public interface MaterialInterface
{
  void setMaterialStatus(MaterialStatus materialStatus);
  int getMaterialID();
  int getCopyNumber();
  String getTitle();
  String getTargetAudience();
  String getDescription();
  String getKeywords();
  String getPublisher();
  String getReleaseDate();
  String getLanguage();
  MaterialStatus getMaterialStatus();
  String getMaterialType();
}
