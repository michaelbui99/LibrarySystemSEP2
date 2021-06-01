package shared.materials.audio;

import shared.person.MaterialCreator;

import java.io.Serializable;

/**
 * Audiobook object class
 *
 * @author Lilian
 * @version 1.0
 */
public class AudioBook extends AudioMaterial implements Serializable
{
  private MaterialCreator author;

  public AudioBook(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration,
      MaterialCreator author, String url)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, playDuration, url);
    this.author = author;
  }

  public MaterialCreator getAuthor()
  {
    return author;
  }

  @Override public String getMaterialType()
  {
    return "Audio Book";
  }

  @Override public String getMaterialDetails()
  {
    return "Titlel: " + getTitle() + " \n" + "Forlag: " + getPublisher() + "\n"
        + "Udgivelsesdato: " + getReleaseDate() + "\n" + "Sprog: "
        + getLanguage() + "\n" + "Målgruppe: " + getTargetAudience() + "\n"
        + "Spillelængde (minutter): " + getPlayDuration() + "\n"
        + "Beskrivelse: " + getDescription() + "\n" + "Emneord: "
        + getKeywords() + "\n";
  }
}
