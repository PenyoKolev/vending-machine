package com.egtinteractive.inventory;

import java.util.ArrayList;
import java.util.List;
import com.egtinteractive.beverage.Products;

public class Inventory {

  private List<Products> products;
  private int coffee;
  private int milk;

  /*
   * [WARNING] author ivailozd
   *
   * The profit shouldn't be Inventory's responsibility
   *
   */
  private int funds;

  public Inventory() {
    setProducts(new ArrayList<>());
    setCoffee(coffee);
    setMilk(milk);
    setFunds(funds);
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

  public int getFunds() {
    return funds;
  }

  public void setFunds(int funds) {
    this.funds = funds;
  }
}
