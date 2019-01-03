package com.egtinteractive.vendingMachine;

import static com.egtinteractive.machine.VendingMachine.Ingredients;
import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.machine.Machine;
import com.egtinteractive.machine.VendingMachine;

public class App {

  public static void main(String[] args) {

    Machine machine = new VendingMachine();

    machine.addProduct("Bahur", 10, 2);
    machine.addProduct("Sarmi", 7, 1);
    machine.addProduct("Cote de porc", 4, 1);
    machine.addProduct(Ingredients.COFFEE, 3);
    machine.addProduct(Ingredients.MILK, 5);

    machine.endService();

//    machine.putCoins(1);
//    machine.selectItem("Bahur");
    machine.putCoins(12);
    machine.selectItem("Bahur");
//    machine.selectItem(Drinks.CAPUCCINO);

    machine.makeDrink();
    machine.takeDrink();

    System.out.println();
    
    machine.putCoins(12);
    machine.selectItem(Drinks.CAPUCCINO);

    machine.makeDrink();
    machine.takeDrink();
  }
}
