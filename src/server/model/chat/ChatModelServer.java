package server.model.chat;

import shared.servers.PropertyChangeSubject;

import java.util.List;

public interface ChatModelServer extends PropertyChangeSubject
{
  void  sendMessage(String msg);
  List<String> getConnectedUsers();
  String getUserName();
  void setUserName(String name, String oldName);
}
