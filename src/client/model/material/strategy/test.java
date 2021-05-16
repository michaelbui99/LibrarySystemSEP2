package client.model.material.strategy;

import shared.materials.Material;

import java.util.List;

public class test
{
  public static void main(String[] args)
  {

   SearchStrategyManager searchStrategyManager = new SearchStrategyManager();
   searchStrategyManager.selectStrategy("audiobook");
    List<Material> ml =  searchStrategyManager.findMaterial("","","","","");
    System.out.println(ml.size());
  }
}
