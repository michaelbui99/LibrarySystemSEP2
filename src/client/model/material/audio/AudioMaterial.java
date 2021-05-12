package client.model.material.audio;

import client.model.material.Material;

public abstract class AudioMaterial extends Material
{
  private int playDuration;
  public AudioMaterial(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, );
    this.playDuration = playDuration;
  }

  public int getPlayDuration()
  {
    return playDuration;
  }
}
