package com.egtinteractive.machine;

import java.util.Set;
import java.util.TreeSet;
import com.egtinteractive.beverage.Articles;
import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.beverage.Products;
import com.egtinteractive.inventory.Inventory;

public class VendingMachine implements Machine {
  public enum Ingredients {
    COFFEE,
    MILK
  }

  static final int DEFAULT_SIZE = 10;
  static final int COFFEE_TANK = 10;
  static final int MILK_TANK = 10;

  private StateMachine state;
  private Inventory inventory;
  private int balance;
  private Set<Drinks> available = new TreeSet<>();

  public VendingMachine() {
    this.state = StateMachine.SERVICE;

    /*
     * [WARNING] author ivailozd
     *
     *  It's better to pass the inventory as parameter
     *
     */
    this.setInventory(new Inventory());
    this.balance = 0;
  }

  public Set<Drinks> available() {
    for (Drinks drink : Drinks.values()) {
      if (inventory.getCoffee() >= drink.getCoffee() && inventory.getMilk() >= drink.getMilk()) {
        available.add(drink);
      }
    }
    return available;
  }

  public void updateInventory(Articles article) {
    if (article instanceof Drinks) {
      Drinks drink = (Drinks) article;
      inventory.setCoffee(inventory.getCoffee() - drink.getCoffee());
      inventory.setMilk(inventory.getMilk() - drink.getMilk());
      available = new TreeSet<>();
    } else {
      inventory.getProducts().remove(article);
    }
    inventory.setFunds(inventory.getFunds() + article.getPrice());
  }

  /*
   * [WARNING] author ivailozd
   *
   * Can not be public
   *
   */
  public StateMachine getState() {
    return state;
  }

  /*
   * [WARNING] author ivailozd
   *
   * Can not be public
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

  @Override
  public int putCoins(int coins) {
    return state.putCoins(this, coins);
  }

  @Override
  public int returnCoins() {
    return state.returnCoins(this);
  }

  @Override
  public Drinks selectItem(Drinks drink) {
    return state.selectItem(this, drink);
  }

  @Override
  public Products selectItem(String productName) {
    return state.selectItem(this, productName);
  }

  @Override
  public void makeItem() {
    state.makeItem(this);
  }

  @Override
  public Articles takeItem() {
    return state.takeItem(this);
  }

  @Override
  public void service() {
    state.service(this);
  }

  @Override
  public void endService() {
    state.endService(this);
  }

  @Override
  public void addProduct(String name, int price, int quantity) {
    state.addProduct(this, name, price, quantity);
  }

  @Override
  public void addProduct(Ingredients ingredients, int quantity) {
    state.addProduct(this, ingredients, quantity);
  }
}
