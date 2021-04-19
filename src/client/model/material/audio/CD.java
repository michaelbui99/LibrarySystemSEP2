package client.model.material.audio;

public class CD extends AudioMaterial
{

  public CD(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, playDuration);
  }

  @Override public String getMaterialType()
  {
    return "CD";
  }
}
