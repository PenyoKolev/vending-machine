package selectitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import machine.VendingMachine;

public class PutCoinsShouldNotChangeState {
    
    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.selectItemMachine();
    }
    
    @Test(dataProvider = "vendingMachine")
    public void putCoinsShouldNotChangeState(VendingMachine machine) {
	// Arrange
	String state = machine.getState().toString();
	int coins = 10;

	// Act
	machine.putCoins(coins);

	// Assert
	assertEquals(machine.getState().toString(), state);
    }
}
