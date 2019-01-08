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

  private StateMachine state;
  private Inventory inventory;
  private int balance;
  private int funds;
  private Articles article;
  private Set<Drinks> available = new TreeSet<>();

  public VendingMachine(Inventory inventory) {
    this.state = StateMachine.SERVICE;
    this.inventory = inventory;
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
    setFunds(getFunds() + article.getPrice());
  }

  StateMachine getState() {
    return state;
  }

  void setState(StateMachine state) {
    this.state = state;
  }
  
  public String getStateName() {
    return state.name();
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

  public int getFunds() {
    return funds;
  }

  public void setFunds(int funds) {
    this.funds = funds;
  }

  public Articles getArticle() {
    return article;
  }

  public void setArticle(Articles article) {
    this.article = article;
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
    return state.selectItem(this, inventory, drink);
  }

  @Override
  public Products selectItem(String productName) {
    return state.selectItem(this, inventory, productName);
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
    state.addProduct(this, name, inventory, price, quantity);
  }

  @Override
  public void addProduct(Ingredients ingredients, int quantity) {
    state.addProduct(this, ingredients, inventory, quantity);
  }
}
