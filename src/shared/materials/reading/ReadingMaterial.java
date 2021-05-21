package shared.materials.reading;

import shared.materials.Material;

import java.io.Serializable;

public abstract class ReadingMaterial extends Material implements Serializable
{
  private int pageCount;

  public ReadingMaterial(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount)
  {
    //TODO: INSERT URL IN CONSTRUCTOR
    super(materialID,copyNumber, title, targetAudience, description,tags, publisher, releaseDate, language, null, null);

    this.pageCount = pageCount;
  }

  public int getPageCount()
  {
    return pageCount;
  }
}
