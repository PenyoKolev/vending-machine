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
    machine.putCoins(coins);
    int expected = oldBalance + coins;

    // Assert
    assertEquals(machine.getBalance(), expected);
  }

  @Test(dataProvider = "standBy")
  public void putCoinsShouldChangeStateToSELECT_ITEM(VendingMachine machine) {
    // Arrange
    int coins = ThreadLocalRandom.current().nextInt(1, 100);

    // Act
    machine.putCoins(coins);

    // Assert
    assertEquals(machine.getState().toString(), "SELECT_ITEM");
  }

  @Test(dataProvider = "standBy")
  public void putNegativeCoinsShouldNotChangeState(VendingMachine machine) {
    // Arrange
    int coins = ThreadLocalRandom.current().nextInt(-100, -1);

    // Act
    machine.putCoins(coins);

    // Assert
    assertEquals(machine.getState().toString(), "STAND_BY");
  }
  
  @Test(dataProvider = "standBy")
  public void serviceShouldChangeStateToSERVICE(VendingMachine machine) {
    // Act
    machine.service();

    // Assert
    assertEquals(machine.getState().toString(), "SERVICE");
  }
  
  @Test(dataProvider = "standBy")
  public void methodUnsuportedForTheStateShouldDoNothing(VendingMachine machine) {
    // Act
    machine.makeItem();

    // Assert
    assertEquals(machine.getState().toString(), "STAND_BY");
  }
}
