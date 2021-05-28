package shared.materials;

import java.io.Serializable;

public class DVD extends Material implements Serializable
{
  private Place place;
  private String subtitlesLanguage;
  private String playDuration;



  public DVD(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, String subtitlesLanguage, String playDuration, Place place, String imageURL)
  {
    super(materialID, title, publisher, releaseDate, description, targetAudience, language, imageURL );
    this.subtitlesLanguage = subtitlesLanguage;
    this.playDuration = playDuration;

    this.place = place;
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

  @Override public String getMaterialDetails()
  {
    String placeString = " ";
    if (place != null)
    {
      placeString = "Placering: " + place.getHallNo() + ">" +place.getDepartment()  + ">" + place.getGenre() + ">" + place.getCreatorLName();
    }
    return "Titlel: " + getTitle() + "\n" + "Undertekstsprog: " + getSubtitlesLanguage()
        + " \n" + "Forlag: " + getPublisher() + "\n"
        + "Udgivelsesdato: " + getReleaseDate() + "\n"
        + "Sprog: " + getLanguage() + "\n" + "Målgruppe: " + getTargetAudience()
        + "\n" + "Spillelængde (minutter): " + playDuration + "\n" + "Beskrivelse: "
        + getDescription() + "\n" + "Emneord: " + getKeywords() + "\n" + placeString;
  }
}
