package com.egtinteractive.vendingMachine;

import beverage.Drinks;
import inventory.Inventory;
import machine.VendingMachine;

public class App {
    
    public static void main( String[] args ){
	
	
	VendingMachine machine = new VendingMachine();
	Inventory inventory = machine.getInventory();
	
	System.out.println(machine.getState());
	
	machine.service();
	machine.endService();
	
	System.out.println(machine.getState());

	Drinks drink = Drinks.AMERICANO;
	System.out.println(Drinks.disponibles());

	machine.putCoins(2);
	machine.putCoins(2);
	System.out.println(machine.getState());

	machine.selectDrink(drink);
	System.out.println(machine.getState());

	machine.makeDrink();
	System.out.println(machine.getState());

	machine.takeDrink();
	System.out.println(machine.getState());

	machine.putCoins(3);
	System.out.println(machine.getState());
	System.out.println(Drinks.disponibles());

	machine.selectDrink(Drinks.CAPUCCINO);
	System.out.println(machine.getState());
	
	machine.makeDrink();
	System.out.println(machine.getState());

	machine.takeDrink();
	System.out.println(machine.getState());

	System.out.println();
	System.out.printf("Inventory:\nbalance: %d\nslots: %d\ncoffee: %d\nmilk: %d", inventory.getBalance(), inventory.getSlots(), inventory.getCoffee(), inventory.getMilk());
    }
}
