package com.egtinteractive.vendingMachine;

import beverage.Products;
import inventory.Inventory;
import machine.VendingMachine;
import static machine.VendingMachine.Ingredients;

public class App {

  public static void main(String[] args) {

    VendingMachine machine = new VendingMachine();
    Inventory inventory = machine.getInventory();

    System.out.println(machine.getState());

    machine.service();
//    machine.endService();

    System.out.println(machine.getState());

    //    Drinks drink = Drinks.AMERICANO;
    //    System.out.println(machine.disponibles());
    //
    //    machine.putCoins(2);
    //    machine.putCoins(2);
    //    System.out.println(machine.getState());
    //
    //    machine.selectDrink(drink);
    //    System.out.println(machine.getState());
    //
    //    machine.makeDrink();
    //    System.out.println(machine.getState());
    //
    //    machine.takeDrink();
    //    System.out.println(machine.getState());
    //
    //    machine.putCoins(3);
    //    System.out.println(machine.getState());
    //
    //    machine.selectDrink(Drinks.CAPUCCINO);
    //    System.out.println(machine.getState());
    //
    //    machine.selectDrink(Drinks.SODA);
    //    System.out.println(machine.getState());
    //
    //    machine.makeDrink();
    //    System.out.println(machine.getState());
    //
    //    machine.takeDrink();
    //    System.out.println(machine.getState());
    //
    //    machine.service();
    
   
    machine.addProduct("Bahur" , 10, 2);
    machine.addProduct("Sarmi" , 7, 1);
    machine.addProduct("Cote de porc", 4, 1);
    machine.addProduct(Ingredients.COFFEE, 3);
    machine.addProduct(Ingredients.MILK, 5);

    System.out.println();
    System.out.printf(
        "Inventory:\nbalance: %d\ncoffee: %d\nmilk: %d",
        machine.getBalance(), inventory.getCoffee(), inventory.getMilk());

    System.out.println();
    System.out.println();

    for (Products product : inventory.getProducts()) {
      System.out.println(product.getName() + " " + product.getPrice() + " lv");
    }
    System.out.println();
    System.out.println("size of products: " + inventory.getProducts().size());
  }
}
