package server.model;

import shared.PropertyChangeSubject;

import java.io.IOException;
import java.util.List;

public interface ChatModel extends PropertyChangeSubject
{
  void  sendMessage(String msg);
  List<String> getConnectedUsers();
  String getUserName();
  void setUserName(String name, String oldName);
}
