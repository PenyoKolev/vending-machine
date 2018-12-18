package inventory;

public enum Inventory {
    INSTANCE;

    private static final int MAX_FUNDS = 100;
    private static final int SODA_SLOTS_NUMBER = 12;
    private static final int COFFEE_TANK = 100;
    private static final int MILK_TANK = 100;

    private int funds;
    private int balance;
    private int slots;
    private int coffee;
    private int milk;

    private Inventory() {
	this.setFunds(funds);
	this.setBalance(balance);
	this.setSlots(slots);
	this.setCoffee(coffee);
	this.setMilk(milk);
    }

    public void fillUpInventory(Inventory inventory) {
	inventory.setFunds(MAX_FUNDS);
	inventory.setBalance(0);
	inventory.setSlots(SODA_SLOTS_NUMBER);
	inventory.setCoffee(COFFEE_TANK);
	inventory.setMilk(MILK_TANK);
    }

    public int getFunds() {
	return funds;
    }

    public void setFunds(int funds) {
	this.funds = funds;
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
