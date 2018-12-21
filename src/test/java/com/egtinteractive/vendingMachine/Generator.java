package com.egtinteractive.vendingMachine;

import beverage.Drinks;
import machine.VendingMachine;

public class Generator {

    private static VendingMachine machine;
    
    public static VendingMachine standByMode() {
	machine = new VendingMachine();
	machine.service();
	machine.endService();
	return machine;
    }
    
    public static VendingMachine selectItemMode() {
	machine = new VendingMachine();
	machine.service();
	machine.endService();
	machine.putCoins(10);
	return machine;
    }
    
    public static VendingMachine selectItemNotAvailableMode() {
	machine = new VendingMachine();
	machine.service();
	machine.endService();
	machine.putCoins(10);
	machine.selectDrink(Drinks.CAPUCCINO);
	machine.makeDrink();
	machine.takeDrink();
	machine.putCoins(10);
	return machine;
    }
    
    public static VendingMachine selectItemEmptyMode() {
	machine = new VendingMachine();
	machine.service();
	machine.endService();
	machine.putCoins(10);
	machine.selectDrink(Drinks.CAPUCCINO);
	machine.makeDrink();
	machine.takeDrink();
	machine.putCoins(10);
//	machine.selectDrink(Drinks.SODA);
	machine.makeDrink();
	machine.takeDrink();
	machine.putCoins(10);
	return machine;
    }
    
    public static VendingMachine makeItemMode() {
	machine = new VendingMachine();
	machine.service();
	machine.endService();
	machine.putCoins(10);
	machine.selectDrink(Drinks.CAPUCCINO);
	return machine;
    }
    
    public static VendingMachine makeItemNotEnoughMoney() {
	machine = new VendingMachine();
	machine.service();
	machine.endService();
	machine.putCoins(1);
	machine.selectDrink(Drinks.CAPUCCINO);
	return machine;
    }
    
    public static VendingMachine takeItemMode() {
	machine = new VendingMachine();
	machine.service();
	machine.endService();
	machine.putCoins(10);
	machine.selectDrink(Drinks.CAPUCCINO);
	machine.makeDrink();
	return machine;
    }
    
    public static VendingMachine serviceMode() {
	machine = new VendingMachine();
	return machine;
    }
}
