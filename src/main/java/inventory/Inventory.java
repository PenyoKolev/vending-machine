package inventory;

import java.util.ArrayList;
import java.util.List;
import beverage.Products;

public class Inventory {

  private List<Products> products;
  private int coffee;
  private int milk;

  public Inventory() {
    setProducts(new ArrayList<>());
    setCoffee(coffee);
    setMilk(milk);
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
