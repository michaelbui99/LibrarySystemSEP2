package shared.util;

import java.util.List;

public class IDGenerator
{
  private static IDGenerator instance;
  private static Object lock;
  private List<Integer> ids;

  public static IDGenerator getInstance()
  {
    //Double lock check for Thread safety
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new IDGenerator();
        }
      }
    }
    return instance;
  }

  public int generateMaterialID()
  {
    return -1;
  }

  public int generateLoanId()
  {
    return -1;
  }

  private IDGenerator()
  {
  }
}
