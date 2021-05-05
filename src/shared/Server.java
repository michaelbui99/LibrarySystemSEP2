package shared;

public interface Server
{
  UserServer getUserServer();
  MaterialServer getMaterialServer();
  LoanServer getLoanServer();
  ChatServer getChatServer();
}
