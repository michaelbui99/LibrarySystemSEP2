package client.model.material.audio;

public class AudioBook extends AudioMaterial
{
  public AudioBook(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, int playDuration, String author,
      String url)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, playDuration, url);
  }

  @Override public String getMaterialType()
  {
    return "Audio Book";
  }
}
