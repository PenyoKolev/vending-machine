package inventory;

import beverage.Drinks;

public class Item {
    
    private Drinks drink;
    private int price;
    
    public Item (Drinks type, int price) {
	this.setType(type);
	this.setPrice(price);
    }

    public Drinks getType() {
	return drink;
    }

    public void setType(Drinks type) {
	this.drink = type;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }
    
    //TODO hashCode & equals
}
