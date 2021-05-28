package shared.materials.audio;

import shared.materials.Place;

import java.io.Serializable;

public class CD extends AudioMaterial implements Serializable
{
  private Place place;

  public CD(int materialID, int copyNumber, String title, String publisher,
      String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, Place placeID,
      String url)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, playDuration, url);
    this.place = placeID;
  }

  @Override public String getMaterialType()
  {
    return "CD";
  }

  @Override public String getMaterialDetails()
  {
    String placeString = " ";
    if (place != null)
    {
      placeString = "Placering: " + place.getHallNo() + ">" +place.getDepartment()  + ">" + place.getGenre() + ">" + place.getCreatorLName();
    }
    return "Titlel: " + getTitle() + " \n" + "Forlag: " + getPublisher() + "\n"
        + "Udgivelsesdato: " + getReleaseDate() + "\n" + "Sprog: "
        + getLanguage() + "\n" + "Målgruppe: " + getTargetAudience() + "\n"
        + "Spillelængde (minutter): " + getPlayDuration() + "\n"
        + "Beskrivelse: " + getDescription() + "\n" + "Emneord: "
        + getKeywords() + "\n" + placeString;

  }
}
