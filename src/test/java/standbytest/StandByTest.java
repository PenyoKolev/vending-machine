package standbytest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import inventory.Inventory;
import machine.VendingMachine;

public class StandByTest {
    
//    @DataProvider(name = "vendingMachine")
//    public Object[][] getData() {
//	return Provider.standByMachine();
//    }
//
//    @Test(dataProvider = "vendingMachine")
//    public void putCoinsShouldChangeStateToSELECT_ITEM(VendingMachine machine) {
//	// Arrange
//	int coins = 10;
//
//	// Act
//	machine.putCoins(coins);
//
//	// Assert
//	assertEquals(machine.getState().toString(), "SELECT_ITEM");
//    }
//    
//    @Test(dataProvider = "vendingMachine")
//    public void putCoinsShouldAddCoinsToBalance(VendingMachine machine) {
//	// Arrange
//	Inventory inventory = Inventory.INSTANCE;
//	int balance = inventory.getBalance();
//	int coins = 10;
//
//	// Act
//	machine.putCoins(coins);
//
//	// Assert
//	assertEquals(inventory.getBalance(), balance + coins);
//    }
}
