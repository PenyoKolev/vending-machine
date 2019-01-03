package com.egtinteractive.provider;

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

  public static VendingMachine serviceMode() {
    machine = new VendingMachine();
    return machine;
  }
}
