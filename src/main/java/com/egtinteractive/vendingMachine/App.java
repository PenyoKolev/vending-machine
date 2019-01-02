package com.egtinteractive.vendingMachine;

import machine.Machine;
import machine.VendingMachine;
import static machine.VendingMachine.Ingredients;
import beverage.Drinks;

public class App {

  public static void main(String[] args) {

    Machine machine = new VendingMachine();

    machine.addProduct("Bahur", 10, 2);
    machine.addProduct("Sarmi", 7, 1);
    machine.addProduct("Cote de porc", 4, 1);
    machine.addProduct(Ingredients.COFFEE, 3);
    machine.addProduct(Ingredients.MILK, 5);

    machine.endService();

    machine.putCoins(1);
    machine.selectItem(Drinks.CAPUCCINO);
    machine.putCoins(9);
    machine.selectItem(Drinks.CAPUCCINO);

    machine.makeDrink();
    machine.takeDrink();

    System.out.println();
  }
}
