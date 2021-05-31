package shared.materials.audio;

import shared.materials.Material;

import java.io.Serializable;

//Lilian
public abstract class AudioMaterial extends Material implements Serializable
{
  private int playDuration;
  public AudioMaterial(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String url)
  {
    super(materialID,copyNumber, title, targetAudience, description,tags, publisher, releaseDate, language, url, null);
    this.playDuration = playDuration;
  }

  public int getPlayDuration()
  {
    return playDuration;
  }
}
