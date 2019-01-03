package com.egtinteractive.machine;

import com.egtinteractive.beverage.Articles;
import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.beverage.Products;
import com.egtinteractive.machine.VendingMachine.Ingredients;

public interface Machine {

  public int putCoins(int coins);

  public int returnCoins();

  public Drinks selectItem(Drinks drink);
  
  public Products selectItem(String productName);
  
  public Articles makeDrink();

  public Articles takeDrink();
  
  public void service();

  public void endService();

  public void addProduct(String name, int price, int quantity);

  public void addProduct(Ingredients ingredients, int quantity);

}
