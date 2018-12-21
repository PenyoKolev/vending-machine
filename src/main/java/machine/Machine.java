package machine;

import beverage.Drinks;
import machine.VendingMachine.Ingredients;

public interface Machine {

  public int putCoins(VendingMachine machine, int coins);

  public int returnCoins(VendingMachine machine);

  public Drinks selectDrink(VendingMachine machine, Drinks drink);

  public Drinks makeDrink(VendingMachine machine);

  public Drinks takeDrink(VendingMachine machine);

  public void service(VendingMachine machine);

  public void endService(VendingMachine machine);
  
  public void addProduct(VendingMachine machine, String name, int price, int quantity);
  
  public void addProduct(VendingMachine machine, Ingredients ingredients, int quantity);

}
