package client.model.material;

public class DVD extends Material
{
  private int placeID;
  private String subtitlesLanguage;
  private double playDuration;



  public DVD(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage, double playDuration, int placeID)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, "", 0); //jeg har tilføje isbn og pageCount fordi vi har super constructor og vi miste 2 arguments
    this.subtitlesLanguage = subtitlesLanguage;
    this.playDuration = playDuration;

    this.placeID = placeID;
  }

  public String getSubtitlesLanguage()
  {
    return subtitlesLanguage;
  }

  public double getPlayDuration()
  {
    return playDuration;
  }

  @Override public String getMaterialType()
  {
    return "DVD";
  }
}
