package shared.network;

//Michael
public class ServerConnectionException extends RuntimeException
{
  public ServerConnectionException()
  {
  }

  public ServerConnectionException(String message)
  {
    super(message);
  }
}
