package machine;

import beverage.Drinks;
import inventory.Inventory;

public class VendingMachine {

    private StateMachine state;
    private Inventory inventory;

    public VendingMachine() {
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
    
    public int putCoins(int coins) {
	return state.putCoins(this, coins);
    }
    
    public int returnCoins() {
	return state.returnCoins(this);
    }
    
    public Drinks selectDrink(Drinks drink) {
	return state.selectDrink(this, drink);
    }
    
    public Drinks makeDrink() {
	return state.makeDrink(this);
    }

    public Drinks takeDrink() {
	return state.takeDrink(this);
    }

    public void service() {
	state.service(this);
    }
    
    public void endService() {
	state.endService(this);
    }
   
}
