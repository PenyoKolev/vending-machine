package com.egtinteractive.vendingMachine;

public class Provider {

  public static Object[][] standByMachine() {
    return new Object[][] {{Generator.standByMode()}};
  }

  public static Object[][] selectItemMachine() {
    return new Object[][] {{Generator.selectItemMode()}};
  }

  public static Object[][] selectItemNotAvailableMachine() {
    return new Object[][] {{Generator.selectItemNotAvailableMode()}};
  }

  public static Object[][] selectItemEmptyMachine() {
    return new Object[][] {{Generator.selectItemEmptyMode()}};
  }

  public static Object[][] selectItemNotEnoughMoneyMachine() {
    return new Object[][] {{Generator.selectItemNotEnoughMoney()}};
  }

  public static Object[][] makeItemMachine() {
    return new Object[][] {{Generator.makeItemMode()}};
  }

  public static Object[][] takeItemMachine() {
    return new Object[][] {{Generator.takeItemMode()}};
  }

  public static Object[][] serviceMachine() {
    return new Object[][] {{Generator.serviceMode()}};
  }
}
