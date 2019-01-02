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
	
	return machine;
    }
    
    public static VendingMachine selectItemNotAvailableMode() {
	machine = new VendingMachine();
	
	return machine;
    }
    
    public static VendingMachine selectItemEmptyMode() {
	
	return machine;
    }
    
    public static VendingMachine makeItemMode() {
	machine = new VendingMachine();
	
	return machine;
    }
    
    public static VendingMachine makeItemNotEnoughMoney() {
	machine = new VendingMachine();
	
	return machine;
    }
    
    public static VendingMachine takeItemMode() {
	machine = new VendingMachine();
	
	return machine;
    }
    
    public static VendingMachine serviceMode() {
	machine = new VendingMachine();
	return machine;
    }
}
