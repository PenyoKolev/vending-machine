package com.egtinteractive.test;

import org.testng.annotations.Test;
import com.egtinteractive.machine.VendingMachine;
import com.egtinteractive.provider.Provider;
import static org.testng.Assert.assertEquals;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.annotations.DataProvider;

public class StandByTest {

  @DataProvider(name = "standBy")
  public Object[][] machine() {
    return Provider.standByMachine();
  }

  @Test(dataProvider = "standBy")
  public void putCoinsShouldAddCoinsToBalance(VendingMachine machine) {
    // Arrange
    int coins = ThreadLocalRandom.current().nextInt(1, 100);
    int oldBalance = machine.getBalance();

    // Act
    int result = machine.putCoins(coins);
    int expected = oldBalance + coins;

    // Assert
    assertEquals(result, coins);
    assertEquals(machine.getBalance(), expected);
  }

  @Test(dataProvider = "standBy")
  public void putCoinsShouldChangeStateToSELECT_ITEM(VendingMachine machine) {
    // Arrange
    int coins = ThreadLocalRandom.current().nextInt(1, 100);

    // Act
    int result = machine.putCoins(coins);

    // Assert
    assertEquals(result, coins);
    assertEquals(machine.getStateName(), "SELECT_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void putNegativeCoinsShouldNotChangeState(VendingMachine machine) {
    // Arrange
    int coins = ThreadLocalRandom.current().nextInt(-100, -1);

    // Act
    int result = machine.putCoins(coins);

    // Assert
    assertEquals(result, coins);
    assertEquals(machine.getStateName(), "STAND_BY");
  }

  @Test(dataProvider = "standBy")
  public void serviceShouldChangeStateToSERVICE(VendingMachine machine) {
    // Act
    machine.service();

    // Assert
    assertEquals(machine.getStateName(), "SERVICE");
  }

  @Test(dataProvider = "standBy", expectedExceptions = IllegalStateException.class)
  public void methodUnsuportedForTheStateShouldDoNothing(VendingMachine machine) {
    // Act
    machine.makeItem();
  }
}
