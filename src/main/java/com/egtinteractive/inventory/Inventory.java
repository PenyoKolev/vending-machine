package com.egtinteractive.inventory;

import java.util.HashMap;
import java.util.Map;
import com.egtinteractive.beverage.Products;

public class Inventory {

  public final int DEFAULT_SIZE = 10;
  public final int COFFEE_TANK = 10;
  public final int MILK_TANK = 10;

  private Map<String, ProductPair> products;
  private int coffee;
  private int milk;

  public Inventory() {
    this.products = new HashMap<>();
    this.coffee = 0;
    this.milk = 0;
  }

  public Products getProductByName(String productName) {

    if (products.containsKey(productName)) {
      return products.get(productName).getProduct();
    }
    return null; 
  }
  
  public Map<String, ProductPair> getProducts() {
    return products;
  }

  public void setProducts(Map<String, ProductPair> products) {
    this.products = products;
  }

  public int getCoffee() {
    return coffee;
  }

  public void setCoffee(int coffee) {
    this.coffee = coffee;
  }

  public int getMilk() {
    return milk;
  }

  public void setMilk(int milk) {
    this.milk = milk;
  }

  public int getSize() {
    int result = 0;
    for (ProductPair pair : this.getProducts().values()) {
      result += pair.getQuantity();
    }
    return result;
  }
}
