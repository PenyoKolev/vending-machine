package inventory;

import beverage.Drinks;

public class Item {

    private Drinks drink;
    private int price;

    public Item(Drinks drink, int price) {
	this.setDrink(drink);
	this.setPrice(price);
    }

    public Drinks getDrink() {
	return drink;
    }

    public void setDrink(Drinks drink) {
	this.drink = drink;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    
    // TODO hashCode & equals
}
