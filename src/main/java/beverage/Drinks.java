package beverage;

import inventory.Inventory;
import inventory.Item;

public enum Drinks {
    SODA(3, 0, 0) {
	@Override
	public Item create() {
	    Item item = new Item(this, getPrice());
	    inventory.setSlots(inventory.getSlots() - 1);
	    updateInventory(this, item);
	    return item;
	}
    },
    AMERICANO(2, 2, 0) {
	@Override
	public Item create() {
	    Item item = new Item(this, getPrice());
	    updateInventory(this, item);
	    return item;
	}
    },
    CAPUCCINO(3, 1, 2) {
	@Override
	public Item create() {
	    Item item = new Item(this, getPrice());
	    updateInventory(this, item);
	    return item;
	}
    },
    LATTE(2, 1, 1) {
	@Override
	public Item create() {
	    Item item = new Item(this, getPrice());
	    updateInventory(this, item);
	    return item;
	}
    };

    static Inventory inventory = Inventory.INSTANCE;

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

    public abstract Item create();

    private static void updateInventory(Drinks drinks, Item item) {
	inventory.setFunds(inventory.getFunds() + item.getPrice());
	inventory.setCoffee(inventory.getCoffee() - drinks.getCoffee());
	inventory.setMilk(inventory.getMilk() - drinks.getMilk());
    }
}
