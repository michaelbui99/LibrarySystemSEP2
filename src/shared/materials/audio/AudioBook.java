package shared.materials.audio;

import shared.person.MaterialCreator;

import java.io.Serializable;

public class AudioBook extends AudioMaterial implements Serializable
{
  private MaterialCreator author;

  public AudioBook(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, MaterialCreator author,
      String url)
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
}
