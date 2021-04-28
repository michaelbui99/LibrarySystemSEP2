package client.model.material.audio;

public class CD extends AudioMaterial
{
  private int placeID;
  public CD(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration, int placeID)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, playDuration);
    this.placeID = placeID;
  }

  @Override public String getMaterialType()
  {
    return "CD";
  }
}
