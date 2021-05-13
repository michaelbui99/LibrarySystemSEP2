package client.model.material;

import java.io.Serializable;

public class DVD extends Material implements Serializable
{
  private int placeID;
  private String subtitlesLanguage;
  private String playDuration;



  public DVD(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage, String playDuration, int placeID, String imageURL)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, imageURL );
    this.subtitlesLanguage = subtitlesLanguage;
    this.playDuration = playDuration;

    this.placeID = placeID;
  }

  public String getSubtitlesLanguage()
  {
    return subtitlesLanguage;
  }

  public String  getPlayDuration()
  {
    return playDuration;
  }

  @Override public String getMaterialType()
  {
    return "DVD";
  }
}
