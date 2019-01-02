package machine;

import beverage.Drinks;
import beverage.Products;
import machine.VendingMachine.Ingredients;

public interface Machine {

  public int putCoins(int coins);

  public int returnCoins();

  public Drinks selectItem(Drinks drink);
  
  public Products selectItem(String productName);
  
  public Drinks makeDrink();

  public Drinks takeDrink();

  public void service();

  public void endService();

  public void addProduct(String name, int price, int quantity);

  public void addProduct(Ingredients ingredients, int quantity);

}
