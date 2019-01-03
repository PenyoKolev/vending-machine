package com.egtinteractive.test;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.egtinteractive.beverage.Drinks;
import com.egtinteractive.machine.VendingMachine;
import com.egtinteractive.provider.Provider;

public class TakeItemTest {

  @DataProvider(name = "standBy")
  public Object[][] machine() {
    return Provider.standByMachine();
  }

  @Test(dataProvider = "standBy")
  public void takeItemShouldChangeStateToSTAND_BY(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);
    machine.selectItem(Drinks.CAPUCCINO);
    machine.makeItem();

    // Act
    machine.takeItem();

    // Assert
    assertEquals(machine.getState().toString(), "STAND_BY");
  }
  
  @Test(dataProvider = "standBy")
  public void takeItemShouldReturnChange(VendingMachine machine) {
    // Arrange
    int coins = 20;
    Drinks drink = Drinks.CAPUCCINO;
    machine.putCoins(coins);
    machine.selectItem(drink);
    machine.makeItem();

    // Act
    machine.takeItem();

    // Assert
    assertEquals(coins - machine.getInventory().getFunds(), coins - drink.getPrice());
  }
  
  @Test(dataProvider = "standBy")
  public void takeItemShouldSetBalanceToZero(VendingMachine machine) {
    // Arrange
    machine.putCoins(20);
    machine.selectItem(Drinks.CAPUCCINO);
    machine.makeItem();

    // Act
    machine.takeItem();

    // Assert
    assertEquals(machine.getBalance(), 0);
  }
}
