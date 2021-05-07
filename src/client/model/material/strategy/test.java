package client.model.material.strategy;

import client.model.material.Material;

import java.sql.SQLException;
import java.util.List;

public class test
{
  public static void main(String[] args)
  {

   SearchStrategyManager searchStrategyManager = new SearchStrategyManager();
   searchStrategyManager.selectStrategy(new BookStrategy());
    try
    {
      List<Material> ml =  searchStrategyManager.searchAll();
      System.out.println(ml.size());
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
