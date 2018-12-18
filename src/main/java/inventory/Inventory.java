package inventory;

public enum Inventory {
    INSTANCE;

    private static final int SODA_SLOTS_NUMBER = 12;
    private static final int COFFEE_TANK = 100;
    private static final int MILK_TANK = 100;

    private int balance;
    private int slots;
    private int coffee;
    private int milk;

    private Inventory() {
	setBalance(balance);
	setSlots(slots);
	setCoffee(coffee);
	setMilk(milk);
    }

    public void fillUpInventory() {
	setBalance(0);
	setSlots(SODA_SLOTS_NUMBER);
	setCoffee(COFFEE_TANK);
	setMilk(MILK_TANK);
    }

    public int getBalance() {
	return balance;
    }

    public void setBalance(int balance) {
	this.balance = balance;
    }

    public int getSlots() {
	return slots;
    }

    public void setSlots(int slots) {
	this.slots = slots;
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
