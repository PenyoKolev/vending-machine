package com.egtinteractive.vendingMachine;

import static com.egtinteractive.machine.VendingMachine.Ingredients;
import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.machine.Machine;
import com.egtinteractive.machine.VendingMachine;

public class App {

  public static void main(String[] args) {

    Machine machine = new VendingMachine();

    machine.addProduct("Water", 2, 2);
    machine.addProduct("Schweppes", 3, 1);
    machine.addProduct("Perrier", 4, 1);
    machine.addProduct(Ingredients.COFFEE, 3);
    machine.addProduct(Ingredients.MILK, 2);

    machine.endService();

    machine.putCoins(12);
    machine.selectItem("Water");

    machine.makeItem();
    machine.takeItem();

    System.out.println();

    machine.putCoins(12);
    machine.selectItem(Drinks.CAPUCCINO);

    machine.makeItem();
    machine.takeItem();
    
  }
}
