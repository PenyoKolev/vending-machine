package com.egtinteractive.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.beverage.Products;
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
    Drinks drink = machine.selectItem(Drinks.CAPUCCINO);

    // Assert
    assertEquals(drink, Drinks.CAPUCCINO);
    assertEquals(machine.getStateName(), "SELECT_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void selectItemDrinkShouldChangeStateToMAKE_ITEM(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);

    // Act
    Drinks drink = machine.selectItem(Drinks.CAPUCCINO);

    // Assert
    assertEquals(drink, Drinks.CAPUCCINO);
    assertEquals(machine.getStateName(), "MAKE_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void selectItemDrinkShouldNotChangeStateIfItemNotAvailable(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);
    machine.getInventory().setMilk(1);

    // Act
    Drinks drink = machine.selectItem(Drinks.CAPUCCINO);

    // Assert
    assertEquals(drink, Drinks.CAPUCCINO);
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
    Drinks drink = machine.selectItem(Drinks.CAPUCCINO);

    // Assert
    assertEquals(drink, Drinks.CAPUCCINO);
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
    Drinks drink = machine.selectItem(Drinks.CAPUCCINO);

    // Assert
    assertEquals(drink, Drinks.CAPUCCINO);
    assertEquals(machine.getBalance(), 0);
  }

  @Test(dataProvider = "standBy")
  public void selectItemProductShouldNotChangeStateIfNotSuchProduct(VendingMachine machine) {
    // Arrange
    int coins = ThreadLocalRandom.current().nextInt(5, 100);
    machine.putCoins(coins);

    // Act
    Products product = machine.selectItem("Lemonade");

    // Assert
    assertNull(product);
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
    Products product = machine.selectItem("Chips");

    // Assert
    assertEquals(product.getName(), "Chips");
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
    Products product = machine.selectItem("Chips");

    // Assert
    assertEquals(product.getName(), "Chips");
    assertEquals(machine.getStateName(), "SELECT_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void putCoinsShouldAddToBalance(VendingMachine machine) {
    // Arrange
    int coins = ThreadLocalRandom.current().nextInt(1, 100);

    // Act
    int result = machine.putCoins(coins);

    // Assert
    assertEquals(result, coins);
    assertEquals(machine.getBalance(), coins);
  }

  @Test(dataProvider = "standBy")
  public void putNegativeCoinsShouldNotChangeBalance(VendingMachine machine) {
    // Arrange
    int coins = ThreadLocalRandom.current().nextInt(-100, -1);

    // Act
    int result = machine.putCoins(coins);

    // Assert
    assertEquals(result, coins);
    assertEquals(machine.getBalance(), 0);
  }

  @Test(dataProvider = "standBy", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(VendingMachine machine) {
    // Act
    machine.makeItem();
  }
}
