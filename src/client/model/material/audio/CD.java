package client.model.material.audio;

import java.io.Serializable;

public class CD extends AudioMaterial implements Serializable
{
  private int placeID;
  public CD(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, int placeID,
      String url)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, playDuration, url);
    this.placeID = placeID;
  }

  @Override public String getMaterialType()
  {
    return "CD";
  }
}
