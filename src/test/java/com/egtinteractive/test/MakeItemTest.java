package com.egtinteractive.test;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.machine.VendingMachine;
import com.egtinteractive.provider.Provider;

public class MakeItemTest {

  @DataProvider(name = "standBy")
  public Object[][] machine() {
    return Provider.standByMachine();
  }

  @Test(dataProvider = "standBy")
  public void makeItemShouldChangeStateToTAKE_ITEM(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);
    Drinks drink = Drinks.CAPUCCINO;
    machine.selectItem(drink);

    // Act
    machine.makeItem();

    // Assert
    assertEquals(machine.getState().toString(), "TAKE_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void makeItemShouldAddItemPriceToFunds(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);
    Drinks drink = Drinks.CAPUCCINO;
    machine.selectItem(drink);
    int expected = drink.getPrice();

    // Act
    machine.makeItem();

    // Assert
    assertEquals(machine.getInventory().getFunds(), expected);
  }

  @Test(dataProvider = "standBy")
  public void makeItemShouldReduceBalanceWithItemPrice(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);
    Drinks drink = Drinks.CAPUCCINO;
    machine.selectItem(drink);
    int expected = machine.getBalance() - drink.getPrice();

    // Act
    machine.makeItem();

    // Assert
    assertEquals(machine.getBalance(), expected);
  }

  @Test(dataProvider = "standBy")
  public void test(VendingMachine machine) {
    // Arrange
    int quantity = 2;
    machine.service();
    machine.addProduct("Lemonade", 2, quantity);
    machine.endService();
    machine.putCoins(20);
    machine.selectItem("Lemonade");

    // Act
    machine.makeItem();

    // Assert
    assertEquals(machine.getInventory().getProducts().size(), quantity - 1);
  }

  @Test(dataProvider = "standBy")
  public void returnCoinsShouldSetBalanceToZero(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);
    Drinks drink = Drinks.CAPUCCINO;
    machine.selectItem(drink);

    // Act
    machine.returnCoins();

    // Assert
    assertEquals(machine.getBalance(), 0);
  }

  @Test(dataProvider = "standBy")
  public void returnCoinsShouldChangeStateToSTAND_BY(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);
    Drinks drink = Drinks.CAPUCCINO;
    machine.selectItem(drink);

    // Act
    machine.returnCoins();

    // Assert
    assertEquals(machine.getState().toString(), "STAND_BY");
  }
}
