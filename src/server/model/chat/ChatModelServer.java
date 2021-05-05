package server.model.chat;

import shared.PropertyChangeSubject;

import java.io.IOException;
import java.util.List;

public interface ChatModelServer extends PropertyChangeSubject
{
  void  sendMessage(String msg);
  List<String> getConnectedUsers();
  String getUserName();
  void setUserName(String name, String oldName);
}
