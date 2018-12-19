package standbytest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import machine.VendingMachine;

public class StandByTest {
    
    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.standByMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void putCoinsShouldChangeStateToSELECT_ITEM(VendingMachine machine) {
	// Arrange
	int coins = 10;

	// Act
	machine.putCoins(coins);

	// Assert
	assertEquals(machine.getState().toString(), "SELECT_ITEM");
    }
    
    @Test(dataProvider = "vendingMachine")
    public void putCoinsShouldAddCoinsToBalance(VendingMachine machine) {
	// Arrange
	int balance = machine.getInventory().getBalance();
	int coins = 10;

	// Act
	machine.putCoins(coins);

	// Assert
	assertEquals(machine.getInventory().getBalance(), balance + coins);
    }
}
