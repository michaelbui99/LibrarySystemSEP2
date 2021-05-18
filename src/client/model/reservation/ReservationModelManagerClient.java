package client.model.reservation;

import client.network.Client;
import shared.materials.Material;
import shared.person.borrower.Borrower;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ReservationModelManagerClient implements ReservationModelClient
{
  private PropertyChangeSupport support;
  private Client client;

  public ReservationModelManagerClient(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);
  }

  @Override public void registerReservation(Material material,
      Borrower borrower)
  {
    client.registerReservation(material, borrower);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(name, listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}
