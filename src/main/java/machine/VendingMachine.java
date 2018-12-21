package machine;

import java.util.Set;
import java.util.TreeSet;
import beverage.Drinks;
import inventory.Inventory;

/*
 * [WARNING] author ivailozd
 *
 * Violation of Dependency inversion principle
 *
 */
public class VendingMachine { // add inventory to constructor
  
//  private static final int SODA_SLOTS_NUMBER = 10;  refactor SODA to Cold Drinks with options to add different drinks
  private static final int COFFEE_TANK = 10;
  private static final int MILK_TANK = 10;

  private StateMachine state;
  private Inventory inventory;
  private int balance; // should implement it here
  private Set<Drinks> disponibles;

  public VendingMachine() {
    this.state = StateMachine.SERVICE;
    this.setInventory(new Inventory());
    this.balance = 0;
  }

  public Set<Drinks> disponibles() {
    for (Drinks drink : Drinks.values()) {
      if (inventory.getCoffee() >= drink.getCoffee() && inventory.getMilk() >= drink.getMilk()) {
//        disponibles.add(drink);
      }
//      if (inventory.getSlots() <= 0) {        refactor SODA to Cold Drinks with options to add different drinks
//        disponibles.remove(SODA); // should refactor method for SODAs
//      }
    }
    return disponibles;
  }

  public void updateInventory(Drinks drinks) {
    setBalance(getBalance() - drinks.getPrice());
    inventory.setCoffee(inventory.getCoffee() - drinks.getCoffee());
    inventory.setMilk(inventory.getMilk() - drinks.getMilk());
    disponibles = new TreeSet<>();
  }
  
  public void fillUpInventory() {
//    inventory.setSlots(SODA_SLOTS_NUMBER);
    inventory.setCoffee(COFFEE_TANK);
    inventory.setMilk(MILK_TANK);
  }

  private void create() {
    // TODO should implement it here
  }

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
}
