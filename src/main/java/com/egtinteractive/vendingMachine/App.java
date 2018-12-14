package com.egtinteractive.vendingMachine;

import beverage.Drinks;
import functions.Service;
import inventory.Inventory;
import inventory.Item;

public class App {
    
    public static void main( String[] args ){
	
	Inventory inventory = Inventory.INSTANCE;
	Service service = new Service();
	service.fillUpInventory(inventory);
	
	Item soda = Drinks.SODA.create();
	Item americano = Drinks.AMERICANO.create();
	Item capuccino = Drinks.CAPUCCINO.create();
	Item latte = Drinks.LATTE.create();
	
	System.out.printf("Inventory funds: %d\nslots: %d\ncoffee: %d\nmilk: %d", inventory.getFunds(), inventory.getSlots(),
		inventory.getCoffee(), inventory.getMilk());

    }
}
