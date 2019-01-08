package com.egtinteractive.inventory;

import java.util.ArrayList;
import java.util.List;
import com.egtinteractive.beverage.Products;

public class Inventory {
  
  public final int DEFAULT_SIZE = 10;
  public final int COFFEE_TANK = 10;
  public final int MILK_TANK = 10;

  private List<Products> products;
  private int coffee;
  private int milk;

  public Inventory() {
    this.products = new ArrayList<>();
    this.coffee = 0;
    this.milk = 0;
  }

  public Products getProductByName(String productName) {

    /*
     * [WARNING] author ivailozd
     *
     *  Should find the product faster
     *
     */
    for (Products product : products) {
      if (product.getName().equals(productName)) {
        return product;
      }
    }
    return null;
  }

  public List<Products> getProducts() {
    return products;
  }

  public void setProducts(List<Products> products) {
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
}
