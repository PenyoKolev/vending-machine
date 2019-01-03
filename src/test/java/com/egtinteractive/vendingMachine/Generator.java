package com.egtinteractive.vendingMachine;

import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.machine.VendingMachine;
import com.egtinteractive.machine.VendingMachine.Ingredients;

public class Generator {

  private static VendingMachine machine;

  public static VendingMachine standByMode() {
    machine = new VendingMachine();
    machine.service();
    machine.addProduct(Ingredients.COFFEE, 3);
    machine.addProduct(Ingredients.MILK, 2);
    machine.endService();
    return machine;
  }

  public static VendingMachine selectItemMode() {
    machine = new VendingMachine();
    machine.service();
    machine.addProduct(Ingredients.COFFEE, 3);
    machine.addProduct(Ingredients.MILK, 2);
    machine.endService();
    machine.putCoins(12);
    machine.selectItem(Drinks.CAPUCCINO);
    return machine;
  }

  public static VendingMachine selectItemNotAvailableMode() {
    machine = new VendingMachine();
    machine.service();
    machine.addProduct(Ingredients.COFFEE, 3);
    machine.addProduct(Ingredients.MILK, 1);
    machine.endService();
    machine.putCoins(12);
    machine.selectItem(Drinks.CAPUCCINO);
    return machine;
  }

  public static VendingMachine selectItemEmptyMode() {
    machine = new VendingMachine();
    machine.service();
    machine.endService();
    machine.putCoins(12);
    machine.selectItem(Drinks.CAPUCCINO);
    return machine;
  }

  public static VendingMachine selectItemNotEnoughMoney() {
    machine = new VendingMachine();
    machine.service();
    machine.endService();
    machine.putCoins(2);
    machine.selectItem(Drinks.CAPUCCINO);
    return machine;
  }

  public static VendingMachine makeItemMode() {
    machine = new VendingMachine();
    machine.service();
    machine.endService();
    machine.putCoins(12);
    machine.selectItem(Drinks.CAPUCCINO);
    machine.makeItem();
    return machine;
  }

  public static VendingMachine takeItemMode() {
    machine = new VendingMachine();
    machine.service();
    machine.endService();
    machine.putCoins(12);
    machine.selectItem(Drinks.CAPUCCINO);
    machine.makeItem();
    machine.takeItem();
    return machine;
  }

  public static VendingMachine serviceMode() {
    machine = new VendingMachine();
    return machine;
  }
}
