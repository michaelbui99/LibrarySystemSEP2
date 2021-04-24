package client.model.material.audio;

import client.model.material.Material;

public abstract class AudioMaterial extends Material
{
  private double playDuration;
  public AudioMaterial(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, double playDuration)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language,"",0); //jeg har tilføje isbn og pageCount fordi vi har super constructor og vi miste 2 arguments
    this.playDuration = playDuration;
  }

  public double getPlayDuration()
  {
    return playDuration;
  }
}
