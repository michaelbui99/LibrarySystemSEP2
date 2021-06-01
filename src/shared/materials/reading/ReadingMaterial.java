package shared.materials.reading;

import shared.materials.Material;

import java.io.Serializable;

/**
 * Reading material Object abstract class
 *
 * @author Lilian
 * @version 1.0
 */
public abstract class ReadingMaterial extends Material implements Serializable
{
  private int pageCount;

  public ReadingMaterial(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount)
  {
    super(materialID, copyNumber, title, targetAudience, description, tags,
        publisher, releaseDate, language, null, null);

    this.pageCount = pageCount;
  }

  public int getPageCount()
  {
    return pageCount;
  }
}
