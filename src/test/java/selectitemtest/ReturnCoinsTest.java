package selectitemtest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.vendingMachine.Provider;

import machine.VendingMachine;

public class ReturnCoinsTest {
  
    @DataProvider(name = "vendingMachine")
    public Object[][] getData() {
	return Provider.selectItemMachine();
    }

    @Test(dataProvider = "vendingMachine")
    public void returnCoinsShouldReturnAllCoins(VendingMachine machine) {
	// Act
	machine.returnCoins();

	// Assert
	assertEquals(machine.getInventory().getBalance(), 0);
    }
    
    @Test(dataProvider = "vendingMachine")
    public void returnCoinsShouldChangeStateToSTAND_BY(VendingMachine machine) {
	// Act
	machine.returnCoins();

	// Assert
	assertEquals(machine.getState().toString(), "STAND_BY");
    }
}
