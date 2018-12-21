package inventory;

public class Inventory {

//  private int slots;      // refactor SODA to Cold Drinks with options to add different drinks
  private int coffee;
  private int milk;

  public Inventory() {
//    setSlots(slots);
    setCoffee(coffee);
    setMilk(milk);
  }

//  public int getSlots() {
//    return slots;
//  }
//
//  public void setSlots(int slots) {
//    this.slots = slots;
//  }

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
