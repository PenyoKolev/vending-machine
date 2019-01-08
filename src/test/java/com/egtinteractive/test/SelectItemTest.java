package com.egtinteractive.test;

import static org.testng.Assert.assertEquals;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.machine.VendingMachine;
import com.egtinteractive.provider.Provider;

public class SelectItemTest {

  @DataProvider(name = "standBy")
  public Object[][] machine() {
    return Provider.standByMachine();
  }

  @Test(dataProvider = "standBy")
  public void selectItemDrinkShouldNotChangeStateIfNotEnoughMoney(VendingMachine machine) {
    // Arrange
    machine.putCoins(2);

    // Act
    machine.selectItem(Drinks.CAPUCCINO);

    // Assert
    assertEquals(machine.getStateName(), "SELECT_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void selectItemDrinkShouldChangeStateToMAKE_ITEM(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);

    // Act
    machine.selectItem(Drinks.CAPUCCINO);

    // Assert
    assertEquals(machine.getStateName(), "MAKE_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void selectItemDrinkShouldNotChangeStateIfItemNotAvailable(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);
    machine.getInventory().setMilk(1);

    // Act
    machine.selectItem(Drinks.CAPUCCINO);

    // Assert
    assertEquals(machine.getStateName(), "SELECT_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void selectItemDrinkShouldChangeStateToSERVICEIfNotEnoughIngredients(
      VendingMachine machine) {
    // Arrange
    machine.putCoins(20);
    machine.getInventory().setCoffee(0);
    machine.getInventory().setMilk(0);

    // Act
    machine.selectItem(Drinks.CAPUCCINO);

    // Assert
    assertEquals(machine.getStateName(), "SERVICE");
  }

  @Test(dataProvider = "standBy")
  public void selectItemDrinkShouldReturnMoneyIfNotEnoughIngredients(VendingMachine machine) {
    // Arrange
    int coins = ThreadLocalRandom.current().nextInt(5, 100);
    machine.putCoins(coins);
    machine.getInventory().setCoffee(0);
    machine.getInventory().setMilk(0);

    // Act
    machine.selectItem(Drinks.CAPUCCINO);

    // Assert
    assertEquals(machine.getBalance(), 0);
  }

  @Test(dataProvider = "standBy")
  public void selectItemProductShouldNotChangeStateIfNotSuchProduct(VendingMachine machine) {
    // Arrange
    int coins = ThreadLocalRandom.current().nextInt(5, 100);
    machine.putCoins(coins);

    // Act
    machine.selectItem("Lemonade");

    // Assert
    assertEquals(machine.getStateName(), "SELECT_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void selectItemProductShouldChangeStateToMAKE_ITEM(VendingMachine machine) {
    // Arrange
    machine.service();
    machine.addProduct("Chips", 3, 1);
    machine.endService();
    int coins = ThreadLocalRandom.current().nextInt(5, 100);
    machine.putCoins(coins);

    // Act
    machine.selectItem("Chips");

    // Assert
    assertEquals(machine.getStateName(), "MAKE_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void selectItemProductShouldNotChangeStateIfNotEnoughMoney(VendingMachine machine) {
    // Arrange
    machine.service();
    machine.addProduct("Chips", 3, 1);
    machine.endService();
    int coins = 2;
    machine.putCoins(coins);

    // Act
    machine.selectItem("Chips");

    // Assert
    assertEquals(machine.getStateName(), "SELECT_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void putCoinsShouldAddToBalance(VendingMachine machine) {
    // Arrange
    int oldCoins = ThreadLocalRandom.current().nextInt(1, 100);
    machine.putCoins(oldCoins);
    int coins = ThreadLocalRandom.current().nextInt(1, 100);

    // Act
    machine.putCoins(coins);

    // Assert
    assertEquals(machine.getBalance(), coins + oldCoins);
  }

  @Test(dataProvider = "standBy")
  public void putNegativeCoinsShouldNotChangeBalance(VendingMachine machine) {
    // Arrange
    int oldCoins = ThreadLocalRandom.current().nextInt(1, 100);
    machine.putCoins(oldCoins);
    int coins = ThreadLocalRandom.current().nextInt(-100, -1);

    // Act
    machine.putCoins(coins);

    // Assert
    assertEquals(machine.getBalance(), oldCoins);
  }
}
