package shared.network;

/**
 * New exception for server connection
 *
 * @author Michael
 * @version 1.0
 */
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
