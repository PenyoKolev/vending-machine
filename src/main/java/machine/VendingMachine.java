package machine;

import beverage.Drinks;

public class VendingMachine {

    private StateMachine state;

    public VendingMachine() {
	this.state = StateMachine.SERVICE;
    }

    public StateMachine getState() {
	return state;
    }

    public void setState(StateMachine state) {
	this.state = state;
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
