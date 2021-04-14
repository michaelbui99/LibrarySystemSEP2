package client.model;

public abstract class Material
{
  private String title;

  public Material(String title)
  {
    this.title = title;
  }

  public abstract String getMaterialType();
}
