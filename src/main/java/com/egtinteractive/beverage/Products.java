package com.egtinteractive.beverage;

public class Products implements Articles {

  private String name;
  private int price;

  public Products(String name, int price) {
    this.setName(name);
    this.setPrice(price);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
