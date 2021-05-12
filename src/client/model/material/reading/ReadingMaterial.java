package client.model.material.reading;

import client.model.material.Material;

public abstract class ReadingMaterial extends Material
{
  private int pageCount;

  public ReadingMaterial(int materialID, int copyNumber, String title,
      String publisher, String releaseDate, String description, String tags,
      String targetAudience, String language, int pageCount)
  {
    super(materialID, copyNumber, title, publisher, releaseDate, description,
        tags, targetAudience, language, );

    this.pageCount = pageCount;
  }



  public int getPageCount()
  {
    return pageCount;
  }
}
