package functions;

import inventory.Inventory;

public class Service {
    
    private static final int MAX_FUNDS = 100;
    private static final int SODA_SLOTS_NUMBER = 12;
    private static final int COFFEE_TANK = 100;
    private static final int MILK_TANK = 100;
    
    public void fillUpInventory(Inventory inventory) {
	inventory.setFunds(MAX_FUNDS);
	inventory.setSlots(SODA_SLOTS_NUMBER);
	inventory.setCoffee(COFFEE_TANK);
	inventory.setMilk(MILK_TANK);
    }
}
