package shared.network;

import java.beans.PropertyChangeListener;

/**
 * Property change subject
 *
 * @author Lilian
 * @version 1.0
 */
public interface PropertyChangeSubject
{
  /**
   * Adds a PropertyChangeListener to the listener list for a specific property.
   * If propertyName or listener is null, no exception is thrown and no action is taken.
   *
   * @param name     The property's name
   * @param listener The property change listener to be added
   */
  void addPropertyChangeListener(String name, PropertyChangeListener listener);

  /**
   * Adds a PropertyChangeListener to the listener list for a specific property.
   * If propertyName or listener is null, no exception is thrown and no action is taken.
   * If propertyName or listener is null, no exception is thrown and no action is taken.
   *
   * @param listener The property change listener to be added
   */
  void addPropertyChangeListener(PropertyChangeListener listener);

  /**
   * Remove a PropertyChangeListener to the listener list for a specific property.
   * This method should be used to remove PropertyChangeListeners that were registered for a specific bound property.
   * If propertyName or listener is null, no exception is thrown and no action is taken.
   *
   * @param name     The property's name
   * @param listener The property change listener to be added
   */
  void removePropertyChangeListener(String name,
      PropertyChangeListener listener);

  /**
   * Remove a PropertyChangeListener to the listener list for a specific property.
   * This method should be used to remove PropertyChangeListeners that were registered for a specific bound property.
   * If propertyName or listener is null, no exception is thrown and no action is taken.
   *
   * @param listener The property change listener to be added
   */
  void removePropertyChangeListener(PropertyChangeListener listener);
}
