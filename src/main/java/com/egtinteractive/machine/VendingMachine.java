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

  public StateMachine getState() {
    return state;
  }

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
    System.out.println(this.state);
    System.out.println("coffee: " + inventory.getCoffee());
    System.out.println("milk: " + inventory.getMilk());
    System.out.println("products: " + inventory.getProducts());
    System.out.println("balance: " + this.getBalance());
    System.out.println("funds: " + inventory.getFunds());
    return state.putCoins(this, coins);
  }

  @Override
  public int returnCoins() {
    System.out.println(this.state);
    System.out.println("coffee: " + inventory.getCoffee());
    System.out.println("milk: " + inventory.getMilk());
    System.out.println("products: " + inventory.getProducts());
    System.out.println("balance: " + this.getBalance());
    System.out.println("funds: " + inventory.getFunds());
    return state.returnCoins(this);
  }

  @Override
  public Drinks selectItem(Drinks drink) {
    System.out.println(this.state);
    System.out.println("coffee: " + inventory.getCoffee());
    System.out.println("milk: " + inventory.getMilk());
    System.out.println("products: " + inventory.getProducts());
    System.out.println("balance: " + this.getBalance());
    System.out.println("funds: " + inventory.getFunds());
    return state.selectItem(this, drink);
  }

  @Override
  public Products selectItem(String productName) {
    System.out.println(this.state);
    System.out.println("coffee: " + inventory.getCoffee());
    System.out.println("milk: " + inventory.getMilk());
    System.out.println("products: " + inventory.getProducts());
    System.out.println("balance: " + this.getBalance());
    System.out.println("funds: " + inventory.getFunds());
    return state.selectItem(this, productName);
  }

  @Override
  public void makeItem() {
    System.out.println(this.state);
    System.out.println("coffee: " + inventory.getCoffee());
    System.out.println("milk: " + inventory.getMilk());
    System.out.println("products: " + inventory.getProducts());
    System.out.println("balance: " + this.getBalance());
    System.out.println("funds: " + inventory.getFunds());
    state.makeItem(this);
  }

  @Override
  public Articles takeItem() {
    System.out.println(this.state);
    System.out.println("coffee: " + inventory.getCoffee());
    System.out.println("milk: " + inventory.getMilk());
    System.out.println("products: " + inventory.getProducts());
    System.out.println("balance: " + this.getBalance());
    System.out.println("funds: " + inventory.getFunds());
    return state.takeItem(this);
  }

  @Override
  public void service() {
    System.out.println(this.state);
    System.out.println("coffee: " + inventory.getCoffee());
    System.out.println("milk: " + inventory.getMilk());
    System.out.println("products: " + inventory.getProducts());
    System.out.println("balance: " + this.getBalance());
    System.out.println("funds: " + inventory.getFunds());
    state.service(this);
  }

  @Override
  public void endService() {
    System.out.println(this.state);
    System.out.println("coffee: " + inventory.getCoffee());
    System.out.println("milk: " + inventory.getMilk());
    System.out.println("products: " + inventory.getProducts());
    System.out.println("balance: " + this.getBalance());
    System.out.println("funds: " + inventory.getFunds());
    state.endService(this);
  }

  @Override
  public void addProduct(String name, int price, int quantity) {
    System.out.println(this.state);
    System.out.println("coffee: " + inventory.getCoffee());
    System.out.println("milk: " + inventory.getMilk());
    System.out.println("products: " + inventory.getProducts());
    System.out.println("balance: " + this.getBalance());
    System.out.println("funds: " + inventory.getFunds());
    state.addProduct(this, name, price, quantity);
  }

  @Override
  public void addProduct(Ingredients ingredients, int quantity) {
    System.out.println(this.state);
    System.out.println("coffee: " + inventory.getCoffee());
    System.out.println("milk: " + inventory.getMilk());
    System.out.println("products: " + inventory.getProducts());
    System.out.println("balance: " + this.getBalance());
    System.out.println("funds: " + inventory.getFunds());
    state.addProduct(this, ingredients, quantity);
  }
}
