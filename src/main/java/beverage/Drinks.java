package beverage;

import java.util.Set;
import java.util.TreeSet;

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

    static Set<Drinks> disponibles = new TreeSet<>();

    private static void updateInventory(Drinks drinks, Item item) {
	inventory.setBalance(inventory.getBalance() - drinks.getPrice());
	inventory.setCoffee(inventory.getCoffee() - drinks.getCoffee());
	inventory.setMilk(inventory.getMilk() - drinks.getMilk());
	disponibles = new TreeSet<>();
    }

    public Set<Drinks> disponibles() {
	for (Drinks drink : Drinks.values()) {
	    if (inventory.getCoffee() >= drink.getCoffee() && inventory.getMilk() >= drink.getMilk()) {
		disponibles.add(drink);
	    }
	    if (inventory.getSlots() <= 0) {
		disponibles.remove(SODA);
	    }
	}
	return disponibles;
    }
}
