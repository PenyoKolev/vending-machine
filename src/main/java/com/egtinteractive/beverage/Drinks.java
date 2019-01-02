package com.egtinteractive.beverage;

public enum Drinks {
  AMERICANO(2, 2, 0),
  CAPUCCINO(3, 1, 2),
  LATTE(2, 1, 1);

  private final int price;
  private final int coffee;
  private final int milk;

  private Drinks(int price, int coffee, int milk) {
    this.price = price;
    this.coffee = coffee;
    this.milk = milk;
  }

  public int getPrice() {
    return price;
  }

  public int getCoffee() {
    return coffee;
  }

  public int getMilk() {
    return milk;
  }
}
