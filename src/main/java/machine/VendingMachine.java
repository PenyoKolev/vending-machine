package machine;

import beverage.Drinks;
import inventory.Inventory;

public enum VendingMachine {
    INSTANCE;

    private StateMachine state;
    private Inventory inventory;

    private VendingMachine() {
	this.state = StateMachine.SERVICE;
	this.inventory = Inventory.INSTANCE;
    }

    public StateMachine getState() {
	return state;
    }

    public void setState(StateMachine state) {
	this.state = state;
    }

    public Inventory getInventory() {
	return inventory;
    }

    public void setInventory(Inventory inventory) {
	this.inventory = inventory;
    }
    
    public void putCoins(int coins) {
	state.putCoins(this, coins);
    }
    
    public void returnCoins() {
	state.returnCoins(this);
    }
    
    public void selectDrink(Drinks drink) {
	state.selectDrink(this, drink);
    }
    
    public void makeDrink() {
	state.makeDrink(this);
    }

    public void takeDrink() {
	state.takeDrink(this);
    }

    public void service() {
	state.service(this);
    }
    
    public void endService() {
	state.endService(this);
    }
   
}
