package shared.materials;

/**
 * Material interface
 *
 * @author Lilian
 * @version 1.0
 */
public interface MaterialInterface
{
  /**
   * Give a material a status based on the given argument
   *
   * @param materialStatus The new  material status
   */
  void setMaterialStatus(MaterialStatus materialStatus);

  /**
   * Getter of material id
   *
   * @return an Integer as material id
   */
  int getMaterialID();

  /**
   * Getter for copy number
   *
   * @return an Integer as copy
   */
  int getCopyNumber();

  /**
   * Getter for title
   *
   * @return a String as title
   */
  String getTitle();

  /**
   * Getter for target audience
   *
   * @return a String as a target audience
   */
  String getTargetAudience();

  /**
   * Getter for description
   *
   * @return a String as a description
   */
  String getDescription();

  /**
   * Getter for keywords
   *
   * @return a String as keywords
   */
  String getKeywords();

  /**
   * Getter for publisher
   *
   * @return a String as a publisher
   */
  String getPublisher();

  /**
   * Getter for release date
   *
   * @return a String as a release date
   */
  String getReleaseDate();

  /**
   * Getter for language
   *
   * @return a String as language
   */
  String getLanguage();

  /**
   * Getter for material status
   *
   * @return a MaterialStatus object as material status
   */
  MaterialStatus getMaterialStatus();

  /**
   * Getter for material type
   *
   * @return a String as a material type
   */
  String getMaterialType();
}
