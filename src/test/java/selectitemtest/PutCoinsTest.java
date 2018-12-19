package selectitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import machine.VendingMachine;

public class PutCoinsTest {
    
    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.selectItemMachine();
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
