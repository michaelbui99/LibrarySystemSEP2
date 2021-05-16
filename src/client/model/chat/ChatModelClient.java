package client.model.chat;

import shared.servers.PropertyChangeSubject;

import java.io.IOException;
import java.util.List;

public interface ChatModelClient extends PropertyChangeSubject
{
  void  sendMessage(String msg);
  List<String> getConnectedUsers();
  String getUserName();
  void setUserName(String name, String oldName) throws IOException, ClassNotFoundException;
}
