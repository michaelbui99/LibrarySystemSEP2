package shared.network;

import java.beans.PropertyChangeListener;

//Lilian
public interface PropertyChangeSubject
{
  void addPropertyChangeListener(String name, PropertyChangeListener listener);
  void addPropertyChangeListener(PropertyChangeListener listener);
  void removePropertyChangeListener(String name, PropertyChangeListener listener);
  void removePropertyChangeListener(PropertyChangeListener listener);
}
