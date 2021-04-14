package client.model.material;

public abstract class AudioMaterial extends Material
{
  private double playDuration;
  public AudioMaterial(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language);
    this.playDuration = playDuration;
  }

  public double getPlayDuration()
  {
    return playDuration;
  }
}
