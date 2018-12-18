package com.egtinteractive.vendingMachine;

import beverage.Drinks;
import inventory.Inventory;
import machine.VendingMachine;

public class App {
    
    public static void main( String[] args ){
	
	
	VendingMachine machine = VendingMachine.INSTANCE;
	Inventory inventory = machine.getInventory();
	machine.service();

	System.out.println(machine.getState());

	Drinks drink = Drinks.SODA;
	machine.putCoins(5);

	System.out.println(machine.getState());
	
	
	machine.selectDrink(drink);

	System.out.println(machine.getState());
	
	machine.makeDrink();
	
	System.out.println(machine.getState());

	machine.takeDrink();
	
	System.out.println(machine.getState());
	
//	machine.putCoins(3);
//	System.out.println(machine.getState());
//	
//	machine.selectDrink(Drinks.CAPUCCINO);
//	System.out.println(machine.getState());
//
//	machine.makeDrink();
//	System.out.println(machine.getState());
//
//	machine.takeDrink();
//	System.out.println(machine.getState());

	System.out.println();
	System.out.printf("Inventory:\nfunds: %d\nbalance: %d\nslots: %d\ncoffee: %d\nmilk: %d", inventory.getFunds(), inventory.getBalance(), inventory.getSlots(), inventory.getCoffee(), inventory.getMilk());
    }
}
