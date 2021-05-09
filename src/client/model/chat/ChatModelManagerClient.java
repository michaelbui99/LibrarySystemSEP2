package client.model.chat;

import client.network.Client;
import client.network.RMIClient;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

public class ChatModelManagerClient implements ChatModelClient
{
  public ChatModelManagerClient(Client client)
  {

  }

  @Override public void sendMessage(String msg)
  {

  }

  @Override public List<String> getConnectedUsers()
  {
    return null;
  }

  @Override public String getUserName()
  {
    return null;
  }

  @Override public void setUserName(String name, String oldName)
      throws IOException, ClassNotFoundException
  {

  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {

  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {

  }

  @Override public void removePropertyChangeListener(String name,
      PropertyChangeListener listener)
  {

  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {

  }
}
