package machine;

import beverage.Drinks;
import inventory.Inventory;

/*
 * [WARNING] author ivailozd
 *
 * Violation of Dependency inversion principle
 *
 */
public class VendingMachine { // add inventory to constructor

  static final int DEFAULT_SIZE = 10;
  static final int COFFEE_TANK = 10;
  static final int MILK_TANK = 10;

  private StateMachine state;
  private Inventory inventory;
  private int balance;

  public enum Ingredients {
    COFFEE,
    MILK
  }

  public VendingMachine() {
    this.state = StateMachine.SERVICE;
    this.setInventory(new Inventory());
    this.balance = 0;
  }

  public void updateInventory(Drinks drinks) {
    //    setBalance(getBalance() - drinks.getPrice());
    //    inventory.setCoffee(inventory.getCoffee() - drinks.getCoffee());
    //    inventory.setMilk(inventory.getMilk() - drinks.getMilk());
  }

  public void fillUpInventory() {
    //    inventory.setSlots.size(SODA_SLOTS_NUMBER);
    //    inventory.setCoffee(COFFEE_TANK);
    //    inventory.setMilk(MILK_TANK);
  }

  //  private void create() {
  //    // TODO should implement it here
  //  }

  public StateMachine getState() {
    return state;
  }

  /*
   * [WARNING] author ivailozd
   *
   * What are the dangers of the method's visibility?
   *
   */
  public void setState(StateMachine state) {
    this.state = state;
  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public int putCoins(int coins) {
    return state.putCoins(this, coins);
  }

  public int returnCoins() {
    return state.returnCoins(this);
  }

  public Drinks selectDrink(Drinks drink) {
    return state.selectDrink(this, drink);
  }

  public Drinks makeDrink() {
    return state.makeDrink(this);
  }

  public Drinks takeDrink() {
    return state.takeDrink(this);
  }

  public void service() {
    state.service(this);
  }

  public void endService() {
    state.endService(this);
  }

  public void addProduct(String name, int price, int quantity) {
    state.addProduct(this, name, price, quantity);
  }

  public void addProduct(Ingredients ingredients, int quantity) {
    state.addProduct(this, ingredients, quantity);
  }
}
