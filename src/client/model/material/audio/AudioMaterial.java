package client.model.material.audio;

import client.model.material.Material;

import java.io.Serializable;

public abstract class AudioMaterial extends Material implements Serializable
{
  private int playDuration;
  public AudioMaterial(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String url)
  {
    super(materialID, title, publisher, releaseDate, description, targetAudience, language, url);
    this.playDuration = playDuration;
  }

  public int getPlayDuration()
  {
    return playDuration;
  }
}
