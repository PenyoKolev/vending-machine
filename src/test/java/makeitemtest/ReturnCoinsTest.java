package makeitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import inventory.Inventory;
import machine.VendingMachine;

public class ReturnCoinsTest {
  
//    @DataProvider(name = "vendingMachine")
//    public Object[][] getData() {
//	return Provider.makeItemMachine();
//    }
//
//    @Test(dataProvider = "vendingMachine")
//    public void returnCoinsShouldReturnAllCoins(VendingMachine machine) {
//	//Arrange
//	Inventory inventory = Inventory.INSTANCE;
//	// Act
//	machine.returnCoins();
//
//	// Assert
//	assertEquals(inventory.getBalance(), 0);
//    }
//    
//    @Test(dataProvider = "vendingMachine")
//    public void returnCoinsShouldChangeStateToSTAND_BY(VendingMachine machine) {
//	// Act
//	machine.returnCoins();
//
//	// Assert
//	assertEquals(machine.getState().toString(), "STAND_BY");
//    }
}
