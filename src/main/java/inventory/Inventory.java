package inventory;

public enum Inventory {
    INSTANCE;
    
    private int funds;
    private int slots;
    private int coffee;
    private int milk;
    
    private Inventory() {
	this.setFunds(funds);
	this.setSlots(slots);
	this.setCoffee(coffee);
	this.setMilk(milk);
    }

    public int getFunds() {
	return funds;
    }

    public void setFunds(int funds) {
	this.funds = funds;
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
